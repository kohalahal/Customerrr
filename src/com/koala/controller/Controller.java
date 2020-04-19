package com.koala.controller;

import java.util.Vector;
import com.koala.dao.Dao;
import com.koala.view.Window;

public class Controller implements EventListener {
	Window window;
	Dao dao;
	
	public Controller() {
		window = new Window(this);
		dao = new Dao(this);
		window.table.initiateList(dao.getList());
	}
	
	public static void main(String[] args) {
		new Controller();
	}
	
	@Override
	public void refreshList() {
		// TODO Auto-generated method stub
		System.out.println("테이블이니이시에잇");
		window.table.initiateList(dao.getList());
	}

	@Override
	public void addData(Vector<Object> c) {
		// TODO Auto-generated method stub
		System.out.println("테이블이니이시에잇");
		window.table.initiateList(dao.addCustomer(c));
	}

	@Override
	public void editData(Vector<Object> c) {
		// TODO Auto-generated method stub
		System.out.println("테이블이니이시에잇");
		window.table.initiateList(dao.editCustomer(c));

	}
	
	@Override
	public void editDataStatus(Vector<Object> c) {
		// TODO Auto-generated method stub
		System.out.println("수정만");
		dao.editCustomer(c);
		window.in.clearAll();
	}


	@Override
	public void removeData(Vector<Object> c) {
		// TODO Auto-generated method stub
		System.out.println("테이블이니이시에잇");
		window.table.initiateList(dao.removeCustomer(c));

	}

	@Override
	public void searchData() {
		// TODO Auto-generated method stub
		System.out.println("컽느롤러 서치");

	}

	@Override
	public void changeDataList() {
		// TODO Auto-generated method stub
		System.out.println("컽느롤러 체인치리스트");

	}

	@Override
	public void selectData(Vector<Object> v) {
		// TODO Auto-generated method stub
		window.in.setData(v);
	}

	@Override
	public void dataError(String s) {
		// TODO Auto-generated method stub
		window.popUp("DB 오류 : "+s);
	}

	


}
