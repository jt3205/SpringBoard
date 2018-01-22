package net.jtorol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jtorol.domain.UserVO;

@Controller
@RequestMapping("/test/")
public class TestController {
	@RequestMapping("a")
	public String doA() {
		System.out.println("A 호출");
		return "test";
	}
	
	@RequestMapping("getBean")
	public String getBean(Model model) {
		UserVO temp = new UserVO();
		temp.setUid("jt3205");
		temp.setUpw("1234");
		temp.setUname("박형진");
		
		model.addAttribute("user", temp);
		return "getBean";
	}
}
