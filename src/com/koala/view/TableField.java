package com.koala.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import org.json.JSONArray;

import com.koala.controller.EventListener;
import com.koala.controller.WindowHandler;
import com.koala.dao.Dao;
import com.koala.view.custom.Combo;
import com.koala.view.custom.RowHeader;
import com.koala.view.custom.TableModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TableField extends JPanel {
	Color orange;

	protected JTable table;
	protected RowHeader row;
	protected TableModel model;
	protected JScrollPane scroll;
	protected TableColumnModel columnModel;	

	protected Combo sortView;
	ButtonGroup check;
	protected JCheckBox cAll;
	protected JCheckBox cBoxCancel;
	protected JCheckBox cBoxBook;
	protected JCheckBox cBoxIn;
	protected JCheckBox cBoxOut;
	
	protected Combo srcAtt;	
	protected JTextField srcField;
	protected JButton srcBtn;
	JButton refreshBtn;
	
	private EventListener listener;

	String[] sortString;
	JPanel tableTitlePanel;
	JPanel listChoicePanel;
	
	Vector<Vector<Object>> all;
	Vector<Vector<Object>> book;
	Vector<Vector<Object>> in;
	Vector<Vector<Object>> out;
	Vector<Vector<Object>> cancel;
	Vector<Vector<Object>> src;
	
	Vector<Vector<Object>> onView;
	private JButton prevBtn;
	
	
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
		table.initiateList(d.getList());
		sc.nextLine();
		table.initiateList(d.getList());
	}

	public TableField() {
		String attComboPath = "attribute.txt";
		setSize(940, 455);
		sortString = new String [] {"입실일 내림차순", "입실일 오름차순","퇴실일 내립차순", "퇴실일 오름차순","고객 성함 순","고객 국적순", "객실 순", "호실 순"};
		orange = new Color(16753510);
		setLayout(null);
		tableTitlePanel = new JPanel();
		tableTitlePanel.setBackground(orange);
		tableTitlePanel.setBounds(0, 0, 940, 31);
		add(tableTitlePanel);
		tableTitlePanel.setLayout(null);
		JLabel lblNewLabel = new JLabel("고객 정보 리스트");
		lblNewLabel.setBounds(136, 6, 279, 21);
		tableTitlePanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("한컴돋움", Font.BOLD, 14));
		
		sortView = new Combo(sortString);
		sortView.setBounds(716, 3, 139, 25);
		tableTitlePanel.add(sortView);
		sortView.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 13));
		
		listChoicePanel = new JPanel();
		listChoicePanel.setBounds(0, 425, 940, 25);
		listChoicePanel.setBackground(new Color(14277081));
		add(listChoicePanel);
		listChoicePanel.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("고객 상태");
		lblNewLabel_1.setBounds(10, 2, 70, 20);
		listChoicePanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("돋움체", Font.BOLD, 13));
		check = new ButtonGroup();
		check.add(cAll);
		check.add(cBoxBook);
		check.add(cBoxIn);
		check.add(cBoxOut);
		check.add(cBoxCancel);	
		cAll = new JCheckBox("모두 보기");		
		cAll.setBounds(77, 2, 80, 20);
		cAll.setSelected(true);
		cAll.setOpaque(false);
		CheckControl cB = new CheckControl();
		cAll.addItemListener(cB);
		listChoicePanel.add(cAll);
		cBoxCancel = new JCheckBox("취소");
		cBoxCancel.setBounds(369, 2, 80, 20);
		cBoxCancel.setSelected(true);
		cBoxCancel.setOpaque(false);
		cBoxCancel.addItemListener(cB);
		listChoicePanel.add(cBoxCancel);
		cBoxBook = new JCheckBox("예약 중");
		cBoxBook.setBounds(161, 2, 80, 20);
		cBoxBook.setSelected(true);
		cBoxBook.setOpaque(false);
		cBoxBook.addItemListener(cB);
		listChoicePanel.add(cBoxBook);
		cBoxIn = new JCheckBox("투숙 중");
		cBoxIn.setBounds(237, 2, 80, 20);
		cBoxIn.setSelected(true);
		cBoxIn.setOpaque(false);
		cBoxIn.addItemListener(cB);
		listChoicePanel.add(cBoxIn);
		cBoxOut = new JCheckBox("퇴실");
		cBoxOut.setBounds(309, 2, 80, 20);
		cBoxOut.setSelected(true);
		cBoxOut.setOpaque(false);
		cBoxOut.addItemListener(cB);
		listChoicePanel.add(cBoxOut);		
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
			                case 3:
			                	return Boolean.class;
			                default:
			                	return String.class;
				}
			}
			public boolean isCellEditable(int rowIndex, int columnIndex){
				if(columnIndex == 0||columnIndex == 1||columnIndex == 2||columnIndex == 3) return true;
				else return false; //Or whatever column index you want to be editable
			}
		};		
		
		model = new TableModel();
		table.setModel(model);
		table.setBounds(1, 25, 130, 40);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowHeight(20);
		add(table);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 40, 940, 375);
		add(scroll);
		columnModel = table.getColumnModel();
		columnModel.removeColumn(columnModel.getColumn(0));
		columnModel.getColumn(0).setPreferredWidth(30);
		columnModel.getColumn(1).setPreferredWidth(30);
		columnModel.getColumn(2).setPreferredWidth(30);
		columnModel.getColumn(3).setPreferredWidth(30);
		columnModel.getColumn(4).setPreferredWidth(105);
		columnModel.getColumn(5).setPreferredWidth(105);
		columnModel.getColumn(6).setPreferredWidth(30);
		columnModel.getColumn(7).setPreferredWidth(80);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(60);
		columnModel.getColumn(10).setPreferredWidth(60);
		columnModel.getColumn(11).setPreferredWidth(40);
		columnModel.getColumn(12).setPreferredWidth(100);
		columnModel.getColumn(13).setPreferredWidth(100);
		columnModel.getColumn(14).setPreferredWidth(100);
		columnModel.getColumn(15).setPreferredWidth(100);


		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		row = new RowHeader(table);
		scroll.setRowHeaderView(row);
		scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER,
		    row.getTableHeader());
			
		cAll.setName("all");
		cBoxBook.setName("book");
		cBoxIn.setName("in");
		cBoxOut.setName("out");
		cBoxCancel.setName("cancel");
		srcAtt = new Combo(attComboPath);
		srcAtt.setBounds(450, 2, 92, 21);
		listChoicePanel.add(srcAtt);
		srcAtt.setName("searchAtt");
		srcField = new JTextField();
		srcField.setBounds(552, 2, 255, 21);
		listChoicePanel.add(srcField);
		srcField.setColumns(10);		
		srcField.setName("s");
		srcBtn = new JButton("검 색");
		srcBtn.setBounds(812, 1, 67, 23);
		listChoicePanel.add(srcBtn);
		srcBtn.setName("search");
		
		prevBtn = new JButton("이전");
		prevBtn.setBounds(880, 1, 60, 23);
		listChoicePanel.add(prevBtn);
		
		
		
//		ItemListener i = new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//listener.changeDataList();
//			}
//		};
//		cAll.addItemListener(i);
//		cBoxCancel.addItemListener(i);
//		cBoxBook.addItemListener(i);
//		cBoxIn.addItemListener(i);
//		cBoxOut.addItemListener(i);
//		sortView.addItemListener(i);
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(e.getActionCommand());
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("이전")) {
					returnList();
				} else if(e.getActionCommand().equals("검색")) {
					if(srcAtt.getSelectedIndex()<1) {
						popUp("검색 속성을 선택하세요");
						return;
					} 
					if(srcField.getText().equals("")) {
						popUp("검색어를 입력하세요");
						return;
					}
					searchList();
					//System.out.println(srcAtt.getSelectedIndex());
				} else {
					listener.refreshList();
				}
			}
		};
		srcBtn.addActionListener(a);
		sortView.setName("sortView");
		
		refreshBtn = new JButton();
		refreshBtn.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 10));
		refreshBtn.setText("새로 고침");
		
		refreshBtn.setBounds(899, 4, 29, 25);
		tableTitlePanel.add(refreshBtn);
		refreshBtn.setName("refresh");
		refreshBtn.setIcon(new ImageIcon("E:\\ref.png"));
		refreshBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		refreshBtn.addActionListener(a);
		
		prevBtn.addActionListener(a);
		TableModelListener t = new TableModelListener() {			
			@Override
			public void tableChanged(TableModelEvent e) {
				row.tableChanged(e);
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
			    //System.out.println("로우"+row);
			    int column = e.getColumn();
			   // System.out.println("로우"+row);
			    if (column == 1||column == 2||column == 3||column == 4) {
			        TableModel model = (TableModel) e.getSource();
			        Boolean checked = (Boolean) model.getValueAt(row, column);
			        Vector<Object> c = model.getRow(row);
			        
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
			        	case 4:
			        		c.set(4, true);
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
			        	case 4:
			        		c.set(4, false);
			        		popUp(model.getValueAt(row, 8)+"님이 지불하시지 않은 상태로 변경되었습니다.");
			        		listener.editDataStatus(c);
			        		break;
			        	}
			        }
			        
			    }
			}
		};
		model.addTableModelListener(t);

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

	
	//1입실일 2	퇴실일 3	성함 4	국가 5 	여권 6	객실 7	호수 8	이메일 9	전화번호 10	요청

	private void searchList() {
		String s = srcField.getText();
		src = new Vector<Vector<Object>>();
		switch(srcAtt.getSelectedIndex()) {
		case 1:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(4).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 2:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(5).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 3:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(7).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 4:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(8).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 5:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(9).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 6:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(10).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 7:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(11).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 8:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(12).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 9:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(13).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		case 10:
			for(int i = 0; i<onView.size(); i++) {
				if(onView.get(i).get(14).equals(s)) {
					src.add(onView.get(i));
				}
			}
			break;
		}
		model.clearAllRows();
		model.setdataVector(src);
	}

	
	public void returnList() {
		model.clearAllRows();
		model.setdataVector(onView);	
	}
	
	
	//1:all 2:book 3:in 4:out 5:cancel
	public void emptyList() {
		onView = new Vector<Vector<Object>>();
		model.clearAllRows();
		model.setdataVector(onView);		
	}
	
	public void fullList() {
		onView = new Vector<Vector<Object>>(all);
		model.clearAllRows();
		model.setdataVector(onView);
	}
	
	public void initiateList(Vector<Vector<Object>> v) {
		all = new Vector<Vector<Object>>(v);
		onView = new Vector<Vector<Object>>(v);
		book = new Vector<Vector<Object>>();
		in = new Vector<Vector<Object>>();
		out = new Vector<Vector<Object>>();
		cancel = new Vector<Vector<Object>>();
		for(Vector<Object> o:v) {
			if(o.get(3).equals(Boolean.TRUE)) out.add(o);
			else if(o.get(2).equals(Boolean.TRUE)) in.add(o);
			else if(o.get(1).equals(Boolean.TRUE)) book.add(o);
			else cancel.add(o);
		}
		model.clearAllRows();
		model.setdataVector(onView);	
		System.out.println("all:"+all);
		System.out.println("book:"+book);
		System.out.println("in:"+in);
		System.out.println("out:"+out);
		System.out.println("cancel:"+cancel);
	}
	
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
	
	
	class CheckControl implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			//System.out.println(e.getStateChange()+" "+((JCheckBox)e.getSource()).getName() );
			if(((JCheckBox)e.getSource()).getName().equals("all")) {
				switch(((JCheckBox)e.getSource()).getName()+e.getStateChange()) {
				case "all1":
					cBoxCancel.setSelected(true);
					cBoxBook.setSelected(true);
					cBoxIn.setSelected(true);
					cBoxOut.setSelected(true);
					fullList();
					break;
				case "all2":
					cBoxCancel.setSelected(false);
					cBoxBook.setSelected(false);
					cBoxIn.setSelected(false);
					cBoxOut.setSelected(false);
					emptyList();
					break;
				}		
			} else {
				switch( (((JCheckBox)e.getSource()).getName())+e.getStateChange()) {
				case "book1":
					onView=model.adddataVector(book);
					break;
				case "in1":
					onView=model.adddataVector(in);
					break;
				case "out1":
					onView=model.adddataVector(out);
					break;
				case "cancel1":
					onView=model.adddataVector(cancel);
					break;
				case "book2":
					onView=model.removedataVector(book);
					break;
				case "in2":
					onView=model.removedataVector(in);
					break;
				case "out2":
					onView=model.removedataVector(out);
					break;
				case "cancel2":
					onView=model.removedataVector(cancel);
					break;
					
				}
			}
		}		
	}
}
