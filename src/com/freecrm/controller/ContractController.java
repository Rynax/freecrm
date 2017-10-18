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

import com.freecrm.data.contract.ContractInfoDaoImpl;
import com.freecrm.data.contract.ContractInfoEntity;

@Controller
public class ContractController {
	@RequestMapping( value="/GetContractInfoData")
	@ResponseBody
	public String GetContractInfoData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContractInfoDaoImpl contractinfodao = (ContractInfoDaoImpl)context.getBean("ContractInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<ContractInfoEntity> list = null;
		try {
			list = contractinfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(ContractInfoEntity contract : list) {
				rowsArr.put(contract.toJson());
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
	
	@RequestMapping( value="/SaveContractInfoData")
	@ResponseBody
	public String SaveContractInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContractInfoDaoImpl dao = (ContractInfoDaoImpl)context.getBean("ContractInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String ProductName = request.getParameter("ProductName");
		String ProductCnt = request.getParameter("ProductCnt");
		String CompanyName = request.getParameter("CompanyName");
		String Price = request.getParameter("Price");
		String SumMoney = request.getParameter("SumMoney");
		String Rebate = request.getParameter("Rebate");
		String ConfirmConfirm = request.getParameter("ConfirmConfirm");
		String ReturnId = request.getParameter("ReturnId");
		String InvoiceId = request.getParameter("InvoiceId");
		String Remark = request.getParameter("Remark");
		if((ProductName == null || ProductName.isEmpty()) ||
		   (ProductCnt == null || ProductCnt.isEmpty()) ||
		   (CompanyName == null || CompanyName.isEmpty()) ||
		   (Price == null || Price.isEmpty()) ||
		   (SumMoney == null || SumMoney.isEmpty()) ||
		   (Rebate == null || Rebate.isEmpty()) ||
		   (ConfirmConfirm == null || ConfirmConfirm.isEmpty()) ||
		   (ReturnId == null || ReturnId.isEmpty()) ||
		   (InvoiceId == null || InvoiceId.isEmpty()) ||
		   (Remark == null || Remark.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		ContractInfoEntity entity = new ContractInfoEntity();
		entity.set_product_name(ProductName);
		entity.set_product_Cnt(ProductCnt);
		entity.set_company_name(CompanyName);
		entity.set_price(Price);
		entity.set_sum_money(SumMoney);
		entity.set_rebate(Rebate);
		entity.set_confirm_confirm(ConfirmConfirm);
		entity.set_return_id(ReturnId);
		entity.set_invoice_id(InvoiceId);
		entity.set_remark(Remark);
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeleteContractData")
	@ResponseBody
	public String DeleteContractData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContractInfoDaoImpl dao = (ContractInfoDaoImpl)context.getBean("ContractInfoDaoImpl");
		
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
