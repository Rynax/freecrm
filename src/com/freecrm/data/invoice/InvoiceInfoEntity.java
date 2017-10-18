package com.freecrm.data.invoice;

import org.json.JSONException;
import org.json.JSONObject;

public class InvoiceInfoEntity {
	private int id;
	private String invoice_id;
	private String money;
	private String invoice_type;
	private String invoice_date;
	
	public int get_id() {
		return id;
	}
	
	public String get_invoice_id() {
		return invoice_id;
	}
	
	public String get_money() {
		return money;
	}
	
	public String get_invoice_type() {
		return invoice_type;
	}
	
	public String get_invoice_date() {
		return invoice_date;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_invoice_id(String s) {
		this.invoice_id = s;
	}
	
	public void set_money(String s) {
		this.money = s;
	}
	
	public void set_invoice_type(String s) {
		this.invoice_type = s;
	}
	
	public void set_invoice_date(String s) {
		this.invoice_date = s;
	}
	
	public InvoiceInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], invoice_id[" + invoice_id + "], money[" + money + "], invoice_type[" + invoice_type + "], invoice_date[" + invoice_date + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("InvoiceId", invoice_id);
		row.put("Money", money);
		row.put("InvoiceType", invoice_type);
		row.put("InvoiceDate", invoice_date);
		return row;
	}
}
