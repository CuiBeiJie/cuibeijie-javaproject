package com.cbjprivilege.dto;

import org.springframework.beans.BeanUtils;

import com.cbjprivilege.model.SysAcl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AclDto extends SysAcl{
	//是否默认要选中
    private boolean checked = false;
    //是否有权限操作
    private boolean hasAcl = false;
    
    public static AclDto adapt(SysAcl acl){
    	AclDto dto = new AclDto();
    	BeanUtils.copyProperties(acl, dto);
    	return dto;
    }
    
}
