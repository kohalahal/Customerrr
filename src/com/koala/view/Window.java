package com.koala.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.koala.controller.EventListener;
import com.koala.dao.Customer;


public class Window extends JFrame {
	static public Color orange;
	ImageIcon logo;
	String hotelName;
	String appName;
	protected JPanel frame;
	JPanel mainPanel;
	public InputField inputField;
	public TableField tableField;
	
	public Window() {
		orange = new Color(16753510);
		logo = new ImageIcon("c:/logo.png");
		hotelName = "HOTEL KOALA";
		appName = "고객 관리 시스템";
		
		setBounds(100, 100, 1280, 458);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setName(appName);
		frame = (JPanel) getContentPane();
		frame.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(10, 10, 297, 90);
		frame.add(mainPanel);
		mainPanel.setLayout(null);
		JLabel HLogo = new JLabel(logo);
		HLogo.setBounds(10, 5, 80, 80);
		mainPanel.add(HLogo);
		JLabel HotelName = new JLabel(hotelName);
		HotelName.setBounds(113, 17, 130, 26);
		mainPanel.add(HotelName);
		//frame.add(HotelName);
		HotelName.setFont(new Font("Dialog", Font.BOLD, 18));
		JLabel AppName = new JLabel("투숙객 관리 시스템");
		mainPanel.add(AppName);
		AppName.setBounds(105, 45, 148, 24);
		AppName.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		inputField = new InputField();
		inputField.setBounds(10, 110, 295, 300);
		frame.add(inputField);
		tableField = new TableField();
		tableField.sortView.setBounds(759, 5, 169, 23);
		tableField.setBounds(315, 10, 940, 400);
		frame.add(tableField);
		
		setVisible(true);
	}
	
	public Window(EventListener l) {
		this();
		addListener(l);
	}
	
	public static void main(String[] args) {
		new Window();
	}
	
//	public void addWindowHandler(WindowHandler w) {
//		//inputField.addListener(w);
//		tableField.addListener(w);
//	}
	
	public void addListener(EventListener l) {
		tableField.addListener(l);
		inputField.addListener(l);
	}

//input field
	public Customer getInputData() {
		// TODO Auto-generated method stub
		return inputField.getData();
	}
	
	public int getLoadedDataId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void removeData() {
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
	
	private int selectedId() {
		return 0;
	}	

}
