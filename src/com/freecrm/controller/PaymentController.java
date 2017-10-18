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

import com.freecrm.data.payment.PaymentInfoDaoImpl;
import com.freecrm.data.payment.PaymentInfoEntity;

@Controller
public class PaymentController {
	@RequestMapping( value="/GetPaymentInfoData")
	@ResponseBody
	public String GetPaymentInfoData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PaymentInfoDaoImpl paymentinfodao = (PaymentInfoDaoImpl)context.getBean("PaymentInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<PaymentInfoEntity> list = null;
		try {
			list = paymentinfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(PaymentInfoEntity payment : list) {
				rowsArr.put(payment.toJson());
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
	
	@RequestMapping( value="/SavePaymentInfoData")
	@ResponseBody
	public String SavePaymentInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PaymentInfoDaoImpl dao = (PaymentInfoDaoImpl)context.getBean("PaymentInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String Money = request.getParameter("Money");
		String Proportion = request.getParameter("Proportion");
		String Residue = request.getParameter("Residue");
		String Payment_date = request.getParameter("Payment_date");
		String Type = request.getParameter("Type");
		if((Money == null || Money.isEmpty()) ||
		   (Proportion == null || Proportion.isEmpty()) ||
		   (Residue == null || Residue.isEmpty()) ||
		   (Payment_date == null || Payment_date.isEmpty()) ||
		   (Type == null || Type.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		PaymentInfoEntity entity = new PaymentInfoEntity();
		entity.set_money(Money);
		entity.set_proportion(Proportion);
		entity.set_residue(Residue);
		entity.set_payment_date(Payment_date);
		entity.set_type(Type);
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeletePaymentData")
	@ResponseBody
	public String DeletePaymentData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PaymentInfoDaoImpl dao = (PaymentInfoDaoImpl)context.getBean("PaymentInfoDaoImpl");
		
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
