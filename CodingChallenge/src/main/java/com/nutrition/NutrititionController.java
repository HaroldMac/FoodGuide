package com.nutrition;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NutrititionController {
	
	@RequestMapping("/recommendation")
	public ModelAndView add(@RequestParam("gender")String gender, @RequestParam("userAge") int age, @RequestParam("language") String language){
		MyFoodGuide fd = new MyFoodGuide(gender, age, language);
		String recommendation = fd.webPrint();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("recommendation.jsp");
		mv.addObject("rec", recommendation);
		return mv;
	}

	

}
