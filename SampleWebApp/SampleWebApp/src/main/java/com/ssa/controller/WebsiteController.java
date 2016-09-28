package com.ssa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages={"com.ssa"})
public class WebsiteController {

	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request, ModelAndView mv) {
		if(request.getParameter("name") != null) {
			mv.addObject("name", request.getParameter("name"));
		}
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("/about")
	public ModelAndView about(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName("about");
		return mv;
	}
	
}
