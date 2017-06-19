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
import org.springframework.web.servlet.ModelAndView;

import com.jsxnh.kbms.dao.CustomerDao;
import com.jsxnh.kbms.dao.DomainDao;
import com.jsxnh.kbms.dao.GradeDao;
import com.jsxnh.kbms.entities.Customer;
import com.jsxnh.kbms.entities.Domain;

@Controller
public class UserHandler {

	@Autowired
	CustomerDao customerDao;
	@Autowired
	DomainDao domainDao;
	@Autowired
	GradeDao gradeDao;
	
	
	@RequestMapping(value="/register",produces="application/json;charset=UTF-8")
	public ModelAndView Register(){
		ModelAndView model=new ModelAndView("register");
		JSONArray json=new JSONArray();
		for(Domain domain:domainDao.listAllDomain()){
			json.put(domain.getDomain());
		}
		model.addObject("domains", json.toString());
		return model;
	}
	
	@RequestMapping("/login")
	public String Login(){
		return "login";
	}
	
	@RequestMapping("/main")
	public String Main(){
		return "main";
	}
	
	@RequestMapping("/kbms/modifymessage")
	public ModelAndView ModifyMessage(HttpServletRequest request){
		ModelAndView model=new ModelAndView("modifymessage");
		Customer cus=customerDao.queryForExist((Integer)(request.getSession().getAttribute("user_id"))).get(0);
		model.addObject("name", cus.getName());
		model.addObject("age", cus.getAge());
		model.addObject("sex", cus.getSex());
		model.addObject("domain", domainDao.finddomainByid(cus.getDomian_id()).getDomain());
		JSONArray json=new JSONArray();
		for(Domain domain:domainDao.listAllDomain()){
			json.put(domain.getDomain());
		}
		model.addObject("domains", json.toString());		
		return model;
	}
	
	@RequestMapping("/kbms/lookmessage")
	public ModelAndView lookMessage(HttpServletRequest request){
		ModelAndView model=new ModelAndView("lookmessage");
		Customer cus=customerDao.queryForExist((Integer)(request.getSession().getAttribute("user_id"))).get(0);
		model.addObject("name", cus.getName());
		model.addObject("age", cus.getAge());
		model.addObject("sex", cus.getSex());
		model.addObject("tel", cus.getTel());
		model.addObject("email", cus.getEmail());
		model.addObject("domain", domainDao.finddomainByid(cus.getDomian_id()).getDomain());
		model.addObject("grade", gradeDao.findGradeBygrade(cus.getGrade_id()).getGrade());
		return model;
	}
	
	
	@RequestMapping(value="/kbms/register",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String Register(@RequestBody String usermessage){
		JSONObject json=new JSONObject(usermessage);
		Customer cus=new Customer();
		cus.setId(json.getInt("id"));
		cus.setPassword(json.getString("password"));
		cus.setName(json.getString("name"));
		cus.setAge(json.getInt("age"));
		cus.setSex(json.getInt("sex"));
		cus.setTel(json.getString("tel"));
		cus.setEmail(json.getString("email"));
		cus.setGrade_id(0);
		cus.setImage("images\\user.png");
		cus.setDomian_id(domainDao.finddomainBydomain(json.getString("domain")).getId());
		customerDao.addCustomer(cus);
		JSONObject res=new JSONObject();
		res.put("result", "success");
		return res.toString();
	}
	
	@RequestMapping(value="/finduser",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String findUser(@RequestParam("user_id") Integer user_id){
		JSONArray res=new JSONArray();
		if(customerDao.queryForExist(user_id).size()>0){
			res.put("Y");
		}else{
			res.put("N");
		}
		return res.toString();
	}
	
	@RequestMapping(value="/kbms/modifypassword",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
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
	
	@RequestMapping(value="/kbms/login",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public  @ResponseBody String login(HttpServletRequest request){
		String user_id=request.getParameter("id");
		String password=request.getParameter("password");
		JSONArray res=new JSONArray();
		if(customerDao.Login(Integer.parseInt(user_id), password)){
			request.getSession().setAttribute("user_id",Integer.parseInt(user_id));
			res.put("Y");
		}else{
			res.put("N");
		}		
		return res.toString();
	}
	
	@RequestMapping(value="/kbms/updatedomain",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String updateDomain(@RequestParam("user_id") Integer user_id,@RequestParam("domain") String domain){
		JSONArray res=new JSONArray();
		customerDao.updateDomain(user_id, domainDao.finddomainBydomain(domain).getId());
		res.put("Y");
		return res.toString();
	}
	
	@RequestMapping(value="/kbms/updategrade",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String updateGrade(@RequestParam("user_id") Integer user_id,@RequestParam("grade") Integer grade){
		JSONArray res=new JSONArray();
		customerDao.updateDomain(user_id,gradeDao.findGradeBygrade(grade).getId());
		res.put("Y");
		return res.toString();
	}
	
	@RequestMapping(value="/kbms/updatemessage",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String updateMessage(@RequestParam("user_id") Integer user_id,@RequestParam("name") String name,
			                                   @RequestParam("sex") Integer sex,@RequestParam("age") Integer age){
		JSONArray res=new JSONArray();
		customerDao.updateMessage(user_id, name, sex, age);
		res.put("Y");
		return res.toString();
	}
	
	@RequestMapping("/kbms/logout")
	public void Logout(HttpServletRequest request){
		request.getSession().removeAttribute("user_id");
	}
}
