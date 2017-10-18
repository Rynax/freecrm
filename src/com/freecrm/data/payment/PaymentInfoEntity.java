package com.freecrm.data.payment;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentInfoEntity {
	private int id;
	private String money;
	private String proportion;
	private String residue;
	private String payment_date;
	private String type;
	
	public int get_id() {
		return id;
	}
	
	public String get_money() {
		return money;
	}
	
	public String get_proportion() {
		return proportion;
	}
	
	public String get_residue() {
		return residue;
	}

	public String get_payment_date() {
		return payment_date;
	}

	public String get_type() {
		return type;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_money(String s) {
		this.money = s;
	}

	public void set_proportion(String s) {
		this.proportion = s;
	}

	public void set_residue(String s) {
		this.residue = s;
	}
	
	public void set_payment_date(String s) {
		this.payment_date = s;
	}
	
	public void set_type(String s) {
		this.type = s;
	}
	
	public PaymentInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], money[" + money + "], proportion[" + proportion + "], residue[" + residue + "], payment_date[" + payment_date + "], type[" + type + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("Money", money);
		row.put("Proportion", proportion);
		row.put("Residue", residue);
		row.put("Payment_date", payment_date);
		row.put("Type", type);
		return row;
	}
}
