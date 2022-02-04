package com.example.demo.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex03")
public class Exam03Controller {
	@Autowired
	private ServletContext application;

	@RequestMapping("")
	public String index() {
		return "exam03";
	}

	@RequestMapping("/result")
	public String result(int item1, int item2, int item3) {
		int totalPrice = item1 + item2 + item3;
		int taxIncludeTotalPrice = (int)(totalPrice * 1.1);
		
		application.setAttribute("totalPrice", totalPrice);
		application.setAttribute("taxIncludeTotalPrice", taxIncludeTotalPrice);
		
		return "exam03-result";
	}
}
