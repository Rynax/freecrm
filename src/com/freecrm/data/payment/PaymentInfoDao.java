package com.freecrm.data.payment;

import java.util.List;

public interface PaymentInfoDao {
	public void add(PaymentInfoEntity p);
	public void update(PaymentInfoEntity p);
	public void delete(int id);
	public List<PaymentInfoEntity> find_by_id(int id);
	public List<PaymentInfoEntity> find_all();
}
