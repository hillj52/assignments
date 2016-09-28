package com.ssa.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages={"com.ssa"})
public class WebsiteController {

	private HashMap<Integer,String> helpTopics;
	
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
	
	@RequestMapping("/help")
	public ModelAndView help(HttpServletRequest request, ModelAndView mv) {
		if(request.getParameter("id") != null) {
			mv.addObject("id", request.getParameter("id"));
			mv.addObject("description", this.getHelpTopic(Integer.parseInt(request.getParameter("id"))));
		}
		mv.setViewName("help");
		return mv;
	}
	
	private String getHelpTopic(int id) {
		return this.helpTopics.get(id);
	}
	
	public WebsiteController() {
		this.helpTopics = new HashMap<Integer,String>();
		this.helpTopics.put(1,"I know the Bengels need help, but there is nothing I can do");
		this.helpTopics.put(2,"Serioulsy the Bengals are beyond help");
		this.helpTopics.put(3,"I know Andy Dalton threw another pick, that is normal");
		this.helpTopics.put(4,"The Ravens will always be better than the Bengals, that's the way it is");
		this.helpTopics.put(5,"Vontaze Burfict will never stop commiting personal fouls, stop asking");
	}
}
