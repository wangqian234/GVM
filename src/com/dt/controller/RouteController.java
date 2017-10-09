package com.dt.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 路由跳转相关
 * 
 * @author
 * @date 2017年8月9日
 */
@Controller
@RequestMapping("/routeController")
public class RouteController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     
    @RequestMapping("/toIndexPage.do")
 	public String toLoginPage() {
 		return "0index/index";
 	}
    
    @RequestMapping("/toTestPage.do")
    public String toTestPage() {
 		return "1baseInfo/index";
 	}
    @RequestMapping("/toOperaPage.do")
    public String toOperaPage(){
    	return "2operaState/index";
    }
    @RequestMapping("/toErrorPage.do")
    public String toErrorPage(){
    	return "3errorState/index";
    }
    @RequestMapping("/toPrePage.do")
    public String toPrePage(){
    	return "4preMain/index";
    }
    @RequestMapping("/toEvalPage.do")
    public String toEvalPage(){
    	return "5evalState/index";
    }
}
