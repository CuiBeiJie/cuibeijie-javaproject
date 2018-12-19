package com.cbjprivilege.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cbjprivilege.exception.ParamException;
import com.cbjprivilege.exception.PermissionException;

public class SpringExceptionResolver implements HandlerExceptionResolver {
	
	private static Logger log = LoggerFactory
			.getLogger(SpringExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		// TODO Auto-generated method stub
		String url = request.getRequestURL().toString();
		ModelAndView mv;
		String defaultMsg = "System error";
		// 项目中请求json数据,使用.json结尾
		if (url.endsWith(".json")) {
			if (exception instanceof PermissionException || exception instanceof ParamException ) {
				JsonData result = JsonData.fail(exception.getMessage());
				mv = new ModelAndView("jsonView", result.toMap());
			} else {
				log.error("unknow json exception,  url:" + url, exception);
				JsonData result = JsonData.fail(defaultMsg);
				mv = new ModelAndView("jsonView", result.toMap());
			}
		} else if (url.endsWith(".page")) {// 项目中请求page界面,使用.page结尾
			log.error("unknow page exception,  url:" + url, exception);
			JsonData result = JsonData.fail(defaultMsg);
			mv = new ModelAndView("exception", result.toMap());
		} else {
			log.error("unknow  exception,  url:" + url, exception);
			JsonData result = JsonData.fail(defaultMsg);
			mv = new ModelAndView("jsonView", result.toMap());
		}
		return mv;
	}

}
