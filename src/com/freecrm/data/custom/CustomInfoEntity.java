package com.freecrm.data.custom;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomInfoEntity {
	private int id;
	private String cus_name;
	private String cus_type;
	private String linkman;
	private String address;
	private String areas;
	private Timestamp create_time;
	private String leader;
	
	public int get_id() {
		return id;
	}
	
	public String get_cus_name() {
		return cus_name;
	}
	
	public String get_cus_type() {
		return cus_type;
	}
	
	public String get_linkman() {
		return linkman;
	}
	
	public String get_address() {
		return address;
	}
	
	public String get_areas() {
		return areas;
	}
	
	public Timestamp get_create_time() {
		return create_time;
	}
	
	public String get_leader() {
		return leader;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_cus_name(String s) {
		this.cus_name = s;
	}
	
	public void set_cus_type(String s) {
		this.cus_type = s;
	}
	
	public void set_linkman(String s) {
		this.linkman = s;
	}
	
	public void set_address(String s) {
		this.address = s;
	}

	public void set_areas(String s) {
		this.areas = s;
	}

	public void set_create_time(Timestamp t) {
		this.create_time = t;
	}

	public void set_leader(String s) {
		this.leader = s;
	}
	
	public CustomInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], cus_name[" + cus_name + "], cus_type[" + cus_type + "], linkman[" + linkman + "], address[" + address + "], areas[" + areas + 
				"], create_time[" + create_time + "], leader[" + leader + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("CusName", cus_name);
		row.put("CusType", cus_type);
		row.put("LinkMan", linkman);
		row.put("Address", address);
		row.put("Areas", areas);
		row.put("CreateTime", create_time);
		row.put("Leader", leader);
		return row;
	}
}
