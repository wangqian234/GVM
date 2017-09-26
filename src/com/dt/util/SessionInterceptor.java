package com.dt.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter   {
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		String uri = request.getRequestURI();  
//		if (uri.indexOf("sysUser/login.html")<0) {  
//			HttpSession sesson = request.getSession();
//			if (sesson.getAttribute("systemUser") == null) {
//				String path = request.getContextPath();
//				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//				response.sendRedirect(basePath);  //返回登陆页  
//				return false;
//			}
//		}
//		return super.preHandle(request, response, handler);  
//	}
//
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		//request.getRequestDispatcher("../index.jsp").forward(request, response);
//	}
//
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		//request.getRequestDispatcher("../index.jsp").forward(request, response);
//	}
}
