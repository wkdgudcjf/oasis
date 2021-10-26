import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.theOasis.controller.MemberController;
import com.theOasis.member.MemberInfo;
import com.theOasis.member.User;

@Controller
@SessionAttributes("user")
public class LoginFormController {
	private Validator loginValidator;
	@Resource

	public void setLoginValidator(Validator loginValidator) {
		this.loginValidator = loginValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String toLoginView() {
		return "login";
	}

	@ModelAttribute
	public User setUpForm() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.POST) 
	public ModelAndView onSubmit(User user, BindingResult bindingResult) {
		
		this.loginValidator.validate(user, bindingResult);
		 
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		
		if(MemberController.getInstance().check(user.getId(), user.getPassword()))
		{
			modelAndView.setViewName("loginSuccess");
			modelAndView.addObject("loginUser", MemberController.getInstance().getManager().search(MemberInfo.ID, user.getId()).get(0));
			
			return modelAndView;
		}else
		{
			bindingResult.reject("error.login.user");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}
}
