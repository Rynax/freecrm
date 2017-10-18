package com.freecrm.data.project;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectInfoEntity {
	private int id;
	private String proname;
	private Timestamp define_time;
	private String leader;
	private String contract_amount;
	private String expected_month;
	private String schedule;
	private Timestamp schedule_time;
	private String actual_amount;
	private String paid_amount;
	private String return_amount;
	private Timestamp paymentplan;
	private int projectcycle;
	private String contract_id;
	
	public int get_id() {
		return id;
	}
	
	public String get_proname() {
		return proname;
	}
	
	public Timestamp get_define_time() {
		return define_time;
	}
	
	public String get_leader() {
		return leader;
	}
	
	public String get_contract_amount() {
		return contract_amount;
	}
	
	public String get_expected_month() {
		return expected_month;
	}
	
	public String get_schedule() {
		return schedule;
	}
	
	public Timestamp get_schedule_time() {
		return schedule_time;
	}
	
	public String get_actual_amount() {
		return actual_amount;
	}
	
	public String get_paid_amount() {
		return paid_amount;
	}
	
	public String get_return_amount() {
		return return_amount;
	}
	
	public Timestamp get_paymentplan() {
		return paymentplan;
	}
	
	public int get_projectcycle() {
		return projectcycle;
	}
	
	public String get_contract_id() {
		return contract_id;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_proname(String s) {
		this.proname = s;
	}
	
	public void set_define_time(Timestamp t) {
		this.define_time = t;
	}
	
	public void set_leader(String s) {
		this.leader = s;
	}
	
	public void set_contract_amount(String s) {
		this.contract_amount = s;
	}
	
	public void set_expected_month(String s) {
		this.expected_month = s;
	}

	public void set_schedule(String s) {
		this.schedule = s;
	}

	public void set_schedule_time(Timestamp t) {
		this.schedule_time = t;
	}
	
	public void set_actual_amount(String s) {
		this.actual_amount = s;
	}
	
	public void set_paid_amount(String s) {
		this.paid_amount = s;
	}

	public void set_return_amount(String s) {
		this.return_amount = s;
	}

	public void set_paymentplan(Timestamp t) {
		this.paymentplan = t;
	}
	
	public void set_projectcycle(int i) {
		this.projectcycle = i;
	}
	
	public void set_contract_id(String s) {
		this.contract_id = s;
	}
	
	public ProjectInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], proname[" + proname + "], define_time[" + define_time + "], leader[" + leader + 
				"], contract_amount[" + contract_amount + "], expected_month[" + expected_month + "], schedule[" + schedule + "], schedule_time[" + schedule_time + 
				"], actual_amount[" + actual_amount + "], paid_amount[" + paid_amount + "], return_amount[" + return_amount + "], paymentplan[" + paymentplan + 
				"], projectcycle[" + projectcycle + "], contract_id[" + contract_id + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("Proname", proname);
		row.put("Define_time", define_time);
		row.put("Leader", leader);
		row.put("Contract_amount", contract_amount);
		row.put("Expected_month", expected_month);
		row.put("Schedule", schedule);
		row.put("Schedule_time", schedule_time);
		row.put("Actual_amount", actual_amount);
		row.put("Paid_amount", paid_amount);
		row.put("Return_amount", return_amount);
		row.put("Paymentplan", paymentplan);
		row.put("Projectcycle", projectcycle);
		row.put("Contract_id", contract_id);
		return row;
	}
}
