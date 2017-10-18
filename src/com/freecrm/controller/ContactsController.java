package com.freecrm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freecrm.data.contacts.ContactsInfoDaoImpl;
import com.freecrm.data.contacts.ContactsInfoEntity;

@Controller
public class ContactsController {
	@RequestMapping( value="/GetContactsInfoData")
	@ResponseBody
	public String GetContactsInfoData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContactsInfoDaoImpl contactsinfodao = (ContactsInfoDaoImpl)context.getBean("ContactsInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<ContactsInfoEntity> list = null;
		try {
			list = contactsinfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(ContactsInfoEntity contact : list) {
				rowsArr.put(contact.toJson());
			}
			
			respObj.put("total", list.size());
			respObj.put("rows", rowsArr);
		} catch (JSONException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		System.out.printf("resp: %s\n", respObj.toString());
		return respObj.toString();
	}
	
	@RequestMapping( value="/SaveContactsInfoData")
	@ResponseBody
	public String SaveContactsInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContactsInfoDaoImpl dao = (ContactsInfoDaoImpl)context.getBean("ContactsInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String PName = request.getParameter("PName");
		String PDuties = request.getParameter("PDuties");
		String PDepart = request.getParameter("PDepart");
		String PTel = request.getParameter("PTel");
		String PMail = request.getParameter("PMail");
		if((PName == null || PName.isEmpty()) ||
		   (PDuties == null || PDuties.isEmpty()) ||
		   (PDepart == null || PDepart.isEmpty()) ||
		   (PTel == null || PTel.isEmpty()) ||
		   (PMail == null || PMail.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		ContactsInfoEntity entity = new ContactsInfoEntity();
		entity.set_p_name(PName);
		entity.set_p_duties(PDuties);
		entity.set_p_depart(PDepart);
		entity.set_p_tel(PTel);
		entity.set_p_mail(PMail);
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeleteContactsData")
	@ResponseBody
	public String DeleteContactsData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContactsInfoDaoImpl dao = (ContactsInfoDaoImpl)context.getBean("ContactsInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String Id = request.getParameter("Id");
		if(Id == null || Id.isEmpty()) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		//dao.delete(Integer.parseInt(Id));
		System.out.printf("delete: %d\n", Integer.parseInt(Id));
		return "{\"Success\":\"成功\"}";
	}
}
