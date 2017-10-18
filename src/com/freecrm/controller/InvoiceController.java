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

import com.freecrm.data.invoice.InvoiceInfoDaoImpl;
import com.freecrm.data.invoice.InvoiceInfoEntity;

@Controller
public class InvoiceController {
	@RequestMapping( value="/GetInvoiceInfoData")
	@ResponseBody
	public String GetInvoiceInfoData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InvoiceInfoDaoImpl invoiceinfodao = (InvoiceInfoDaoImpl)context.getBean("InvoiceInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<InvoiceInfoEntity> list = null;
		try {
			list = invoiceinfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(InvoiceInfoEntity invoice : list) {
				rowsArr.put(invoice.toJson());
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
	
	@RequestMapping( value="/SaveInvoiceData")
	@ResponseBody
	public String SaveInvoiceData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InvoiceInfoDaoImpl dao = (InvoiceInfoDaoImpl)context.getBean("InvoiceInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String InvoiceId = request.getParameter("InvoiceId");
		String Money = request.getParameter("Money");
		String InvoiceType = request.getParameter("InvoiceType");
		String InvoiceDate = request.getParameter("InvoiceDate");
		if((InvoiceId == null || InvoiceId.isEmpty()) ||
		   (Money == null || Money.isEmpty()) ||
		   (InvoiceType == null || InvoiceType.isEmpty()) ||
		   (InvoiceDate == null || InvoiceDate.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		InvoiceInfoEntity entity = new InvoiceInfoEntity();
		entity.set_invoice_id(InvoiceId);
		entity.set_money(Money);
		entity.set_invoice_type(InvoiceType);
		entity.set_invoice_date(InvoiceDate);
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeleteInvoiceData")
	@ResponseBody
	public String DeleteInvoiceData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InvoiceInfoDaoImpl dao = (InvoiceInfoDaoImpl)context.getBean("InvoiceInfoDaoImpl");
		
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
