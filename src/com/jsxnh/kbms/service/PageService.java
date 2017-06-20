package com.jsxnh.kbms.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsxnh.kbms.dao.AttributeDao;
import com.jsxnh.kbms.dao.CheckingDao;
import com.jsxnh.kbms.dao.CustomerDao;
import com.jsxnh.kbms.dao.DomainDao;
import com.jsxnh.kbms.dao.GradeDao;
import com.jsxnh.kbms.dao.ModuleDao;
import com.jsxnh.kbms.dao.RattributeDao;
import com.jsxnh.kbms.dao.ResourceDao;
import com.jsxnh.kbms.dao.UncheckDao;
import com.jsxnh.kbms.entities.Attribute;
import com.jsxnh.kbms.entities.Customer;
import com.jsxnh.kbms.entities.Rattribute;
import com.jsxnh.kbms.entities.Resource;
import com.jsxnh.kbms.entities.Uncheck;

@Service
public class PageService {

	@Autowired
	private DomainDao domainDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private AttributeDao attributeDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RattributeDao rattributeDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private UncheckDao uncheckDao;
	@Autowired
	private CheckingDao checkingDao;
	
	public Integer findResourceBydomain(Integer domain_id){
		if(resourceDao.listResourceBydomain_id(domain_id).size()%8==0){
			return resourceDao.listResourceBydomain_id(domain_id).size()/8;
		}
		return resourceDao.listResourceBydomain_id(domain_id).size()/8+1;
	}
	
	public JSONArray findResourceBydomian(Integer domain_id,Integer page){
		JSONArray res=new JSONArray();
		List<Resource> resources=resourceDao.listResourceBydomain_id(domain_id);
		int max=8*page;
		if(8*page>resources.size()){
			max=resources.size();
		}
		for(int i=8*(page-1);i<max;i++){
			JSONObject json=new JSONObject();
			json.put("id", resources.get(i).getId());
			json.put("title", resources.get(i).getSummary());
			json.put("resource", resources.get(i).getContent());
			json.put("create_id", resources.get(i).getCreate_id());
			json.put("last_time", new Date(resources.get(i).getLast_time().getTime()));
			json.put("domain", domainDao.finddomainByid(resources.get(i).getDomain_id()).getDomain());
			json.put("module", moduleDao.findModulebyId(resources.get(i).getModule_id()).getModule());
			res.put(json);
		}
		return res;
	}
	
	public Integer findResourceBymodule(Integer module_id){
		if(resourceDao.listResourceBymodule_id(module_id).size()%8==0){
			return resourceDao.listResourceBymodule_id(module_id).size()/8;
		}
		return resourceDao.listResourceBymodule_id(module_id).size()/8+1;
	}
	
	public JSONArray findResourceBymodule(Integer module_id,Integer page){
		JSONArray res=new JSONArray();
		List<Resource> resources=resourceDao.listResourceBymodule_id(module_id);
		int max=8*page;
		if(8*page>resources.size()){
			max=resources.size();
		}
		for(int i=8*(page-1);i<max;i++){
			JSONObject json=new JSONObject();
			json.put("id", resources.get(i).getId());
			json.put("title", resources.get(i).getSummary());
			json.put("resource", resources.get(i).getContent());
			json.put("create_id", resources.get(i).getCreate_id());
			json.put("last_time", new Date(resources.get(i).getLast_time().getTime()));
			json.put("domain", domainDao.finddomainByid(resources.get(i).getDomain_id()).getDomain());
			json.put("module", moduleDao.findModulebyId(resources.get(i).getModule_id()).getModule());
			res.put(json);
		}
		return res;
	}
	
	public Integer findResourceByattribute(String attribute,String value){
		Attribute attr=attributeDao.findAttributeByattribute(attribute);
		List<Rattribute> rattrs=rattributeDao.findAattributeByattributeandvalue(attr.getId(), value);
		List<Resource> resources=new LinkedList<Resource>();
		for(Rattribute rattr:rattrs){
			resources.add(resourceDao.findResourceByid(rattr.getResource_id()));
		}
		if(resources.size()%8==0){
			return resources.size()/8;
		}
		return resources.size()/8+1;
	}
	
	
	public JSONArray findResourceByattribute(String attribute,String value,Integer page){
		JSONArray res=new JSONArray();
		Attribute attr=attributeDao.findAttributeByattribute(attribute);
		List<Rattribute> rattrs=rattributeDao.findAattributeByattributeandvalue(attr.getId(), value);
		List<Resource> resources=new LinkedList<Resource>();
		for(Rattribute rattr:rattrs){
			resources.add(resourceDao.findResourceByid(rattr.getResource_id()));
		}
		int max=8*page;
		if(8*page>resources.size()){
			max=resources.size();
		}
		for(int i=8*(page-1);i<max;i++){
			JSONObject json=new JSONObject();
			json.put("id", resources.get(i).getId());
			json.put("title", resources.get(i).getSummary());
			json.put("resource", resources.get(i).getContent());
			json.put("create_id", resources.get(i).getCreate_id());
			json.put("last_time", new Date(resources.get(i).getLast_time().getTime()));
			json.put("domain", domainDao.finddomainByid(resources.get(i).getDomain_id()).getDomain());
			json.put("module", moduleDao.findModulebyId(resources.get(i).getModule_id()).getModule());
			res.put(json);
		}
		return res;
	}
	
	
	public Integer AllUser(){
		List<Customer> customers=customerDao.listAllusers();
		if(customers.size()%8==0){
			return customers.size()/8;
		}
		return customers.size()/8+1;
	}
	
	
	public JSONArray listUser(Integer page){
		JSONArray res=new JSONArray();
		List<Customer> customers=customerDao.listAllusers();
		int max=8*page;
		if(8*page>customers.size()){
			max=customers.size();
		}
		for(int i=8*(page-1);i<max;i++){
			JSONObject json=new JSONObject();
			json.put("id", customers.get(i).getId());
			json.put("name", customers.get(i).getName());
			json.put("grade", gradeDao.findGradeById(customers.get(i).getGrade_id()).getGrade());
			json.put("domain", domainDao.finddomainByid(customers.get(i).getDomian_id()).getDomain());
			res.put(json);
		}
		return res;
	}
	
	public Integer findUncheckResources(Integer user_id){
		List<Uncheck> unchecks=uncheckDao.listAllUncheck();
		List<Uncheck> res=new LinkedList<>();
		for(Uncheck uncheck:unchecks){
			if(!checkingDao.existUncheck(uncheck.getId(), user_id)){
				res.add(uncheck);
			}
		}
		if(res.size()%8==0){
			return res.size()/8;
		}
		return res.size()/8+1;
	}
	
	public JSONArray findUncheckResources(Integer user_id,Integer page){
		List<Uncheck> unchecks=uncheckDao.listAllUncheck();
		List<Uncheck> res=new LinkedList<>();
		for(Uncheck uncheck:unchecks){
			if(!checkingDao.existUncheck(uncheck.getId(), user_id)){
				res.add(uncheck);
			}
		}
		JSONArray json=new JSONArray();
		int max=8*page;
		if(8*page>res.size()){
			max=res.size();
		}
		for(int i=8*(page-1);i<max;i++){
			JSONObject item=new JSONObject();
			item.put("id", res.get(i).getId());
			item.put("resource", res.get(i).getResource());
			item.put("title", res.get(i).getTitle());
			item.put("domain", domainDao.finddomainByid(res.get(i).getDomain_id()).getDomain());
			item.put("module", moduleDao.findModulebyId(res.get(i).getModule_id()).getModule());
			item.put("check_time", res.get(i).getCheck_time());
			json.put(item);
		}
		return json;
		
	}
	
	
}
