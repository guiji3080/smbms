package cn.bdqn.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.Role;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.bdqn.pojo.User;
import cn.bdqn.service.RoleService;
import cn.bdqn.service.UserService;
import cn.bdqn.util.PageBean;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/showCount.html")
	public String showCount(Model model) {
		Integer count = userService.getToTalCount();
		model.addAttribute("count", count);
		return "showCount";
	}

	@RequestMapping("/showAll.html")
	public String showAll(Model model) {
		List<User> userList = userService.findAll();
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		model.addAttribute("userList", userList);
		return "userlist";
	}
	//删除
	@ResponseBody
	@RequestMapping("/userDel/{uid}")
	public Object userDel(@PathVariable Integer uid){
		Map<String, Object> map=new HashMap<String, Object>();
		User user=userService.findById(uid);
		if(user==null){
			map.put("delResult", "notexist");
		
		}else{
			int ret=userService.delUserById(uid);
			if(ret>0){
				map.put("delResult", "true");
			}else{
				map.put("delResult", "false");
			}	
		}
		return map;
	}
	//ajax查看用户
	@ResponseBody
	@RequestMapping(value = "/view/{uid}")
	public User viewUser(@PathVariable Integer uid){
		User user =userService.findById(uid);
		return user;	
	}
	
	//拦截修改密码请求
	@RequestMapping("/pwdmodify")
	public String showPwdModify(){
		return "pwdmodify";
	}
	//ajax判断旧密码,返回fastjson对象
	@ResponseBody
	@RequestMapping("/chekOldPwd")
	public Object chekOldPwd(@RequestParam("oldpwd") String oldpwd,
			HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		User UserLogin=(User) session.getAttribute("userSession");
		if(oldpwd==""){
			map.put("result", "error");
		}else if(UserLogin==null){
			map.put("result", "sessionerror");
		}else if(UserLogin.getUserPassword().equals(oldpwd)){
			map.put("result", "true");
		}else{
			map.put("result", "false");
		}
		return map;
	}
	//处理修改密码
	
	@RequestMapping(value="/doPwdModify",method=RequestMethod.POST)
	public String doPwdModify(
			User user,
			@RequestParam("newpassword") String newpassword,
			HttpSession session,Model model
			){
		User UserLogin=(User) session.getAttribute("userSession");
		user.setUserPassword(newpassword);
		user.setId(UserLogin.getId());
		int ret=userService.updateUser(user);
		if(ret>0){
			model.addAttribute("message", "密码修改成功，请重新登录");
			return "login";
			
		}else{
			return "pwdmodify";
		}
		
	}
	//提交修改
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String doModify(User user,HttpSession session){
		User UserLogin=(User) session.getAttribute("userSession");
		user.setModifyBy(UserLogin.getId());
		user.setModifyDate(new Date());
		int ret=userService.updateUser(user);
		if(ret>0){
			return "success";
		}else{
			return "usermodify";
		}
		
	}
	
	//处理修改请求
	@RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
	public String showModify(@PathVariable Integer id,Model model){
	User user=userService.findById(id);
	model.addAttribute("user", user);
		return "usermodify";
	}
	
	//处理新增
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(User user){
		int ret=userService.addUser(user);
		if(ret>0){
			return "success";
		}else{
			return "useradd";
		}
	
	}
	//拦截新增请求
	@RequestMapping(value="/useradd.html",method=RequestMethod.GET)
	public String showAdd(){
		return "useradd";
	}
	
	//拦截关于用户角色的ajax请求
	@ResponseBody
	@RequestMapping(value="getrolelist")
	//@RequestMapping(value="getrolelist",produces={"application/json;charset=UTF-8"})
	//解决json数据传递中的乱码问题
/*	public String getrolelist(){
		String jsonStr=null;
		List<Role> roleList=roleService.findAll();
		jsonStr=JSON.toJSONStringWithDateFormat(roleList, "yyyy-MM-dd");
		System.out.println(jsonStr);
		return jsonStr;
	}*/
	public List<Role> getrolelist(){
		List<Role> roleList=roleService.findAll();
		return roleList;
	}
	
	//拦截校验usercode是否存在的请求
	@ResponseBody
	@RequestMapping("/chekusercode")
	public Object chekUserCode(@RequestParam("userCode") String userCode){
		//String jsonStr=null;
		Map<String, Object> map=new HashMap<String, Object>();
		boolean isExist=userService.chekUserCode(userCode);
		if(isExist){
			map.put("userCode", "exist");
		}else{
			map.put("userCode", "notexist");
		}
		//jsonStr=JSON.toJSONString(map);
		//	System.out.println(jsonStr);
		return map;
	}
	
	//分页显示
	@RequestMapping("/findByPage")
	public String findByPage(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "queryname", required = false, defaultValue = "") String queryname,
			@RequestParam(value = "queryUserRole", required = false, defaultValue = "0") Integer queryUserRole,
			Model model) {
		if (queryname != null && !queryname.equals("")) {
			try {
				queryname = new String(queryname.getBytes("ISO-8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int pageSize =2;
		PageBean<User> userPage = userService.findByPage(pageNo, pageSize,
				queryname, queryUserRole);
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		model.addAttribute("userPage", userPage);
		model.addAttribute("queryUserName", queryname);
		model.addAttribute("queryUserRole", queryUserRole);
		return "userlist";
	}
}
