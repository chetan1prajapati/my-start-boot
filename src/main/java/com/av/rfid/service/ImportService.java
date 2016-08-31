package com.av.rfid.service;

import com.av.rfid.excel.beans.FileBean;

public interface ImportService {

	public void importExcel(FileBean fileBean);

	public void importStockingExcel(FileBean uploadItem);
}
