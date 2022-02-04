package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;

@Controller
@RequestMapping("/ex04")
public class Exam04Controller {
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		return "exam04";
	}

	@RequestMapping("/result")
	public String result(
			@Validated UserForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		//エラーチェック処理
		if(result.hasErrors()) {
			return index(model);
		}
		
		User user = new User();
		
		//formからuserオブジェクトにプロパティ値をコピー
		BeanUtils.copyProperties(form, user);
		user.setAge(form.getIntAge());
		
		//model.addAttribute("user",user);
		redirectAttributes.addFlashAttribute("user",user);
		
		return "redirect:/ex04/toresult";
	}
	
	@RequestMapping("/toresult")
	public String toresult() {
		return "exam04-result";
	}

}
