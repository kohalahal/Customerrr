package com.koala.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.koala.dao.Dao;
import com.koala.view.Window;
import com.koala.view.custom.Combo;
import com.koala.view.custom.Toggle;


public class WindowHandler implements ActionListener, ItemListener, KeyListener, TableModelListener, ListSelectionListener {
	Window window;
	Boolean loaded;
	Boolean edited;
	
	public WindowHandler() {
		loaded = false;
		edited = false;

	}
	
	public static void main(String[] args) {
		WindowHandler h = new WindowHandler();
		Window window = new Window();
		//window.addWindowHandler(h);
		Dao d = new Dao();
		Vector<Vector<Object>> v = d.getList();
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		window.tableField.refreshTable(v);
	}
	
	
	
	
	//버튼
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(((Component) e.getSource()).getName()+" "+e.getActionCommand());
		switch( ((Component) e.getSource()).getName() ) {
			case "save":
				if(loaded) editData();
				else addData();
				break;
			case "delete":
				removeData();
				break;
			case "new":
				clearInputField();
				break;
			case "search":
				searchData();
				break;
		}

	}
	
	
	//input field
	private void addData() { //c받기 널이면 그만
		// TODO Auto-generated method stub
		
	}
	
	private void editData() { //다오에 원래 커스터머의 id, 새커스터머
		// TODO Auto-generated method stub
		
		
	}
	
	private void removeData() { //다오에 원래 커스터머의 id
		// TODO Auto-generated method stub
		
	}

	private void clearInputField() {
		// TODO Auto-generated method stub
		
	}


	//table field
	private void searchData() {
		// TODO Auto-generated method stub
		
	}

	private void sortData() {
		// TODO Auto-generated method stub
		
	}
	
	private void selectData() {
		
	}
	
	

	//수정 일어남
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(loaded) edited = true;
		
	}
	
	//소트, 체크박스, 콤보박스
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof Combo || e.getSource() instanceof Toggle) {
			if(loaded) edited = true;
		} //콤보박스, 토글은 읽은상태아니면 액션은 읽을 필요없음
		else if(e.getSource() instanceof JCheckBox){ //여러개오기도하고 하나만오기도해서 어떻게하지?
			System.out.println(e.getStateChange()+((JCheckBox)e.getSource()).getName());
			switch(e.getStateChange()+((JCheckBox)e.getSource()).getName()) {
			case "2모두":System.out.println("2모두");break;
			case "1모두":System.out.println("1모두");break;
			}
		} 
	}


	
	
	
	
	
	
	
	//불린체크박스
	@Override
	public void valueChanged(ListSelectionEvent e) {
		window.tableField.table.
	}
	
	//테이블 선택
	@Override
	public void tableChanged(TableModelEvent e) {
		

	}



	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
}
