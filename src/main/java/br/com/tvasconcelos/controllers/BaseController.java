package br.com.tvasconcelos.controllers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tvasconcelos.entities.User;
import br.com.tvasconcelos.utils.HibernateUtil;
import br.com.tvasconcelos.validations.UserValidation;
 
@Controller
@RequestMapping("/")
public class BaseController {
 
	@RequestMapping(value="welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
 
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
 
	}
 
	@RequestMapping(value="welcome/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - " + name);
		return "index";
 
	}
	
	@RequestMapping(value="contact", method = RequestMethod.GET)
	public String contact(ModelMap model) {
		return "contact";
	}
	
	@RequestMapping(value="contact", method = RequestMethod.POST)
	public void contactPost(@ModelAttribute("user") User user, ModelMap model) {
		BindException exception = new BindException(user, "user");
		UserValidation validation = new UserValidation();
		validation.validate(user, exception);
		
		if (exception.hasErrors()) {
			List<FieldError> errors = (List<FieldError>) exception.getFieldErrors();
			model.addAttribute("errors", errors);
		}else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = null;
			Integer userId = null;
			
			try {
				transaction = session.beginTransaction();
				userId = (Integer) session.save(user);
				transaction.commit();
			} catch (HibernateException e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
 
}
