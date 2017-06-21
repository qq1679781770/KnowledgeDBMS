package com.jsxnh.kbms.handler;

import java.util.Date;
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

import com.jsxnh.kbms.dao.AttributeDao;
import com.jsxnh.kbms.entities.Attribute;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Domain;
import com.jsxnh.kbms.service.FindAuthorityService;

@Controller
public class AttributeHandler {

	@Autowired
	private AttributeDao attributeDao;
	@Autowired
	private FindAuthorityService findAuthorityService;
	
	@RequestMapping(value="/findattributes",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findAttributes(){
		JSONArray json=new JSONArray();
		List<Attribute> attributes=attributeDao.listAllAttribute();
		for(Attribute attribute:attributes){
			json.put(attribute.getAttribute());
		}
		return json.toString();
	}
	
	@RequestMapping("/kbms/showaddattribute")
	public ModelAndView showAddattribute(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("添加属性")){
				model=new ModelAndView("addattribute");				
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/addattributes",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addAttribute(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		Attribute attribute=new Attribute();
		attribute.setAttribute(json.getString("attribute"));
		attribute.setCreate_id(json.getInt("create_id"));
		attribute.setLast_time(new Date());
		attributeDao.addAttribute(attribute);
		return new JSONArray().toString();
	}
	
	@RequestMapping(value="/findattributeexist")
	public @ResponseBody String findAttributeExist(@RequestParam("attribute") String attribute){
		JSONArray json=new JSONArray();
		if(attributeDao.findAttributeByattribute(attribute)==null){
			json.put("N");
		}else{
			json.put("Y");
		}
		return json.toString();
		
	}
}
