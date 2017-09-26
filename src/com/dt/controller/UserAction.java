package com.dt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dt.entity.User;
import com.dt.service.UserService;
import com.dt.util.Paging;
/**
 * 用户管理action
 * @author wys
 *
 */
@Controller  
@RequestMapping("/user")  
public class UserAction {

	@Autowired
	private UserService userService;
	private Integer pageSize=10;
	@RequestMapping(value="save")
	public String save(ModelMap modelMap,HttpServletRequest request,User user){
		userService.saveOrUpdate(user);
		return "redirect:/user/index.html";
	}
	@RequestMapping(value="index")
	public String list(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(value = "curPage",required=false,defaultValue="0") Integer curPage,
			@RequestParam(value = "pageSize",required=false,defaultValue="10") Integer pageSize){
		Paging paging=userService.getList(curPage, pageSize);
		modelMap.put("paging",paging);
		modelMap.put("pageSize",pageSize);
		return "user/index";
	}
	@RequestMapping(value="del")
	public String del(ModelMap modelMap,HttpServletRequest request,User user){
		userService.del(user.getId());
		return "redirect:/user/index.html";
	}
	
}
