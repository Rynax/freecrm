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

import com.freecrm.data.custom.CustomInfoDaoImpl;
import com.freecrm.data.custom.CustomInfoEntity;

@Controller
public class CustomController {
	@RequestMapping( value="/GetCustomInfoData")
	@ResponseBody
	public String GetCustomInfoData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomInfoDaoImpl custominfodao = (CustomInfoDaoImpl)context.getBean("CustomInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<CustomInfoEntity> list = null;
		try {
			list = custominfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(CustomInfoEntity custom : list) {
				rowsArr.put(custom.toJson());
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
	
	@RequestMapping( value="/SaveCustomInfoData")
	@ResponseBody
	public String SaveCustomInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomInfoDaoImpl dao = (CustomInfoDaoImpl)context.getBean("CustomInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String CusName = request.getParameter("CusName");
		String CusType = request.getParameter("CusType");
		String LinkMan = request.getParameter("LinkMan");
		String Address = request.getParameter("Address");
		String Areas = request.getParameter("Areas");
		String Leader = request.getParameter("Leader");
		if((CusName == null || CusName.isEmpty()) ||
		   (CusType == null || CusType.isEmpty()) ||
		   (LinkMan == null || LinkMan.isEmpty()) ||
		   (Address == null || Address.isEmpty()) ||
		   (Areas == null || Areas.isEmpty()) ||
		   (Leader == null || Leader.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		CustomInfoEntity entity = new CustomInfoEntity();
		entity.set_cus_name(CusName);
		entity.set_cus_type(CusType);
		entity.set_linkman(LinkMan);
		entity.set_address(Address);
		entity.set_areas(Areas);
		entity.set_leader(Leader);
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeleteCustomInfoData")
	@ResponseBody
	public String DeleteCustomInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomInfoDaoImpl dao = (CustomInfoDaoImpl)context.getBean("CustomInfoDaoImpl");
		
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
