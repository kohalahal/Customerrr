package com.koala.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.koala.controller.EventListener;


public class Window extends JFrame {
	String appName;
	protected JPanel frame;
	JPanel mainPanel;
	public InputField in;
	public TableField table;
	Color back;
	
	public Window() {
		appName = "고객 관리 시스템";		
		setBounds(100, 100, 1280, 510);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setName(appName);
		frame = (JPanel) getContentPane();
		frame.setLayout(null);
		back = new Color(16773102);
        
		
//		mainPanel = new JPanel();
//		mainPanel.setBackground(Color.WHITE);
//		mainPanel.setBounds(10, 10, 300, 90);
//		frame.add(mainPanel);
//		mainPanel.setLayout(null);
//		JLabel HLogo = new JLabel(logo);
//		HLogo.setBounds(10, 5, 80, 80);
//		mainPanel.add(HLogo);
//		JLabel HotelName = new JLabel(hotelName);
//		HotelName.setBounds(113, 17, 130, 26);
//		mainPanel.add(HotelName);
//		//frame.add(HotelName);
//		HotelName.setFont(new Font("Dialog", Font.BOLD, 18));
//		JLabel AppName = new JLabel("투숙객 관리 시스템");
//		mainPanel.add(AppName);
//		AppName.setBounds(105, 45, 148, 24);
//		AppName.setFont(new Font("맑은 고딕", Font.BOLD, 17));
       

		in = new InputField();
		//in.setBounds(10, 7, 300, 455);
		//frame.add(in);
		table = new TableField();
		//table.setBounds(315, 10, 940, 455);
		//frame.add(table);
		table.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	        	//System.out.println("테이블필드 리스트셀렉션");
	        	if (!e.getValueIsAdjusting()) {
	        		 System.out.println("테이블필드 리스트셀렉션!!!");
	        		 System.out.println("로우"+table.table.getSelectedRow());
	        		 if(table.table.getSelectedRow()>-1&&table.table.getSelectedRow()<table.table.getRowCount()) {
	        			 in.setData(table.dataSelected(table.table.getSelectedRow()));
	        		 } else {
	        			// System.out.println("테이블 리스트셀렉션일어났지만 범위바깥임");
	        		 }
	        	 }
	            
	        }
	    });
		//RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS, 10);
		
		//frame.setLayout(rl);
		//frame.add(in, new Float(1));
		//frame.add(table, new Float(1));
		
		
		frame.setLayout(new BorderLayout(5,5));
		frame.setBorder(new Border() {			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(5,20,5,20);
			}
		});
		frame.setBackground(back);
		frame.add(table);
		frame.add(in, BorderLayout.WEST);
		pack();
		setVisible(true);
		
		
		//frame.setLayout(new BorderLayout());
		//frame.add(in,BorderLayout.WEST);
		//frame.add(table,BorderLayout.CENTER);

		
		//frame.setLayout(new GridLayout(1,2));
		//frame.add(in,0);
		//frame.add(frame, 1);
		

	}
	
	public Window(EventListener l) {
		this();
		addListener(l);
	}
	
	public static void main(String[] args) {
		new Window();
	}
	
	private void addListener(EventListener l) {
		table.addListener(l);
		in.addListener(l);
	}
	
	public void selectData(int row) {
		in.setData(table.dataSelected(row));
	}	
	
	public void popUp(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

}
