package com.jsxnh.kbms.handler;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsxnh.kbms.dao.AttributeDao;
import com.jsxnh.kbms.entities.Attribute;
import com.jsxnh.kbms.entities.Domain;

@Controller
public class AttributeHandler {

	@Autowired
	private AttributeDao attributeDao;
	
	@RequestMapping(value="/findattributes",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findAttributes(){
		JSONArray json=new JSONArray();
		List<Attribute> attributes=attributeDao.listAllAttribute();
		for(Attribute attribute:attributes){
			json.put(attribute.getAttribute());
		}
		return json.toString();
	}
}
