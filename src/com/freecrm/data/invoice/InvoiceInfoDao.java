package com.freecrm.data.invoice;

import java.util.List;

public interface InvoiceInfoDao {
	public void add(InvoiceInfoEntity p);
	public void update(InvoiceInfoEntity p);
	public void delete(int id);
	public List<InvoiceInfoEntity> find_by_id(int id);
	public List<InvoiceInfoEntity> find_all();
}
