package com.freecrm.data.contract;

import org.json.JSONException;
import org.json.JSONObject;

public class ContractInfoEntity {
	private int id;
	private String product_name;
	private String product_Cnt;
	private String company_name;
	private String price;
	private String sum_money;
	private String rebate;
	private String remark;
	private String confirm_confirm;
	private String return_id;
	private String invoice_id;
	
	public int get_id() {
		return id;
	}
	
	public String get_product_name() {
		return product_name;
	}
	
	public String get_product_Cnt() {
		return product_Cnt;
	}
	
	public String get_company_name() {
		return company_name;
	}
	
	public String get_price() {
		return price;
	}
	
	public String get_sum_money() {
		return sum_money;
	}
	
	public String get_rebate() {
		return rebate;
	}
	
	public String get_remark() {
		return remark;
	}
	
	public String get_confirm_confirm() {
		return confirm_confirm;
	}
	
	public String get_return_id() {
		return return_id;
	}
	
	public String get_invoice_id() {
		return invoice_id;
	}
	
	public void set_id(int i) {
		this.id = i;
	}
	
	public void set_product_name(String s) {
		this.product_name = s;
	}
	
	public void set_product_Cnt(String s) {
		this.product_Cnt = s;
	}
	
	public void set_company_name(String s) {
		this.company_name = s;
	}
	
	public void set_price(String s) {
		this.price = s;
	}

	public void set_sum_money(String s) {
		this.sum_money = s;
	}

	public void set_rebate(String s) {
		this.rebate = s;
	}

	public void set_remark(String s) {
		this.remark = s;
	}

	public void set_confirm_confirm(String s) {
		this.confirm_confirm = s;
	}

	public void set_return_id(String s) {
		this.return_id = s;
	}

	public void set_invoice_id(String s) {
		this.invoice_id = s;
	}
	
	public ContractInfoEntity() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserInfo: id[" + id + "], product_name[" + product_name + "], product_Cnt[" + product_Cnt + "], company_name[" + company_name + "], price[" + price + "], sum_money[" + sum_money + 
				"], rebate[" + rebate + "], remark[" + remark + "], confirm_confirm[" + confirm_confirm + "], return_id[" + return_id + "], invoice_id[" + invoice_id + "]";
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject row = new JSONObject();
		row.put("Id", id);
		row.put("ProductName", product_name);
		row.put("ProductCnt", product_Cnt);
		row.put("CompanyName", company_name);
		row.put("Price", price);
		row.put("SumMoney", sum_money);
		row.put("Rebate", rebate);
		row.put("Remark", remark);
		row.put("ConfirmConfirm", confirm_confirm);
		row.put("ReturnId", return_id);
		row.put("InvoiceId", invoice_id);
		return row;
	}
}
