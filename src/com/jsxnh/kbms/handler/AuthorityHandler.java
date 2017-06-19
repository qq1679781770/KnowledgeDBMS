package com.jsxnh.kbms.handler;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jsxnh.kbms.dao.AtgDao;
import com.jsxnh.kbms.dao.AuthorityDao;
import com.jsxnh.kbms.dao.CustomerDao;
import com.jsxnh.kbms.dao.SauthorityDao;
import com.jsxnh.kbms.entities.Atg;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Customer;
import com.jsxnh.kbms.entities.Sauthority;
import com.jsxnh.kbms.service.FindAuthorityService;

//@Controller
public class AuthorityHandler {

//	@Autowired
//	CustomerDao customerDao;
//	@Autowired
//	AuthorityDao authorityDao;
//	@Autowired
//	SauthorityDao sauthorityDao;
//	@Autowired
//	AtgDao atgDao;
//	@Autowired
//	FindAuthorityService findAuthorityService;
	
//	@RequestMapping(value="/kbms/showaddauthority",method=RequestMethod.GET)
//	public ModelAndView showAddauthority(HttpServletRequest request){
//		ModelAndView model;
//		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
//		for(Authority authority:authorities){
//			if(authority.getAuthority().equals("赋予权限")){
//				
//			}
//		}
//		return model;
//	}
}
