package com.freecrm.data.contacts;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactsInfoEntity {
	private int id;
	private String p_name;
	private String p_duties;
	private String p_depart;
	private String p_tel;
	private String p_mail;
	
	public int get_id() {
		return id;
	}
	
	public String get_p_name() {
		return p_name;
	}
	
	public String get_p_duties() {
		return p_duties;
	}
	
	public String get_p_depart() {
		return p_depart;
	}
	
	public String get_p_tel() {
		return p_tel;
	}
	
	public String get_p_mail() {
		return p_mail;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_p_name(String s) {
		this.p_name = s;
	}
	
	public void set_p_duties(String s) {
		this.p_duties = s;
	}
	
	public void set_p_depart(String s) {
		this.p_depart = s;
	}
	
	public void set_p_tel(String s) {
		this.p_tel = s;
	}
	
	public void set_p_mail(String s) {
		this.p_mail = s;
	}
	
	public ContactsInfoEntity(String p_name, String p_duties, String p_depart, String p_tel, String p_mail) {
		super();
		this.p_name = p_name;
		this.p_duties = p_duties;
		this.p_depart = p_depart;
		this.p_tel = p_tel;
		this.p_mail = p_mail;
	}
	
	public ContactsInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], p_name[" + p_name + "], p_duties[" + p_duties + "], p_depart[" + p_depart + "], p_tel[" + p_tel + "], p_mail[" + p_mail + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("PName", p_name);
		row.put("PDuties", p_duties);
		row.put("PDepart", p_depart);
		row.put("PTel", p_tel);
		row.put("PMail", p_mail);
		return row;
	}
}
