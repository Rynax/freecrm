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

import com.freecrm.data.user.UserInfoDaoImpl;
import com.freecrm.data.user.UserInfoEntity;

@Controller
public class IndexFrameController {
	@RequestMapping( value="/index_frame.do")
	@ResponseBody
	public String index_frame(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserInfoDaoImpl userinfodao = (UserInfoDaoImpl)context.getBean("UserInfoDaoImpl");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			return "{\"code\":\"1\",\"desc\":\"请登录\",\"url\":\"index.html\"}";
		}
		
		int user_id = (int) session.getAttribute("user_id");
		
		List<UserInfoEntity> list = null;
		try {
			list = userinfodao.find_by_id(user_id);
		} catch (DataAccessException e) {
			System.out.printf("exception: %s\n", e.toString());
			return "{\"code\":\"1\",\"desc\":\"数据库异常\",\"url\":\"index.html\"}";
		}
		
		if(list == null || list.size() == 0) {
			return "{\"code\":\"2\",\"desc\":\"用户不存在，请重新登录\",\"url\":\"index.html\"}";
		}
		System.out.printf("%s\n", list.get(0).toString());
		
		return "{\"code\":\"0\",\"desc\":\"" + list.get(0).get_nick_name() +"\",\"url\":\"\"}";
	}
}
