package com.cbjprivilege.controller;

import java.util.List;
import java.util.Map;

import com.cbjprivilege.common.JsonData;
import com.cbjprivilege.model.SysUser;
import com.cbjprivilege.param.RoleParam;
import com.cbjprivilege.service.SysRoleAclService;
import com.cbjprivilege.service.SysRoleService;
import com.cbjprivilege.service.SysRoleUserService;
import com.cbjprivilege.service.SysTreeService;
import com.cbjprivilege.service.SysUserService;
import com.cbjprivilege.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysTreeService sysTreeService;
	@Resource
	private SysRoleAclService sysRoleAclService;
	@Resource
	private SysRoleUserService sysRoleUserService;
	@Resource
	private SysUserService sysUserService;

	@RequestMapping("role.page")
	public ModelAndView page() {
		return new ModelAndView("role");
	}

	@RequestMapping("/save.json")
	@ResponseBody
	public JsonData saveRole(RoleParam param) {
		sysRoleService.save(param);
		return JsonData.success();
	}

	@RequestMapping("/update.json")
	@ResponseBody
	public JsonData updateRole(RoleParam param) {
		sysRoleService.update(param);
		return JsonData.success();
	}

	@RequestMapping("/list.json")
	@ResponseBody
	public JsonData list() {
		return JsonData.success(sysRoleService.getAll());
	}

	@RequestMapping("/roleTree.json")
	@ResponseBody
	public JsonData roleTree(@RequestParam("roleId") int roleId) {
		
		return JsonData.success(sysTreeService.roleTree(roleId));
	}
	
	@RequestMapping("/changeAcls.json")
	@ResponseBody
	public JsonData changeAcls(@RequestParam("roleId") int roleId,@RequestParam("aclIds") String aclIds) {
		List<Integer> aclIdList =StringUtil.splitToListInt(aclIds);
		sysRoleAclService.changeRoleAcls(roleId, aclIdList);
		return JsonData.success(sysTreeService.roleTree(roleId));
	}
    
	@RequestMapping("/users.json")
	@ResponseBody
	public JsonData users(@RequestParam("roleId") int roleId) {
		List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUser> allUserList = sysUserService.getAll();
        List<SysUser> unselectedUserList = Lists.newArrayList();
        for(SysUser sysUser : allUserList){
        	if(sysUser.getStatus() == 1 && !(selectedUserList.contains(sysUser))){
        		unselectedUserList.add(sysUser);
        	}
        }
        Map<String,List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected", unselectedUserList);
		return JsonData.success(map);
	}
	
	@RequestMapping("/changeUsers.json")
	@ResponseBody
	public JsonData changeUsers(@RequestParam("roleId") int roleId,@RequestParam("userIds") String userIds) {
		List<Integer> userIdList =StringUtil.splitToListInt(userIds);
		sysRoleUserService.changeRoleUsers(roleId, userIdList);
		return JsonData.success();
	}
}
