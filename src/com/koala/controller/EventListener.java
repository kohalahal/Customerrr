package com.koala.controller;

import java.util.Vector;

public interface EventListener {

	void addData(Vector<Object> c);
	void editData(Vector<Object> c);
	void editDataStatus(Vector<Object> c);
	void removeData(Vector<Object> c);
	void searchData();
	void changeDataList();
	void refreshList();
	void selectData(Vector<Object> v);
	void dataError(String s);


}
