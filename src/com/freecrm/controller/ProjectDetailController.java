package com.freecrm.controller;

import java.sql.Timestamp;
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

import com.freecrm.data.project.ProjectInfoDaoImpl;
import com.freecrm.data.project.ProjectInfoEntity;

@Controller
public class ProjectDetailController {
	@RequestMapping( value="/GetProjectInfoData")
	@ResponseBody
	public String GetProjectInfoData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProjectInfoDaoImpl projectinfodao = (ProjectInfoDaoImpl)context.getBean("ProjectInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<ProjectInfoEntity> list = null;
		try {
			list = projectinfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(ProjectInfoEntity project : list) {
				rowsArr.put(project.toJson());
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
	
	@RequestMapping( value="/SaveProjectDtInfoData")
	@ResponseBody
	public String SaveProjectDtInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProjectInfoDaoImpl dao = (ProjectInfoDaoImpl)context.getBean("ProjectInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String Proname = request.getParameter("Proname");
		String Define_time = request.getParameter("Define_time");
		String Leader = request.getParameter("Leader");
		String Contract_amount = request.getParameter("Contract_amount");
		String Expected_month = request.getParameter("Expected_month");
		String Schedule = request.getParameter("Schedule");
		String Schedule_time = request.getParameter("Schedule_time");
		String Actual_amount = request.getParameter("Actual_amount");
		String Paid_amount = request.getParameter("Paid_amount");
		String Return_amount = request.getParameter("Return_amount");
		String Paymentplan = request.getParameter("Paymentplan");
		String Projectcycle = request.getParameter("Projectcycle");
		if((Proname == null || Proname.isEmpty()) ||
		   (Define_time == null || Define_time.isEmpty()) ||
		   (Leader == null || Leader.isEmpty()) ||
		   (Contract_amount == null || Contract_amount.isEmpty()) ||
		   (Expected_month == null || Expected_month.isEmpty()) ||
		   (Schedule == null || Schedule.isEmpty()) ||
		   (Schedule_time == null || Schedule_time.isEmpty()) ||
		   (Actual_amount == null || Actual_amount.isEmpty()) ||
		   (Paid_amount == null || Paid_amount.isEmpty()) ||
		   (Return_amount == null || Return_amount.isEmpty()) ||
		   (Paymentplan == null || Paymentplan.isEmpty()) ||
		   (Projectcycle == null || Projectcycle.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		ProjectInfoEntity entity = new ProjectInfoEntity();
		entity.set_proname(Proname);
		entity.set_define_time(Timestamp.valueOf(Define_time));
		entity.set_leader(Leader);
		entity.set_contract_amount(Contract_amount);
		entity.set_expected_month(Expected_month);
		entity.set_schedule(Schedule);
		entity.set_schedule_time(Timestamp.valueOf(Schedule_time));
		entity.set_actual_amount(Actual_amount);
		entity.set_paid_amount(Paid_amount);
		entity.set_return_amount(Return_amount);
		entity.set_paymentplan(Timestamp.valueOf(Paymentplan));
		entity.set_projectcycle(Integer.parseInt(Projectcycle));
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeleteProjectDtInfoData")
	@ResponseBody
	public String DeleteProjectDtInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProjectInfoDaoImpl dao = (ProjectInfoDaoImpl)context.getBean("ProjectInfoDaoImpl");
		
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
