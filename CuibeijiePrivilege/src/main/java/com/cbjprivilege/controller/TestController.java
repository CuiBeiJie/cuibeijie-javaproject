package com.cbjprivilege.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbjprivilege.common.JsonData;
import com.cbjprivilege.exception.PermissionException;
import com.cbjprivilege.param.TestVo;
import com.cbjprivilege.util.BeanValidator;

@Controller
@RequestMapping("/test")
public class TestController {
	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		log.info("hello");
		return "hello persion";
	}

	@RequestMapping("/helloJsonData.json")
	@ResponseBody
	public JsonData helloJsonData() {
		log.info("helloJsondata");
		throw new PermissionException("test exception");
		// return JsonData.success("Hello Permission");
	}

	@RequestMapping("/validate.json")
	@ResponseBody
	public JsonData validate(TestVo vo) {
		log.info("validate");
		try {
			Map<String, String> map = BeanValidator.validateObject(vo);
			if (map != null && map.entrySet().size() > 0) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					log.info("{}->{}", entry.getKey(), entry.getValue());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return JsonData.success("test validate");

	}
}
