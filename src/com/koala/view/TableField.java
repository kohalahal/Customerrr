package com.koala.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableColumnModel;
import com.koala.controller.EventListener;
import com.koala.dao.Dao;
import com.koala.view.custom.Combo;
import com.koala.view.custom.DateText;
import com.koala.view.custom.RowHeader;
import com.koala.view.custom.TableModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TableField extends JPanel {
	public Color orange;
	public Color deepOrange;
	public Color gray;

	protected JTable table;
	protected RowHeader row;
	protected TableModel model;
	protected JScrollPane scroll;
	protected TableColumnModel columnModel;	

	JLabel tableTitle;
	protected Combo sortView;
	ButtonGroup check;
	protected JCheckBox cAll;
	protected JCheckBox cBoxCancel;
	protected JCheckBox cBoxBook;
	protected JCheckBox cBoxIn;
	protected JCheckBox cBoxOut;
	protected JCheckBox cBoxPay;
	
	protected Combo srcAtt;	
	protected JTextField srcField;
	protected JButton srcBtn;
	JButton refreshBtn;
	ImageIcon ref;
	
	private EventListener listener;

	JPanel topPanel;
	JPanel bottomPanel;
	String[] sortString;
	JPanel tableTitlePanel;
	JPanel tableSortPanel;
	JPanel listChoicePanel;
	JPanel searchPanel;
	
	Vector<Vector<Object>> all;
	Vector<Vector<Object>> book;
	Vector<Vector<Object>> in;
	Vector<Vector<Object>> out;
	Vector<Vector<Object>> cancel;
	Vector<Vector<Object>> pay;
	Vector<Vector<Object>> src;
	GridBagConstraints gb;
	Vector<Vector<Object>> onView;
	private JButton prevBtn;
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1000, 500);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		TableField table = new TableField();
		f.getContentPane().add("Center", table);
		f.setVisible(true);
		f.pack();
		Dao d = new Dao();
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		table.initiateList(d.getList());
		sc.nextLine();
		table.initiateList(d.getList());
		while(true) {
			sc.nextLine();
			System.out.println(table.onView);
		}
	}

	public TableField() {
		//setBackground(Color.white);
		setOpaque(false);
		ref = new ImageIcon("c:/refresh.png");
		table = new JTable() {
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
			                case 0:
			                	return Boolean.class;
			                case 1:
			                	return Boolean.class;
			                case 2:
			                	return Boolean.class;
			                case 16:
			                	return Boolean.class;
			                default:
			                	return String.class;
				}
			}
			public boolean isCellEditable(int rowIndex, int columnIndex){
				if(columnIndex == 0||columnIndex == 1||columnIndex == 2||columnIndex == 16) return true;
				else return false; //Or whatever column index you want to be editable
			}
		};
		model = new TableModel();
		table.setModel(model);
		scroll = new JScrollPane(table);
		sortString = new String [] {"입실일 오름차순", "입실일 내림차순","퇴실일 오름차순", "퇴실일 내림차순","기간 순", "고객 성함 순","고객 국적순", "객실 순", "호실 순", "가격 순"};


		String attComboPath = "attribute.txt";
		setPreferredSize(new Dimension(1000,550));  
		orange = new Color(16753510);
		deepOrange = new Color(16735283);
		gray = new Color(14277081);
		
		
		topPanel = new JPanel(new BorderLayout(80,0));
		//topPanel.setBackground(deepOrange);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		topPanel.setPreferredSize(new Dimension(1000,50));
		
		tableTitlePanel = new JPanel();
		tableTitlePanel.setOpaque(false);
		tableTitlePanel.setPreferredSize(new Dimension(200,25));		
		tableTitle = new JLabel("고 객  정 보  리 스 트");
		Color pink = new Color(13041721);
		tableTitle.setForeground(Color.white);
		tableTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tableTitle.setPreferredSize(new Dimension(200,25));
		tableTitle.setHorizontalAlignment(JLabel.CENTER);
		tableTitle.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
		tableTitlePanel.add(tableTitle, BorderLayout.CENTER);
		
		tableSortPanel = new JPanel(new GridBagLayout());
		tableSortPanel.setPreferredSize(new Dimension(250,30));
		tableSortPanel.setOpaque(true);		
		tableSortPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
		sortView = new Combo(sortString);		 
		sortView.setSize(200,30);
		refreshBtn = new JButton();
		refreshBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		refreshBtn.setText("refresh");
		ref = new ImageIcon("e:\\ref.png");
		refreshBtn.setIcon(ref);
		
		gb = new GridBagConstraints();
        gb.insets = new Insets(3, 3, 3, 3); // Put some space between elements for nicer look
        gb.anchor = GridBagConstraints.EAST;
        gb.gridwidth = GridBagConstraints.LINE_START; 
        gb.weightx = 2; // Cell takes up all extra horizontal space
        gb.fill = GridBagConstraints.HORIZONTAL; 
	
        tableSortPanel.add(sortView, gb);
		gb.fill = GridBagConstraints.NONE;
		tableSortPanel.add(refreshBtn,gb);

		
		topPanel.add(tableTitlePanel, BorderLayout.CENTER);
		topPanel.add(tableSortPanel, BorderLayout.EAST);
		topPanel.setBackground(pink);
		
			
		bottomPanel = new JPanel(new BorderLayout(40,40));
		CheckControl cB = new CheckControl();
		//bottomPanel.setBackground(orange);
		
		listChoicePanel = new JPanel(new GridBagLayout());
		//listChoicePanel.setBounds(0, 425, 940, 25);
		//listChoicePanel.setBackground(orange);
		listChoicePanel.setOpaque(false);
		listChoicePanel.setPreferredSize(new Dimension(450,30));
		
		
		//listChoicePanel.setLayout(null);
		JLabel status = new JLabel("고객 상태");
		status.setFont(new Font("돋움체", Font.BOLD, 13));
		//status.setBackground(Color.gray);
		status.setHorizontalAlignment(JLabel.CENTER);
		status.setVerticalAlignment(JLabel.CENTER);
		status.setForeground(Color.white);
		//status.setOpaque(true);
		//status.setBounds(10, 2, 70, 20);
		status.setPreferredSize(new Dimension(80,25));
		listChoicePanel.add(status);

		cAll = new JCheckBox("모두 보기");		
		cAll.setPreferredSize(new Dimension(80,25));
		//cAll.setBounds(77, 2, 80, 20);
		cAll.setSelected(true);
		cAll.setOpaque(false);
		cAll.addItemListener(cB);
		cBoxBook = new JCheckBox("예약 중");
		//cBoxBook.setBounds(161, 2, 80, 20);
		cBoxBook.setPreferredSize(new Dimension(70,20));
		cBoxBook.setSelected(true);
		cBoxBook.setOpaque(false);
		cBoxBook.addItemListener(cB);
		cBoxIn = new JCheckBox("투숙 중");
		//cBoxIn.setBounds(237, 2, 80, 20);
		cBoxIn.setPreferredSize(new Dimension(70,20));
		cBoxIn.setSelected(true);
		cBoxIn.setOpaque(false);
		cBoxIn.addItemListener(cB);
		cBoxOut = new JCheckBox("퇴실");
		//cBoxOut.setBounds(309, 2, 80, 20);
		cBoxOut.setPreferredSize(new Dimension(60,20));
		cBoxOut.setSelected(true);
		cBoxOut.setOpaque(false);
		cBoxOut.addItemListener(cB);
		cBoxCancel = new JCheckBox("취소");
		//cBoxCancel.setBounds(369, 2, 80, 20);
		cBoxCancel.setPreferredSize(new Dimension(60,20));
		cBoxCancel.setSelected(true);
		cBoxCancel.setOpaque(false);
		cBoxCancel.addItemListener(cB);
		cBoxPay = new JCheckBox("지불");
		//cBoxPay.setBounds(369, 2, 80, 20);
		cBoxPay.setPreferredSize(new Dimension(60,20));
		cBoxPay.setSelected(true);
		cBoxPay.setOpaque(false);
		cBoxPay.addItemListener(cB);
		cBoxPay.setName("pay");
		cAll.setForeground(Color.white);
		cBoxBook.setForeground(Color.white);
		cBoxIn.setForeground(Color.white);
		cBoxOut.setForeground(Color.white);
		cBoxPay.setForeground(Color.white);
		cBoxCancel.setForeground(Color.white);
		listChoicePanel.add(cAll);		
		listChoicePanel.add(cBoxBook);
		listChoicePanel.add(cBoxIn);
		listChoicePanel.add(cBoxOut);	
		listChoicePanel.add(cBoxCancel);
		listChoicePanel.add(cBoxPay);
		
		

				
	
		//table.setBounds(1, 25, 130, 40);
		//table.setBorder(new LineBorder(deepOrange, 1));
		table.setRowHeight(20);
		//add(table);
		//scroll.setBounds(0, 40, 940, 375);
		//add(scroll);
				
		cAll.setName("all");
		cBoxBook.setName("book");
		cBoxIn.setName("in");
		cBoxOut.setName("out");
		cBoxCancel.setName("cancel");
		
		searchPanel = new JPanel(new GridBagLayout());
		srcAtt = new Combo(attComboPath);
		
		searchPanel.add(srcAtt);
		searchPanel.setPreferredSize(new Dimension(450,25));
		srcAtt.setName("searchAtt");
		srcAtt.setSize(92, 21);
		srcField = new JTextField();
		srcField.setPreferredSize(new Dimension(300,25));
		//srcField.setBounds(552, 2, 255, 21);
		
		LineBorder line = new LineBorder(new Color(16753510), 1, true);
		
		
		srcField.setColumns(99999);	
		srcField.setBorder(line);
		deepOrange = new Color(16735283);
		LineBorder wline = new LineBorder(Color.white, 1, true);

		MetalButtonUI btn = new MetalButtonUI() {			
			@Override
			public void uninstallDefaults(AbstractButton b) {
				// TODO Auto-generated method stub
				super.uninstallDefaults(b);						
			}
			@Override
		    protected Color getSelectColor() {
		        return pink;
		    }			
		};
		MetalButtonUI btn2 = new MetalButtonUI() {			
			@Override
			public void uninstallDefaults(AbstractButton b) {
				// TODO Auto-generated method stub
				super.uninstallDefaults(b);				
			}
			@Override
		    protected Color getSelectColor() {
		        return Color.white;
		    }			
		};
		srcBtn = new JButton("검 색");
		//srcBtn.setBounds(812, 1, 67, 23);
		srcBtn.setSize(67,23);
		srcBtn.setBorder(wline);
		srcBtn.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
		srcBtn.setBackground(deepOrange);
		srcBtn.setForeground(Color.white);
		srcBtn.setUI(btn);
		srcBtn.setOpaque(true);
		srcBtn.setFocusPainted(false);
		prevBtn = new JButton("이 전");
		prevBtn.setForeground(pink);
		prevBtn.setBorder(wline);
		prevBtn.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		prevBtn.setUI(btn2);
		prevBtn.setFocusPainted(false);
		prevBtn.setBackground(orange);
		//prevBtn.setBounds(880, 1, 60, 23);
		prevBtn.setSize(40,23);
		refreshBtn.setUI(btn2);
		refreshBtn.setFocusPainted(false);
		refreshBtn.setBackground(orange);
		refreshBtn.setBorder(wline);
		refreshBtn.setForeground(pink);
		refreshBtn.setBorder(BorderFactory.createEmptyBorder(2,10,2,10));
		
		
	    gb.anchor = GridBagConstraints.WEST;
	    gb.gridwidth = 2;
	    gb.weightx = 0; // Cell takes up all extra horizontal space
	    gb.fill = GridBagConstraints.NONE;
		
		searchPanel.add(srcAtt,gb);
		gb.anchor = GridBagConstraints.BASELINE;
		gb.gridwidth = 99999;
		gb.fill = GridBagConstraints.HORIZONTAL;
		gb.ipadx = 200;
		gb.ipady = 5;
	    gb.weightx = 1; // Cell takes up all extra horizontal space

		searchPanel.add(srcField,gb);
	    gb.fill = GridBagConstraints.NONE;
		//gb.anchor = GridBagConstraints.EAST;
		gb.weightx = 0;
		gb.anchor = GridBagConstraints.EAST;
		gb.gridwidth = 1;
		gb.ipadx = 0;
		gb.ipady = 0;
		searchPanel.add(srcBtn,gb);
		searchPanel.add(prevBtn,gb);
		topPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		bottomPanel.add(listChoicePanel, BorderLayout.WEST);
		bottomPanel.add(searchPanel, BorderLayout.EAST);
		//setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setLayout(new BorderLayout(10,0));
		add(topPanel, BorderLayout.NORTH);
	
		searchPanel.setOpaque(false);
		add(scroll,BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		topPanel.setBackground(pink);
		bottomPanel.setBackground(pink);
		tableSortPanel.setOpaque(false);
		setOpaque(false);
		
		srcField.setName("s");

		srcBtn.setName("search");		

		sortView.setName("sortView");

		check = new ButtonGroup();
		//check.add(cAll);
		//check.add(cBoxBook);
		//check.add(cBoxIn);
		//check.add(cBoxOut);
		//check.add(cBoxCancel);	
		//check.add(cBoxPay);
		columnModel = table.getColumnModel();
		columnModel.removeColumn(columnModel.getColumn(0));
		columnModel.getColumn(0).setPreferredWidth(30);
		columnModel.getColumn(1).setPreferredWidth(30);
		columnModel.getColumn(2).setPreferredWidth(30);
		columnModel.getColumn(3).setPreferredWidth(115);
		columnModel.getColumn(4).setPreferredWidth(115);
		columnModel.getColumn(5).setPreferredWidth(30);
		columnModel.getColumn(6).setPreferredWidth(30);
		columnModel.getColumn(7).setPreferredWidth(80);
		columnModel.getColumn(8).setPreferredWidth(60);
		columnModel.getColumn(9).setPreferredWidth(80);
		columnModel.getColumn(10).setPreferredWidth(100);
		columnModel.getColumn(11).setPreferredWidth(60);
		columnModel.getColumn(12).setPreferredWidth(100);
		columnModel.getColumn(13).setPreferredWidth(100);
		columnModel.getColumn(14).setPreferredWidth(100);
		columnModel.getColumn(15).setPreferredWidth(100);
		columnModel.getColumn(16).setPreferredWidth(30);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("이전")) {
					returnList();
				} else if(e.getActionCommand().equals("검 색")) {
					if(srcAtt.getSelectedIndex()<1) {
						popUp("검색 속성을 선택하세요");
						return;
					} 
					if(srcField.getText().equals("")) {
						popUp("검색어를 입력하세요");
						return;
					}
					searchList();
					return;
					//System.out.println(srcAtt.getSelectedIndex());
				} else {
					listener.refreshList();
				}
			}
		};
		srcBtn.addActionListener(a);		
		refreshBtn.addActionListener(a);		
		prevBtn.addActionListener(a);
		TableModelListener t = new TableModelListener() {			
			@Override
			public void tableChanged(TableModelEvent e) {
				row.tableChanged(e);
				//System.out.println("모델체인지드");
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
			    //System.out.println("로우"+row);
			    int column = e.getColumn();
			   // System.out.println("로우"+row);
			    if (column == 1||column == 2||column == 3||column == 17) {
			        TableModel model = (TableModel) e.getSource();
			        Boolean checked = (Boolean) model.getValueAt(row, column);
			        Vector<Object> c = model.getRow(row);
			        //System.out.println(c.toString());
			        if (checked) {
			        	switch(column) {
			        	case 1:
			        		c.set(1, true);
			        		listener.editDataStatus(c);
			        		popUp(model.getValueAt(row, 8)+"님이 예약 취소하신 상태로 변경되었습니다.");
			        		break;
			        		//if( view.ask("예약") )// row 데이터 업글.그거 테이블엔어케 ..적용하죠?
			        	case 2:
			        		c.set(2, true);
			        		popUp(model.getValueAt(row, 8)+"님이 체크인하신 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        		//if( view.ask("입실") )
			        	case 3:
			        		c.set(3, true);
			        		popUp(model.getValueAt(row, 8)+"님이 체크아웃하신 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        		//if( view.ask("퇴실") )
			        	case 17:
			        		c.set(17, true);
			        		popUp(model.getValueAt(row, 8)+"님이 지불하신 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        	}
			        } else {
			        	switch(column) {
			        	case 1:
			        		c.set(1, false);
			        		popUp(model.getValueAt(row, 8)+"님이 예약 취소하신 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        		//if( view.ask("예약") )// row 데이터 업글.그거 테이블엔어케 ..적용하죠?
			        	case 2:
			        		c.set(2, false);
			        		popUp(model.getValueAt(row, 8)+"님이 체크인하시지 않은 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        		//if( view.ask("입실") )
			        	case 3:
			        		c.set(3, false);
			        		popUp(model.getValueAt(row, 8)+"님이 체크아웃하시지 않은 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        		//if( view.ask("퇴실") )
			        	case 17:
			        		c.set(17, false);
			        		popUp(model.getValueAt(row, 8)+"님이 지불하시지 않은 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        	}
			        }
			    }
			}
		};
		model.addTableModelListener(t);
		
		sortView.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println(((Combo)e.getSource()).getSelectedItem());
				sortList();
			}
		});
		row = new RowHeader(table);
		scroll.setRowHeaderView(row);
		scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, row.getTableHeader());	
		table.addPropertyChangeListener(row);
		
	}
	
	public TableField(Vector<Vector<Object>> dataList) {
		this();
		initiateList(dataList);
	}
	
	public Vector<Object> dataSelected(int row) { //인풋에게 데이터 주기
		return onView.get(row);
	}
	
	//테이블 데이터 조종
	public void addRow(Vector<Object> v) {
		model.addRow(v);
	}
	
	public void updateRow(Vector<Object> v) {
		model.updateRow(table.getSelectedRow(), v);
	}
	
	public void removeRow() {
		model.removeRow(table.getSelectedRow());
	}
	
	
	//테이블 리스트 관리
	private Boolean match(Object a, Object b) {
		if(a instanceof String && b instanceof String) {
			if(a.toString().toUpperCase().equals(b.toString().toUpperCase())) return true;
			else return false;
		}
		return a.equals(b);
	}
	//1입실일  2퇴실일 3성함  4국가 5여권 6객실 7호수 8이메일 9	전화번호 10요청
	private void searchList() {
		String s = srcField.getText();
		System.out.println("셀렉티드인덱스"+srcAtt.getSelectedIndex());
		src = new Vector<Vector<Object>>();
		switch(srcAtt.getSelectedIndex()) {
		case 1://1입실일 
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(5), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 2://
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(6), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 3:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(8), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 4:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(9), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 5:// 2퇴실일 3성함  4국가 5여권 6객실 7호수 8이메일 9전화번호 10요청
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(10), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 6:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(11), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 7:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(12), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 8:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(13), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 9:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(14), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 10:
			for(int i = 0; i<onView.size(); i++) {
				if(match(onView.get(i).get(15), s)) {
					src.add(onView.get(i));
				}
			}
			break;
		}
		//System.out.println("서치설정후온뷰:"+onView);
		model.clearAllRows();
		model.setdataVector(src);
	}

	
	public void returnList() {
		//System.out.println("리턴"+onView);
		model.clearAllRows();
		model.setdataVector(onView);	
	}	
	
	//1:all 2:book 3:in 4:out 5:cancel
	public void emptyList() {
		//System.out.println("온뷰비움");
		onView = new Vector<Vector<Object>>();
		model.clearAllRows();
		model.setdataVector(onView);		
	}
	
	public void fullList() {
		//System.out.println("온뷰 풀리스트");
		onView = new Vector<Vector<Object>>(all);
		sortList();
	}
	
	public void initiateList(Vector<Vector<Object>> v) {
		all = new Vector<Vector<Object>>();
		onView = new Vector<Vector<Object>>();
		book = new Vector<Vector<Object>>();
		in = new Vector<Vector<Object>>();
		out = new Vector<Vector<Object>>();
		pay = new Vector<Vector<Object>>();
		cancel = new Vector<Vector<Object>>();
		for(Vector<Object> o:v) {
			all.add(o);
			onView.add(o);
			if(o.get(3).equals(Boolean.TRUE)) out.add(o);
			else if(o.get(2).equals(Boolean.TRUE)) in.add(o);
			else if(o.get(1).equals(Boolean.TRUE)) book.add(o);
			else cancel.add(o);
		}
		for(Vector<Object> o:v) {
			if(o.get(17).equals(Boolean.TRUE)) pay.add(o);
		}
		sortList();	
		System.out.println("all"+all);
		System.out.println("book"+book);
		System.out.println("in"+in);
		System.out.println("out"+out);
		System.out.println("cancel"+cancel);
		System.out.println("pay"+pay);
	}
	
	
	//기타 구성
	public Boolean ask(String s) {
		int j = JOptionPane.showConfirmDialog(null, s);
		if(j==0) return true;
		else return false;
	}
	
	private void popUp(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	public void addListener(EventListener l) {
		this.listener = l;
	}
	
	
	public void addList(Vector<Vector<Object>> dataVector) {
		for(Vector<Object> v: dataVector) {
			onView.add(v);
		}
		//System.out.println("애드후온뷰"+onView);
		sortList();
		//System.out.println("클리어후온뷰"+onView);

	}
    
	public void removeList(Vector<Vector<Object>> dataVector) {		
		Iterator<Vector<Object>> it = onView.iterator();
		while(it.hasNext()) {
			Vector<Object> o = it.next();
			for(int i = 0; i<dataVector.size(); i++) {				
				if(o.equals(dataVector.get(i))) {
					it.remove();
					break;
				}
			}
		}
		sortList();
	}

	private void sortList() {
		System.out.println("sortList"+sortView.getSelectedIndex());
		sort(sortView.getSelectedIndex(), onView);
	//	System.out.println("솔트후"+onView);
		model.clearAllRows();
		model.setdataVector(onView);
	}
	
	public void sort(int field, Vector<Vector<Object>> itemLocationList) {
	//	System.out.println("솔트전"+onView);
	    Collections.sort(itemLocationList, new Comparator<Vector<Object>>() {
	        @Override
	        public int compare(Vector<Object> a, Vector<Object> b) {
	        	switch(field) {
	    		case -1://입실내림오름
	    			try {
	    				//System.out.println("입실내림");
	    				SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	    				Date d = form.parse((String) a.get(4));
	    				Date d2 = form.parse((String) b.get(4));
	    				//System.out.println(d+"<>"+d2+":"+d.compareTo(d2));
	    				return d.compareTo(d2);
	    			} catch (ParseException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			 return 0;
	    		case 1:
    				try {
	    				SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/DD HH:mm");
	    				Date d = form.parse((String) ((Vector)a).get(4));
	    				Date d2 = form.parse((String) ((Vector) b).get(4));
	    				return d2.compareTo(d);
	    			} catch (ParseException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			 return 0;
	    		case 2://퇴실내림오름
	    			try {
	    				SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/DD HH:mm");
	    				Date d = form.parse((String) ((Vector)a).get(5));
	    				Date d2 = form.parse((String) ((Vector) b).get(5));
	    				return d.compareTo(d2);
	    			} catch (ParseException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			 return 0;
	    		case 3:
	    			try {
	    				SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/DD HH:mm");
	    				Date d = form.parse((String) ((Vector)a).get(5));
	    				Date d2 = form.parse((String) ((Vector) b).get(5));
	    				return d2.compareTo(d);
	    			} catch (ParseException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			 return 0;
	    		case 5://이름
	    			return a.get(8).toString().compareTo(b.get(8).toString());
	    		case 6://국적
	    			return a.get(9).toString().compareTo(b.get(9).toString());
	    		case 7://룸타입
	    			return a.get(11).toString().compareTo(b.get(11).toString());
	    		case 8://룸넘버
	    			return a.get(12).toString().compareTo(b.get(12).toString());
	    		case 9://가격
	    			return b.get(16).toString().compareTo(a.get(16).toString());
    			default:
    				return 0;
	    		}
	        	
	        }

	    });
	}

	
	//체크박스 컨트롤하는 내부클래스 커스텀 아이템리스터
	class CheckControl implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(e.getStateChange()+" "+((JCheckBox)e.getSource()).getName() );
			if(((JCheckBox)e.getSource()).getName().equals("all")) {
				switch(((JCheckBox)e.getSource()).getName()+e.getStateChange()) {
				case "all1":
					cBoxCancel.setSelected(true);
					cBoxBook.setSelected(true);
					cBoxIn.setSelected(true);
					cBoxOut.setSelected(true);
					cBoxPay.setSelected(true);
					fullList();
					break;
				case "all2":
					cBoxCancel.setSelected(false);
					cBoxBook.setSelected(false);
					cBoxIn.setSelected(false);
					cBoxOut.setSelected(false);
					cBoxPay.setSelected(false);
					emptyList();
					break;
				}		
			} else {
				switch( (((JCheckBox)e.getSource()).getName())+e.getStateChange()) {
				case "book1":
					addList(book);
					break;
				case "in1":
					addList(in);
					break;
				case "out1":
					addList(out);
					break;
				case "cancel1":
					addList(cancel);
					break;
				case "pay1":
					addList(pay);
					break;
				case "book2":
					removeList(book);
					break;
				case "in2":
					removeList(in);
					break;
				case "out2":
					removeList(out);
					break;
				case "cancel2":
					removeList(cancel);
					break;
				case "pay2":
					removeList(pay);
					break;
				}
			}
			//System.out.println("체크박스컨트롤이후 온뷰:"+onView);
		}		
	}
}
