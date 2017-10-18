package com.freecrm.data.project_detail;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectDetailInfoEntity {
	private int id;
	private Timestamp contact_time;
	private String contactway;
	private String participant;
	private String salescontent;
	private String propulsionplan;
	private Timestamp propulstime;
	
	public int get_id() {
		return id;
	}
	
	public Timestamp get_contact_time() {
		return contact_time;
	}
	
	public String get_contactway() {
		return contactway;
	}
	
	public String get_participant() {
		return participant;
	}
	
	public String get_salescontent() {
		return salescontent;
	}
	
	public String get_propulsionplan() {
		return propulsionplan;
	}
	
	public Timestamp get_propulstime() {
		return propulstime;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_contact_time(Timestamp t) {
		this.contact_time = t;
	}
	
	public void set_contactway(String s) {
		this.contactway = s;
	}
	
	public void set_participant(String s) {
		this.participant = s;
	}
	
	public void set_salescontent(String s) {
		this.salescontent = s;
	}

	public void set_propulsionplan(String s) {
		this.propulsionplan = s;
	}

	public void set_propulstime(Timestamp t) {
		this.propulstime = t;
	}
	
	public ProjectDetailInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], contact_time[" + contact_time + "], contactway[" + contactway + "], participant[" + participant + "], salescontent[" + salescontent + "], propulsionplan[" + propulsionplan + "], propulstime[" + propulstime + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("ContactTime", contact_time);
		row.put("Contactway", contactway);
		row.put("Participant", participant);
		row.put("Salescontent", salescontent);
		row.put("Propulsionplan", propulsionplan);
		row.put("Propulstime", propulstime);
		return row;
	}
}
