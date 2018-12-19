package com.cbjprivilege.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cbjprivilege.common.RequestHolder;
import com.cbjprivilege.model.SysUser;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String servletPath = request.getServletPath();
		SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
		if(sysUser == null){
			String path ="/CuibeijiePrivilege/signin.jsp";
			response.sendRedirect(path);
			return;
		}
		RequestHolder.add(sysUser);
		RequestHolder.add(request);
		chain.doFilter(servletRequest, servletResponse);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
