package com.jsxnh.kbms.handler;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
public class AuthorityHandler {

//	@Autowired
//	CustomerDao customerDao;
	@Autowired
	AuthorityDao authorityDao;
	@Autowired
	SauthorityDao sauthorityDao;
//	@Autowired
//	AtgDao atgDao;
	@Autowired
	FindAuthorityService findAuthorityService;
	
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
	
	@RequestMapping(value="/kbms/finddeleteuserauthority",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findDeleteauthority(@RequestParam("user_id") Integer user_id){
		List<Authority> authorities=findAuthorityService.findAuthority(user_id);
		JSONArray json=new JSONArray();
		for(Authority authority:authorities){
			json.put(authority.getAuthority());
		}
		return json.toString();
	}
	

	@RequestMapping(value="/kbms/findadduserauthority",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findAddauthority(@RequestParam("user_id") Integer user_id){
		List<Authority> authorities=findAuthorityService.findAuthority(user_id);
		List<Authority> allauthorities=authorityDao.listAllAuthority();
		List<String> strs=new LinkedList<>();
		for(Authority authority:allauthorities){
			strs.add(authority.getAuthority());
		}
		for(Authority authority:authorities){
			if(strs.contains(authority.getAuthority())){
				strs.remove(authority.getAuthority());
			}
		}
		
		JSONArray json=new JSONArray();
		for(String str:strs){
			json.put(str);
		}
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/adduserauthority",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addAuthority(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		Sauthority  sauthority=new Sauthority();
		sauthority.setIs_grant(1);
		sauthority.setCreate_id(json.getInt("create_id"));
		sauthority.setLast_time(new Date());
		sauthority.setAuthority_id(authorityDao.findAuthorityByauthority(json.getString("authority")).getId());
		sauthority.setUser_id(json.getInt("user_id"));
		if(sauthorityDao.findSauthorityByuserandauthority(sauthority.getUser_id(), sauthority.getAuthority_id())==null){
			sauthorityDao.addSauthority(sauthority);	
		}else{
			sauthorityDao.updateSauthority(sauthority.getUser_id(), sauthority.getAuthority_id(), 1);
		}			
		return new JSONArray().toString();
	}
	
	
	@RequestMapping(value="/kbms/deleteuserauthority",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String deleteAuthority(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		Sauthority  sauthority=new Sauthority();
		sauthority.setIs_grant(0);
		sauthority.setCreate_id(json.getInt("create_id"));
		sauthority.setLast_time(new Date());
		sauthority.setAuthority_id(authorityDao.findAuthorityByauthority(json.getString("authority")).getId());
		sauthority.setUser_id(json.getInt("user_id"));
		if(sauthorityDao.findSauthorityByuserandauthority(sauthority.getUser_id(), sauthority.getAuthority_id())==null){
			sauthorityDao.addSauthority(sauthority);	
		}else{
			sauthorityDao.updateSauthority(sauthority.getUser_id(), sauthority.getAuthority_id(), 0);
		}			
		return new JSONArray().toString();
	}
}
