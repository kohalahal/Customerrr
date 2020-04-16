package com.koala.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumnModel;
import org.json.JSONArray;

import com.koala.controller.WindowHandler;
import com.koala.dao.Dao;
import com.koala.view.custom.Combo;
import com.koala.view.custom.RowHeader;
import com.koala.view.custom.TableModel;

public class TableField extends JPanel {
	Color orange;

	protected JTable table;
	protected RowHeader row;
	protected TableModel model;
	protected JScrollPane scroll;
	protected TableColumnModel columnModel;	

	protected Combo sortView;
	protected JCheckBox cAll;
	protected JCheckBox cBoxCancel;
	protected JCheckBox cBoxBook;
	protected JCheckBox cBoxIn;
	protected JCheckBox cBoxOut;
	
	protected Combo srcAtt;	
	protected JTextField srcField;
	protected JButton srcBtn;
	
	String[] sortString;
	private JPanel panel;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1280, 450);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		TableField table = new TableField();
		table.setBounds(0, 0, 900, 300);
		f.getContentPane().add(table);
		f.setVisible(true);
		Dao d = new Dao();
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		table.refreshTable(d.getList());
	}

	public TableField() {
		setSize(940, 400);
		sortString = new String [] {"입실일 내림차순", "입실일 오름차순","퇴실일 내립차순", "퇴실일 오름차순","고객 성함 순","고객 국적순", "객실 순", "호실 순"};
		orange = new Color(16753510);
		setLayout(null);
		JPanel tableTitlePanel = new JPanel();
		tableTitlePanel.setBackground(orange);
		tableTitlePanel.setBounds(0, 0, 940, 31);
		add(tableTitlePanel);
		tableTitlePanel.setLayout(null);
		JLabel lblNewLabel = new JLabel("고객 정보 리스트");
		lblNewLabel.setBounds(136, 6, 279, 21);
		tableTitlePanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("한컴돋움", Font.BOLD, 14));
		
		sortView = new Combo(sortString);
		sortView.setBounds(813, 5, 115, 23);
		tableTitlePanel.add(sortView);
		sortView.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 13));
		
		JPanel sortPanel = new JPanel();
		sortPanel.setBounds(0, 372, 455, 25);
		sortPanel.setBackground(new Color(14277081));
		add(sortPanel);
		sortPanel.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("고객 상태");
		lblNewLabel_1.setBounds(25, 2, 70, 20);
		sortPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("돋움체", Font.BOLD, 13));
		
		cAll = new JCheckBox("모두");
		cAll.setBounds(112, 2, 60, 20);
		cAll.setSelected(true);
		cAll.setOpaque(false);
		cAll.addItemListener(new checkControl());
		sortPanel.add(cAll);
		cBoxCancel = new JCheckBox("취소");
		cBoxCancel.setBounds(392, 2, 60, 20);
		cBoxCancel.setSelected(true);
		cBoxCancel.setOpaque(false);
		sortPanel.add(cBoxCancel);
		cBoxBook = new JCheckBox("예약");
		cBoxBook.setBounds(182, 2, 60, 20);
		cBoxBook.setSelected(true);
		cBoxBook.setOpaque(false);
		sortPanel.add(cBoxBook);
		cBoxIn = new JCheckBox("투숙");
		cBoxIn.setBounds(252, 2, 60, 20);
		cBoxIn.setSelected(true);
		cBoxIn.setOpaque(false);
		sortPanel.add(cBoxIn);
		cBoxOut = new JCheckBox("퇴실");
		cBoxOut.setBounds(322, 2, 60, 20);
		cBoxOut.setSelected(true);
		cBoxOut.setOpaque(false);
		sortPanel.add(cBoxOut);
		
		table = new JTable() {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
			                case 0:
			                	return Boolean.class;
			                case 1:
			                	return Boolean.class;
			                case 2:
			                	return Boolean.class;
			   
			                default:
			                	return String.class;
				}
			}
			public boolean isCellEditable(int rowIndex, int columnIndex){
				if(columnIndex == 0||columnIndex == 1||columnIndex == 2) return true;
				else return false; //Or whatever column index you want to be editable
				}
			};		
			row = new RowHeader(table);
			model = new TableModel();
			table.setModel(model);
			table.setBounds(1, 25, 130, 40);
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setRowHeight(20);
			add(table);
			scroll = new JScrollPane(table);
			scroll.setBounds(0, 40, 940, 330);
			add(scroll);
			columnModel = table.getColumnModel();
			columnModel.removeColumn(columnModel.getColumn(0));
			columnModel.getColumn(0).setPreferredWidth(30);
			columnModel.getColumn(1).setPreferredWidth(30);
			columnModel.getColumn(2).setPreferredWidth(30);
			columnModel.getColumn(3).setPreferredWidth(86);
			columnModel.getColumn(4).setPreferredWidth(86);
			columnModel.getColumn(5).setPreferredWidth(30);
			columnModel.getColumn(6).setPreferredWidth(80);
			columnModel.getColumn(7).setPreferredWidth(80);
			columnModel.getColumn(8).setPreferredWidth(60);
			columnModel.getColumn(9).setPreferredWidth(60);
			columnModel.getColumn(10).setPreferredWidth(40);
			columnModel.getColumn(11).setPreferredWidth(100);
			columnModel.getColumn(12).setPreferredWidth(100);
			columnModel.getColumn(13).setPreferredWidth(100);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			
			panel = new JPanel();
			panel.setBounds(457, 372, 483, 25);
			add(panel);
			panel.setLayout(null);
			
					srcAtt = new Combo();
					srcAtt.setBounds(2, 2, 90, 21);
					panel.add(srcAtt);
					
					srcField = new JTextField();
					srcField.setBounds(95, 2, 306, 21);
					panel.add(srcField);
					srcField.setColumns(10);
					
					srcBtn = new JButton("검 색");
					srcBtn.setBounds(403, 1, 80, 23);
					panel.add(srcBtn);
	}
		
	public void addRow(Vector<Object> v) {
		model.addRow(v);
	}
	
	public void updateRow(Vector<Object> v) {
		model.updateRow(table.getSelectedRow(), v);
	}
	
	public void removeRow() {
		model.removeRow(table.getSelectedRow());
	}
	
	public void refreshTable(Vector<Vector<Object>> v) {
		model.clearAllRows();
		model.setdataVector(v);		
	}

	public void addListener(WindowHandler w) {
		model.addTableModelListener(w);
		table.getSelectionModel().addListSelectionListener(w);
		
		cAll.addItemListener(w);
		cBoxCancel.addItemListener(w);
		cBoxBook.addItemListener(w);
		cBoxIn.addItemListener(w);
		cBoxOut.addItemListener(w);
		
		cAll.setName("all");
		cBoxCancel.setName("cancel");
		cBoxBook.setName("book");
		cBoxIn.setName("in");
		cBoxOut.setName("out");
		
		sortView.addItemListener(w);
		sortView.setName("sortView");
				
		srcAtt.addItemListener(w);
		srcAtt.setName("searchAtt");
		srcField.addKeyListener(w);
		srcField.setName("s");
		srcBtn.addActionListener(w);
		srcBtn.setName("search");
	}
	
	class checkControl implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(e.getStateChange());
			switch(e.getStateChange()) {
			case 1:
				cBoxCancel.setSelected(true);
				cBoxBook.setSelected(true);
				cBoxIn.setSelected(true);
				cBoxOut.setSelected(true);
				break;
			case 2:
				cBoxCancel.setSelected(false);
				cBoxBook.setSelected(false);
				cBoxIn.setSelected(false);
				cBoxOut.setSelected(false);
				break;
			}			
		}		
	}

}
