package com.cbjprivilege.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cbjprivilege.beans.PageQuery;
import com.cbjprivilege.beans.PageResult;
import com.cbjprivilege.common.RequestHolder;
import com.cbjprivilege.dao.SysUserMapper;
import com.cbjprivilege.exception.ParamException;
import com.cbjprivilege.model.SysUser;
import com.cbjprivilege.param.UserParam;
import com.cbjprivilege.util.BeanValidator;
import com.cbjprivilege.util.IpUtil;
import com.cbjprivilege.util.MD5Util;
import com.cbjprivilege.util.PasswordUtil;
import com.google.common.base.Preconditions;

@Service
public class SysUserService {
	@Resource
	public SysUserMapper sysUserMapper;

	public void save(UserParam param) {
		BeanValidator.check(param);
		if (checkTelephoneExist(param.getTelephone(), param.getId())) {
			throw new ParamException("电话已被占用");
		}
		if (checkEmailExist(param.getMail(), param.getId())) {
			throw new ParamException("邮箱已被占用");
		}
		String password = PasswordUtil.randomPassword();
		// TODO:
		password = "12345678";
		String encryptedPassword = MD5Util.encrypt(password);
		SysUser user = SysUser.builder().username(param.getUsername())
				.telephone(param.getTelephone()).mail(param.getMail())
				.password(encryptedPassword).deptId(param.getDeptId())
				.status(param.getStatus()).remark(param.getRemark()).build();
		user.setOperator(RequestHolder.getCurrentUser().getUsername());
		user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
		user.setOperateTime(new Date());

		// TODO: sendEmail

		sysUserMapper.insertSelective(user);
	}

	public void update(UserParam param) {
		BeanValidator.check(param);
		if (checkTelephoneExist(param.getTelephone(), param.getId())) {
			throw new ParamException("电话已被占用");
		}
		if (checkEmailExist(param.getMail(), param.getId())) {
			throw new ParamException("邮箱已被占用");
		}
		SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
		Preconditions.checkNotNull(before, "待更新的用户不存在");
		SysUser after = SysUser.builder().id(param.getId())
				.username(param.getUsername()).telephone(param.getTelephone())
				.mail(param.getMail()).deptId(param.getDeptId())
				.status(param.getStatus()).remark(param.getRemark()).build();
		after.setOperator(RequestHolder.getCurrentUser().getUsername());
		after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
		after.setOperateTime(new Date());
		sysUserMapper.updateByPrimaryKeySelective(after);

	}

	private boolean checkEmailExist(String mail, Integer userId) {
		// TODO Auto-generated method stub
		return sysUserMapper.countByMail(mail, userId) > 0;
	}

	private boolean checkTelephoneExist(String telephone, Integer userId) {
		// TODO Auto-generated method stub
		return sysUserMapper.countByTelephone(telephone, userId) > 0;
	}

	public List<SysUser> getAll() {
		return sysUserMapper.getAll();
	}

	public SysUser findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return sysUserMapper.findByKeyword(keyword);
	}

	public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery) {
		// TODO Auto-generated method stub
		BeanValidator.check(pageQuery);
		int count = sysUserMapper.countByDeptId(deptId);
		if (count > 0) {
			List<SysUser> list = sysUserMapper.getPageByDeptId(deptId,
					pageQuery);
			return PageResult.<SysUser> builder().total(count).data(list).build();
		}
		return PageResult.<SysUser> builder().build();
	}

}
