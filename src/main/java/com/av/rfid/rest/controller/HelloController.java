package com.av.rfid.rest.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.av.rfid.data.entity.PasswordResetToken;
import com.av.rfid.data.entity.User;
import com.av.rfid.data.repo.UserRepo;
import com.av.rfid.service.UserService;

@Controller
public class HelloController {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailSender mailSender;
	
	@RequestMapping("/")
	public String index() {
		return "home";
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse resetPassword(
	  HttpServletRequest request, @RequestParam("email") String userEmail) {
	     
	    User user = userRepo.findByEmail(userEmail);
	    if (user == null) {
	        throw new UsernameNotFoundException("User with given Email not found");
	    }
	 
	    String token = UUID.randomUUID().toString();
	    userService.createPasswordResetTokenForUser(user, token);
	    String appUrl = 
	      "http://" + request.getServerName() + 
	      ":" + request.getServerPort() + 
	      request.getContextPath();
	    SimpleMailMessage email = 
	      constructResetTokenEmail(appUrl, request.getLocale(), token, user);
	    mailSender.send(email);
	 
	    return new GenericResponse("reset password email");
	     // messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
	}
	
	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public String showChangePasswordPage(
	  Locale locale, Model model, @RequestParam("id") long id, @RequestParam("token") String token) {
	     
	    PasswordResetToken passToken = userService.getPasswordResetToken(token);
	    User user = passToken.getUser();
	    if (passToken == null || user.getId() != id) {
	        String message = "Invalid token";//messages.getMessage("auth.message.invalidToken", null, locale);
	        model.addAttribute("message", message);
	        return "redirect:/login.html?lang=" + locale.getLanguage();
	    }
	 
	    Calendar cal = Calendar.getInstance();
	    if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	        model.addAttribute("message", 
	        		"Expired");//messages.getMessage("auth.message.expired", null, locale));
	        return "redirect:/login.html?lang=" + locale.getLanguage();
	    }
	 
	    Authentication auth = new UsernamePasswordAuthenticationToken(
	      user, null, userDetailsService.loadUserByUsername(user.getEmail()).getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(auth);
	 
	    return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
	}
	@RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
	@PreAuthorize("hasRole('READ_PRIVILEGE')")
	@ResponseBody
	public GenericResponse savePassword(Locale locale, @RequestParam("password") String password) {
	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    userService.changeUserPassword(user, password);
	    return new GenericResponse("reset success");
	    //messages.getMessage("message.resetPasswordSuc", null, locale));
	}
	
	private SimpleMailMessage constructResetTokenEmail(
			  String contextPath, Locale locale, String token, User user) {
			    String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
			    String message = "Reset Password";//messages.getMessage("message.resetPassword", null, locale);
			    SimpleMailMessage email = new SimpleMailMessage();
			    email.setTo(user.getEmail());
			    email.setSubject("Reset Password");
			    email.setText(message + " rn" + url);
			    email.setFrom("abc@abc.com");//env.getProperty("support.email"));
			    return email;
			}
}
