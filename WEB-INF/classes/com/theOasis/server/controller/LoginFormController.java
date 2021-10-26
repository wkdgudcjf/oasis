package com.theOasis.server.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.MemberController;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.TheOasisMember;
import com.theOasis.member.User;

@Controller
@SessionAttributes("loginUser")
public class LoginFormController {
	private Validator loginValidator;
	public void setLoginValidator(Validator loginValidator) {
		this.loginValidator = loginValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String toLoginView(HttpSession session) {
		if(session.getAttribute("loginUser")!=null)
			return "mainPage";
		else
			return "login";
	}

	@ModelAttribute
	public User setUpForm() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(User user, BindingResult bindingResult) {
		//System.out.println(user);
		
		//if(session.getAttribute("user")!=null)
		this.loginValidator.validate(user, bindingResult);
		
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		
		if(MemberController.getInstance().check(user.getId(), user.getPassword()))
		{
			modelAndView.setViewName("mainPage");
			TheOasisMember loginUser = (TheOasisMember)MemberController.getInstance().getManager().search(MemberInfo.ID, user.getId()).get(0);
			modelAndView.addObject("loginUser", loginUser);
			
			//System.out.println(session.getAttribute("user"));
			return modelAndView;
		}else
		{
			bindingResult.reject("error.login.user");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}
}
