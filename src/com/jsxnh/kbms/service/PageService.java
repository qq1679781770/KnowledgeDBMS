package com.jsxnh.kbms.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsxnh.kbms.dao.AttributeDao;
import com.jsxnh.kbms.dao.DomainDao;
import com.jsxnh.kbms.dao.ModuleDao;
import com.jsxnh.kbms.dao.RattributeDao;
import com.jsxnh.kbms.dao.ResourceDao;
import com.jsxnh.kbms.entities.Attribute;
import com.jsxnh.kbms.entities.Rattribute;
import com.jsxnh.kbms.entities.Resource;

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
			json.put("domain", domainDao.finddomainByid(resources.get(i).getId()).getDomain());
			json.put("module", moduleDao.findModulebyId(resources.get(i).getId()).getModule());
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
			json.put("domain", domainDao.finddomainByid(resources.get(i).getId()).getDomain());
			json.put("module", moduleDao.findModulebyId(resources.get(i).getId()).getModule());
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
			json.put("domain", domainDao.finddomainByid(resources.get(i).getId()).getDomain());
			json.put("module", moduleDao.findModulebyId(resources.get(i).getId()).getModule());
			res.put(json);
		}
		return res;
	}
}
