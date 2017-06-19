package com.jsxnh.kbms.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("拦截器");
		String reqUrl=arg0.getRequestURI().replace(arg0.getContextPath(), "");  
		if(reqUrl.equals("/kbms/register")||reqUrl.equals("/kbms/login")){
			return true;
		}else{
			if(arg0.getSession().getAttribute("user_id")==null||arg0.getSession().getAttribute("user_id").equals("")){
				arg1.sendRedirect(arg0.getContextPath()+"/login");
			}
		}
		return true;
	}

}
