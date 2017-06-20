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
import com.jsxnh.kbms.dao.GradeDao;
import com.jsxnh.kbms.entities.Atg;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Grade;
import com.jsxnh.kbms.service.FindAuthorityService;

@Controller
public class GradeHandler {

	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private FindAuthorityService findAuthorityService;
	@Autowired
	private AtgDao atgDao;
	
	
	@RequestMapping(value="/kbms/showmanageauthority",method=RequestMethod.GET)
	public ModelAndView showManageauthority(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("管理权限")){
				model=new ModelAndView("manageauthority");
				List<Grade> grades=gradeDao.listAllGrade();
				JSONArray json=new JSONArray();
				for(Grade grade:grades){
					json.put(grade.getGrade());
				}
				model.addObject("grades", json);
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/findaddauthoritybygrade",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findAddauthorityBygrade(@RequestParam("id") Integer id){
		JSONArray json=new JSONArray();
		List<Authority> res=authorityDao.listAllAuthority();
		List<String> str=new LinkedList<>();
		for(Authority authority:res){
			str.add(authority.getAuthority());
		}
		List<Atg> atgs=atgDao.findAtgBygrade(gradeDao.findGradeBygrade(id).getId());
		for(Atg atg:atgs){
			Authority authority=authorityDao.findAuthorityByid(atg.getAuthority_id());
			if(str.contains(authority.getAuthority())){
				str.remove(authority.getAuthority());
			}
		}
		for(String ss:str){
			json.put(ss);
		}
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/finddeleteauthoritybygrade",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findDeleteauthorityBygrade(@RequestParam("id") Integer id){
		JSONArray json=new JSONArray();
		List<Authority> authorities=new LinkedList<>();
		List<Atg> atgs=atgDao.findAtgBygrade(gradeDao.findGradeBygrade(id).getId());
		for(Atg atg:atgs){
			Authority authority=authorityDao.findAuthorityByid(atg.getAuthority_id());
			authorities.add(authority);
		}
		for(Authority authority:authorities){
			json.put(authority.getAuthority());
		}
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/addauthority",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addAuthority(@RequestBody String str){
		JSONArray res=new JSONArray();
		JSONObject json=new JSONObject(str);
		Integer grade_id=gradeDao.findGradeBygrade(json.getInt("grade")).getId();
		Integer authority_id=authorityDao.findAuthorityByauthority(json.getString("authority")).getId();
		Atg atg=new Atg();
		atg.setGrade_id(grade_id);
		atg.setAuthority_id(authority_id);
		atg.setLast_time(new Date());
		atg.setCreate_id(json.getInt("user_id"));
		atgDao.addAtg(atg);		
		return res.toString();
	}
	
	@RequestMapping(value="/kbms/deleteauthority",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String deleteAuthority(@RequestBody String str){
		JSONArray res=new JSONArray();
		JSONObject json=new JSONObject(str);
		Integer grade_id=gradeDao.findGradeBygrade(json.getInt("grade")).getId();
		Integer authority_id=authorityDao.findAuthorityByauthority(json.getString("authority")).getId();
		atgDao.deleteAtg(grade_id, authority_id);
		return res.toString();
	}
	
   @RequestMapping("showauthorities")
   public ModelAndView showAuthories(){
	   ModelAndView model=new ModelAndView("showauthorities");
	   JSONArray json=new JSONArray();
	   List<Grade> grades=gradeDao.listAllGrade();
	   for(Grade grade:grades){
		   JSONObject item=new JSONObject();
		   List<Atg> atgs=atgDao.findAtgBygrade(grade.getId());
		   String str = "";
		   for(Atg atg:atgs){
			   str+=authorityDao.findAuthorityByid(atg.getAuthority_id()).getAuthority()+" ";
		   }		   
		   if(atgs!=null){
			   item.put("grade", grade.getGrade());
			   item.put("authorities", str);
			   json.put(item);
		   }
	   }
	   model.addObject("authorities", json.toString());
	   return model;
   }

   @RequestMapping(value="/kbms/listallgrade",produces="application/json;charset=UTF-8",consumes = "application/json")
   public @ResponseBody String listAllgrade(){
	   JSONArray res=new JSONArray();
	   List<Grade> grades=gradeDao.listAllGrade();
	   for(Grade grade:grades){
		   res.put(grade.getGrade());
	   }
	   return res.toString();
   }
}
