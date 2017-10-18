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

import com.freecrm.data.project_detail.ProjectDetailInfoDaoImpl;
import com.freecrm.data.project_detail.ProjectDetailInfoEntity;

@Controller
public class ProjectController {
	@RequestMapping( value="/GetProjectCorrelationData")
	@ResponseBody
	public String GetProjectCorrelationData(HttpServletRequest request, HttpServletResponse response) {
		JSONObject respObj = new JSONObject();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProjectDetailInfoDaoImpl projectdetailinfodao = (ProjectDetailInfoDaoImpl)context.getBean("ProjectDetailInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"total\":0}";
		}
		
		List<ProjectDetailInfoEntity> list = null;
		try {
			list = projectdetailinfodao.find_all();
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"total\":0}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"total\":0}";
		}
		
		try {
			JSONArray rowsArr = new JSONArray();
			for(ProjectDetailInfoEntity projectdetail : list) {
				rowsArr.put(projectdetail.toJson());
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
	
	@RequestMapping( value="/SaveProjectInfoData")
	@ResponseBody
	public String SaveProjectInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProjectDetailInfoDaoImpl dao = (ProjectDetailInfoDaoImpl)context.getBean("ProjectDetailInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"Error\":\"请登录\"}";
		}
		
		String ContactTime = request.getParameter("ContactTime");
		String Contactway = request.getParameter("Contactway");
		String Propulsionplan = request.getParameter("Propulsionplan");
		String Propulstime = request.getParameter("Propulstime");
		String Participant = request.getParameter("Participant");
		String Salescontent = request.getParameter("Salescontent");
		if((ContactTime == null || ContactTime.isEmpty()) ||
		   (Contactway == null || Contactway.isEmpty()) ||
		   (Propulsionplan == null || Propulsionplan.isEmpty()) ||
		   (Propulstime == null || Propulstime.isEmpty()) ||
		   (Participant == null || Participant.isEmpty()) ||
		   (Salescontent == null || Salescontent.isEmpty())) {
			return "{\"Error\":\"输入有误\"}";
		}
		
		ProjectDetailInfoEntity entity = new ProjectDetailInfoEntity();
		entity.set_contact_time(Timestamp.valueOf(ContactTime));
		entity.set_contactway(Contactway);
		entity.set_propulsionplan(Propulsionplan);
		entity.set_propulstime(Timestamp.valueOf(Propulstime));
		entity.set_participant(Participant);
		entity.set_salescontent(Salescontent);
		
		dao.add(entity);
		
		return "{\"Success\":\"成功\"}";
	}
	
	@RequestMapping( value="/DeleteProjectInfoData")
	@ResponseBody
	public String DeleteProjectInfoData(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProjectDetailInfoDaoImpl dao = (ProjectDetailInfoDaoImpl)context.getBean("ProjectDetailInfoDaoImpl");
		
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
