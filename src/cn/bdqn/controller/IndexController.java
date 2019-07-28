package cn.bdqn.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import cn.bdqn.pojo.User;
import cn.bdqn.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	//显示上传
	@RequestMapping("/upload.html")
	public String showUpload(){
		return "upload";
	}
	//处理上传
	@RequestMapping(value="/doUpload")
	public String doUpload(
			@RequestParam("userName") String userName,
			MultipartFile mypic,
			Model model
			){
		System.out.println("上传人："+userName+"，原文件名是:"+mypic.getOriginalFilename());
		File fileto=new File("F:\\upload\\a.jpg");
		try {
			mypic.transferTo(fileto);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("userName", userName);
		
		return "uploadSuccess";
	}
	
	//显示登录
	@RequestMapping("/login.html")
	public String showLogin(){
		return "login";
	}
	//处理登录
	@RequestMapping("/login.do")
	public String doLogin(
			@RequestParam("userCode")
			String userCode,
			@RequestParam("userPassword")
			String userPassword,
			Model model,HttpSession session
			){
		//登陆成功返回一个user对象
		User userLogin=userService.findBylogin(userCode,userPassword);
		if(userLogin!=null){
			//int a=5/0;
			session.setAttribute("userSession",userLogin );
			//return默认的return都是转发,加上关键字redirect 改成重定向
			return "redirect:/main.html";
		}else{
			model.addAttribute("error", "对不起,密码或登录名有误，登录失败");
			//默认的return都是转发
			return "login";
		}
		
	}
	@RequestMapping("/main.html")
	public String showMain(){
		return "frame";
	}
	//退出
	@RequestMapping("/logout.do")
	public String logOut(HttpSession session){
		session.invalidate();
		return "redirect:/login.html";
	}
	
/*	//捕获异常
	@ExceptionHandler(value={RuntimeException.class})
	public String handlerException(Exception e,HttpServletRequest request){
		request.setAttribute("msg", e.getMessage());
		return "error";
	}*/
}
