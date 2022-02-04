package com.example.demo.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;

@Controller
@RequestMapping("/ex06")
public class ShoppingCartController {
	@Autowired
	private ServletContext application;
	private HttpSession session;

	@RequestMapping("")
	public String index(Model model) {
		List<Item> itemList = new LinkedList<>();
		itemList = addItem(itemList, "手帳ノート", 1000);
		itemList = addItem(itemList, "文房具セット", 1500);
		itemList = addItem(itemList, "ファイル", 2000);
		application.setAttribute("itemList", itemList);
		
		
		if(session.getAttribute("cartList")==null) {
			List<Item> cartList = new LinkedList<>();
			session.setAttribute("cartList", cartList);
		}else {
			List<Item> cartList = (LinkedList)session.getAttribute("cartList");
			int totalPrice = 0;
			for (Item item : cartList) {
				totalPrice += item.getPrice();
			}
			model.addAttribute("totalPrice",totalPrice);
		}

		return "item-and-cart";
	}
	
	@RequestMapping("/add")
	public void inCart(int indexNum,Model model) {
		List<Item> itemList = (LinkedList)application.getAttribute("itemList");
		List<Item> cartList = (LinkedList)session.getAttribute("cartList");
		Item item = itemList.get(indexNum);
		cartList.add(item);
		
		session.setAttribute("cartList",cartList);
		index(model);
	}
	
	@RequestMapping("/delete")
	public void delete(int indexNum,Model model) {
		List<Item> cartList = (LinkedList)session.getAttribute("cartList");
		cartList.remove(indexNum);
		
		session.setAttribute("cartList",cartList);
		index(model);
	}

	public List<Item> addItem(List<Item> itemList, String name, int price) {
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);

		itemList.add(item);
		return itemList;
	}
}
