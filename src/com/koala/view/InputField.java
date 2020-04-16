package com.koala.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.koala.controller.WindowHandler;
import com.koala.dao.Customer;
import com.koala.view.custom.Combo;
import com.koala.view.custom.DateText;
import com.koala.view.custom.Toggle;


public class InputField extends JPanel {
	protected SimpleDateFormat dateform;
	protected DateText inD; 
	protected DateText outD; 
	protected JTextField nameIn;
	protected JTextField passIn;
	protected JTextField phoneIn;
	protected JTextField emailIn;
	protected JTextField reqIn;
	protected Combo title;
	protected Combo nation;
	protected Combo roomType;
	protected Combo roomNo;
	protected Toggle rToggle;
	protected Toggle inToggle;
	protected Toggle outToggle;
	protected JButton saveBtn;
	protected JButton newBtn;
	protected JButton delBtn;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1280, 450);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel inpufField = new InputField();
		f.getContentPane().add(inpufField);
		f.setVisible(true);
	}
	
	public InputField() {
		String r1 = "book";
		String r2 = "BOOK";
		String in1  = "in";
		String in2  = "IN";
		String out1  = "out";
		String out2 = "OUT";
		String attComboPath = "attribute.txt";
		String titleComboPath = "title.txt";
		String nationComboPath = "nation.txt";
		String roomTComboPath = "roomType.txt";
		String roomNComboPath = "roomNo.txt";
		
		setSize(295, 300);
		setLayout(null);
		JLabel checkInDate = new JLabel("날 짜");
		checkInDate.setBounds(0, 3, 40, 15);
		add(checkInDate);	
		JLabel name = new JLabel("성 명");
		name.setBounds(0, 33, 57, 15);
		add(name);
		JLabel phone = new JLabel("전화 번호");
		phone.setBounds(0, 123, 57, 15);
		add(phone);	
		JLabel roomInfo = new JLabel("객 실");
		roomInfo.setBounds(0, 93, 57, 15);
		add(roomInfo);
		JLabel email = new JLabel("이메일");
		email.setBounds(0, 153, 57, 15);
		add(email);
		JLabel passport = new JLabel("여 권");
		passport.setBounds(0, 63, 57, 15);
		add(passport);
		JLabel req = new JLabel("요청 사항");
		req.setBounds(0, 183, 57, 15);
		add(req);
				
		dateform = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		inD = new DateText();
//		inD.setEditable(true);
		inD.setBounds(66, 0, 110, 22);
		add(inD);
		outD = new DateText();
		outD.setBounds(182, 0, 111, 22);
		add(outD);
//		outD.setColumns(10);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY,12);
		c.set(Calendar.MINUTE,00);
		Date d = c.getTime();
		inD.setValue(d);
		c.add(Calendar.DAY_OF_MONTH, 2);
		d = c.getTime();
		outD.setValue(d);
			
		nameIn = new JTextField();
		nameIn.setBounds(136, 60, 157, 22);
		add(nameIn);
		nameIn.setColumns(10);
		passIn = new JTextField();
		passIn.setBounds(157, 90, 136, 22);
		add(passIn);
		passIn.setColumns(10);
		phoneIn = new JTextField();
		phoneIn.setBounds(66, 30, 227, 22);
		add(phoneIn);
		phoneIn.setColumns(10);
		emailIn = new JTextField();
		emailIn.setBounds(66, 150, 227, 22);
		add(emailIn);
		emailIn.setColumns(10);
		reqIn = new JTextField();
		reqIn.setBounds(66, 180, 227, 44);
		add(reqIn);
		reqIn.setColumns(10);
		title = new Combo(titleComboPath);
		//cTitle.setItem(titleComboPath);
		title.setBounds(66, 60, 64, 22);
		add(title);
		nation = new Combo(nationComboPath);
		//cNation.setItem(nationComboPath);
		nation.setBounds(66, 90, 86, 22);
		add(nation);
		roomType = new Combo(roomTComboPath);
		//roomType.setItem(roomTComboPath);
		roomType.setBounds(66, 120, 119, 22);
		add(roomType);
		roomNo = new Combo(roomNComboPath);
		//roomNo.setItem(roomNComboPath); 
		roomNo.setBounds(191, 120, 102, 22);
		add(roomNo);
		
		rToggle = new Toggle();
		rToggle.set(r1, r2);
		rToggle.setBounds(0, 232, 57, 29);
		add(rToggle);
		inToggle = new Toggle();
		inToggle.set(in1, in2);
		inToggle.setBounds(66, 232, 57, 29);
		add(inToggle);
		outToggle = new Toggle();
		outToggle.set(out1, out2);
		outToggle.setBounds(132, 232, 57, 29);
		add(outToggle);
		
		saveBtn = new JButton("확 인");
		saveBtn.setBounds(194, 232, 99, 64);
		add(saveBtn);
		newBtn = new JButton("신 규");
		newBtn.setBounds(0, 264, 92, 33);
		add(newBtn);
		delBtn = new JButton("삭 제");
		delBtn.setBounds(97, 264, 92, 33);
		add(delBtn);
	}
	
	public void clearAll() {
		// TODO Auto-generated method stub
		resetToggle();
		resetCombo();
		resetTF();
		resetFTF();
	}
	
	public void resetToggle() {
		rToggle.setSelected(false);
		inToggle.setSelected(false);
		outToggle.setSelected(false);
	}
	
	public void resetCombo() {
		title.setSelectedIndex(0);
		nation.setSelectedIndex(0);
		roomType.setSelectedIndex(0);
		roomNo.setSelectedIndex(0);
	}
	
	public void resetTF() {
		nameIn.setText("");
		passIn.setText("");
		phoneIn.setText("");
		emailIn.setText("");
		reqIn.setText("");
	}

	public void resetFTF() {
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		inD.setValue(d);
		c.add(Calendar.DAY_OF_MONTH, 2);
		d = c.getTime();
		outD.setValue(d);
	}
	
	public void selectData(Customer c) {
		inD.setText((String) c.get(4));
		outD.setText((String) c.get(5));
		
		title.setSelectedItem(c.get(6));
		nation.setSelectedItem(c.get(8));
		roomType.setSelectedItem(c.get(10));
		roomNo.setSelectedItem(c.get(11));
		
		nameIn.setText((String) c.get(7));
		passIn.setText((String) c.get(9));
		phoneIn.setText((String) c.get(13));
		emailIn.setText((String) c.get(12));
		reqIn.setText((String) c.get(14));
		
		rToggle.setSelected((boolean) c.get(1));
		inToggle.setSelected((boolean) c.get(2));
		outToggle.setSelected((boolean) c.get(3));
	}
	
	public Customer getData() {
		return null;
	}
	
	public void addListener(WindowHandler w) {
		newBtn.addActionListener(w);
		delBtn.addActionListener(w);
		saveBtn.addActionListener(w);
				
		newBtn.setName("new");
		delBtn.setName("delete");
		saveBtn.setName("save");		
		
		title.addItemListener(w);
		nation.addItemListener(w);
		roomType.addItemListener(w);
		roomNo.addItemListener(w);
		title.setName("title");
		nation.setName("nation");
		roomType.setName("roomType");
		roomNo.setName("roomNo");
		
		outToggle.addItemListener(w);
		inToggle.addItemListener(w);
		rToggle.addItemListener(w);
		inToggle.setName("inToggle");
		outToggle.setName("outToggle");
		rToggle.setName("rToggle");
		
		nameIn.addKeyListener(w);
		passIn.addKeyListener(w);
		phoneIn.addKeyListener(w);
		emailIn.addKeyListener(w);
		reqIn.addKeyListener(w);
		inD.addKeyListener(w);
		outD.addKeyListener(w);

		nameIn.setName("n");
		passIn.setName("pp");
		phoneIn.setName("p");
		emailIn.setName("e");
		reqIn.setName("r");
		inD.setName("i");
		outD.setName("o");
	}

}
