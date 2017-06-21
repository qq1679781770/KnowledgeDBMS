package com.jsxnh.kbms.handler;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsxnh.kbms.dao.AttributeDao;
import com.jsxnh.kbms.dao.RattributeDao;
import com.jsxnh.kbms.entities.Attribute;
import com.jsxnh.kbms.entities.Rattribute;

@Controller
public class RattributeHandler {

	@Autowired
	private AttributeDao attributeDao;
	@Autowired
	private RattributeDao rattributeDao;
	
	@RequestMapping(value="/kbms/findaddattributes",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findAddattributes(@RequestParam("resource_id") Integer resource_id){
		JSONArray res=new JSONArray();
		List<Attribute> attributes=attributeDao.listAllAttribute();
		List<Rattribute> rattributes=rattributeDao.findRattributeByResource(resource_id);
		List<Integer> allattributes=new LinkedList<Integer>();
		for(Attribute attribute:attributes){
			allattributes.add(attribute.getId());
		}
		for(Rattribute attribute:rattributes){
			allattributes.remove(attribute.getAttribute_id());
		}
		for(Integer id:allattributes){
			res.put(attributeDao.findAttributebyId(id).get(0).getAttribute());
		}
		return res.toString();		
	}
	
	@RequestMapping(value="/kbms/updateattributevalue",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String updateAttributeValue(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		rattributeDao.updateValue(json.getString("value"), json.getInt("create_id"), json.getInt("id"));
		return new JSONArray().toString();
	}
	
	@RequestMapping(value="/kbms/addrattribute",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addRattribute(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		Rattribute rattribute=new Rattribute();
		rattribute.setAttribute_id(attributeDao.findAttributeByattribute(json.getString("attribute")).getId());
		rattribute.setLast_time(new Date());
		rattribute.setResource_id(json.getInt("resource_id"));
		rattribute.setCreate_id(json.getInt("create_id"));
		rattribute.setValue(json.getString("value"));
		rattributeDao.addRattribute(rattribute);		
		return new JSONArray().toString();
	}
	
	@RequestMapping("/kbms/deleterattribute/{id}")
	public @ResponseBody String deleteAttribute(@PathVariable("id") Integer id){
		rattributeDao.deleteRattribute(id);
		return new JSONArray().toString();
	}
	
}
