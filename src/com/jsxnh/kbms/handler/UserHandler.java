package com.jsxnh.kbms.handler;

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

import com.jsxnh.kbms.dao.CustomerDao;
import com.jsxnh.kbms.dao.DomainDao;
import com.jsxnh.kbms.dao.GradeDao;
import com.jsxnh.kbms.entities.Customer;

@Controller
public class UserHandler {

	@Autowired
	CustomerDao customerDao;
	@Autowired
	DomainDao domainDao;
	@Autowired
	GradeDao gradeDao;
	
	@RequestMapping(value="/register",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String Register(@RequestBody String usermessage){
		JSONObject json=new JSONObject(usermessage);
		Customer cus=new Customer();
		cus.setId(json.getInt("id"));
		cus.setPassword(json.getString("password"));
		cus.setName(json.getString("name"));
		cus.setAge(json.getInt("age"));
		cus.setSex(json.getInt("sex"));
		cus.setTel(json.getInt("tel"));
		cus.setEmail(json.getString("email"));
		cus.setGrade_id(0);
		cus.setImage("images\\user.png");
		cus.setDomian_id(domainDao.finddomainBydomain(json.getString("domain")).getId());
		JSONObject res=new JSONObject();
		res.put("result", "success");
		return res.toString();
	}
	
	@RequestMapping(value="/finduser",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findUser(@RequestParam("user_id") Integer user_id){
		JSONArray res=new JSONArray();
		if(customerDao.queryForExist(user_id).size()>0){
			res.put("Y");
		}else{
			res.put("N");
		}
		return res.toString();
	}
	
	@RequestMapping(value="/modifypassword",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String modifyPassword(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		JSONArray res=new JSONArray();
		if(customerDao.modifyPassword(json.getInt("user_id"), json.getInt("tel"), json.getString("password"))){
			res.put("Y");
		}else{
			res.put("N");
		}
		return res.toString();
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String login(HttpServletRequest request){
		Integer user_id=Integer.parseInt(request.getParameter("user_id"));
		String password=request.getParameter("password");
		JSONArray res=new JSONArray();
		if(customerDao.Login(user_id, password)){
			request.getSession().setAttribute("user_id",user_id);
			res.put("Y");
		}else{
			res.put("N");
		}		
		return res.toString();
	}
	
	@RequestMapping(value="/updatedomain",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String updateDomain(@RequestParam("user_id") Integer user_id,@RequestParam("domain") String domain){
		JSONArray res=new JSONArray();
		customerDao.updateDomain(user_id, domainDao.finddomainBydomain(domain).getId());
		res.put("Y");
		return res.toString();
	}
	
	@RequestMapping(value="/updategrade",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String updateGrade(@RequestParam("user_id") Integer user_id,@RequestParam("grade") Integer grade){
		JSONArray res=new JSONArray();
		customerDao.updateDomain(user_id,gradeDao.findGradeBygrade(grade).getId());
		res.put("Y");
		return res.toString();
	}
	
	@RequestMapping(value="/updatemessage",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String updateMessage(@RequestParam("user_id") Integer user_id,@RequestParam("name") String name,
			                                   @RequestParam("sex") Integer sex,@RequestParam("age") Integer age){
		JSONArray res=new JSONArray();
		customerDao.updateMessage(user_id, name, sex, age);
		res.put("Y");
		return res.toString();
	}
	
}
