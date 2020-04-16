package com.koala.controller;

import java.util.Scanner;
import java.util.Vector;

import com.koala.dao.Dao;
import com.koala.view.Window;

public class Controller implements EventListener {
	Window window;
	Dao dao;
	
	public Controller() {
		window = new Window(this);
		dao = new Dao();
		
	}
	
	public static void main(String[] args) {
		
		new Controller();
	}

	@Override
	public void addData() {
		// TODO Auto-generated method stub
		System.out.println("컽느롤러 애드");
	}

	@Override
	public void editData() {
		// TODO Auto-generated method stub
		System.out.println("컽느롤러 에딧");

	}

	@Override
	public void removeData() {
		// TODO Auto-generated method stub
		System.out.println("컽느롤러 리무브");

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

}
