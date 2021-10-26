package com.theOasis.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.theOasis.controller.MemberController;
import com.theOasis.member.User;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		User user = (User)command;
		if(!StringUtils.hasLength(user.getId())){
			errors.rejectValue("id", "error.required");
		}
		else if(!StringUtils.hasLength(user.getPassword())){
			errors.rejectValue("password", "error.required");
		}
		else if(!MemberController.getInstance().check(user.getId()))
		{
			errors.rejectValue("id", "error.login");
		}
		else if(!MemberController.getInstance().check(user.getId(),user.getPassword()))
		{
			errors.rejectValue("password", "error.login");
		}
		
		
		if(errors.hasErrors()){
			errors.reject("error.input.user");
		}
	}

}
