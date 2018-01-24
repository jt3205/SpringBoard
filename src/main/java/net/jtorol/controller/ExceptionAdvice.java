package net.jtorol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/errorPage");
		mav.addObject("title", "에러가 발생했습니다.");
		mav.addObject("msg", "메시지 : " + e.getMessage());
		return mav;
	}
}
