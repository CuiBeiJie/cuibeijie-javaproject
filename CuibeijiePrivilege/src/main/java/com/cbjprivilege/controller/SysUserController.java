package com.cbjprivilege.controller;

import javax.annotation.Resource;






import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbjprivilege.beans.PageQuery;
import com.cbjprivilege.beans.PageResult;
import com.cbjprivilege.common.JsonData;
import com.cbjprivilege.model.SysUser;
import com.cbjprivilege.param.UserParam;
import com.cbjprivilege.service.SysTreeService;
import com.cbjprivilege.service.SysUserService;

@Controller
@RequestMapping("/sys/user")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysTreeService sysTreeService;

	@RequestMapping("/save.json")
	@ResponseBody
	public JsonData saveUser(UserParam param) {
		sysUserService.save(param);
		return JsonData.success();
	}

	@RequestMapping("/update.json")
	@ResponseBody
	public JsonData updateUser(UserParam param) {
		sysUserService.update(param);
		return JsonData.success();
	}
	
	@RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }

}
