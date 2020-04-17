package com.koala.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.koala.controller.EventListener;
import com.koala.dao.Customer;
import com.koala.view.custom.Combo;
import com.koala.view.custom.DateText;
import com.koala.view.custom.Toggle;
import com.koala.view.custom.UpperCaseFilter;

import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;


public class InputField extends JPanel {
	protected SimpleDateFormat dateform;
	ImageIcon logo;
	String hotelName;
	String appName;
	protected JPanel frame;
	JPanel mainPanel;
	static public Color orange;

	protected DateText inD; 
	protected DateText outD; 
	protected JTextField nameIn;
	protected JTextField passIn;
	protected JTextField phoneIn;
	protected JTextField emailIn;
	protected JTextArea reqIn;
	protected Combo title;
	protected Combo nation;
	protected Combo roomType;
	protected Combo roomNo;
	protected Toggle rToggle;
	protected Toggle inToggle;
	protected Toggle outToggle;
	Toggle payToggle;
	protected JButton saveBtn;
	protected JButton newBtn;
	protected JButton rmvBtn;
	JTextArea price;
	String priceInfo;
	private EventListener listener;
	ItemListener i;
	String welcome;
	Integer loadedId;
	Boolean loaded;
	Boolean edited;
	
	Double normal;
	Double good;
	Double best;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1280, 450);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel inpufField = new InputField();
		f.getContentPane().add(inpufField);
		f.setVisible(true);
	}
	
	public InputField() {
		
		orange = new Color(16753510);
		logo = new ImageIcon("e:/logo.png");
		hotelName = "HOTEL KOALA";
		appName = "고객 관리 시스템";
		
		welcome = "환영합니다,\r\n코알라 호텔입니다.";
		String r1 = "book";
		String r2 = "BOOK";
		String in1  = "in";
		String in2  = "IN";
		String out1  = "out";
		String out2 = "OUT";
		String titleComboPath = "title.txt";
		String nationComboPath = "nation.txt";
		String roomTComboPath = "roomType.txt";
		String roomNComboPath = "roomNo.txt";
		normal = 90000d;
		good = 150000d;
		best = 180000d;
		loaded = false;
		edited = false;		
		setSize(295, 455);
		setLayout(null);
		
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 0, 295, 90);
		add(mainPanel);
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
		
		
		JLabel checkInDate = new JLabel("날 짜");
		checkInDate.setBounds(0, 102, 40, 15);
		add(checkInDate);	
		JLabel name = new JLabel("성 명");
		name.setBounds(0, 132, 57, 15);
		add(name);
		JLabel phone = new JLabel("전화 번호");
		phone.setBounds(0, 252, 57, 15);
		add(phone);	
		JLabel roomInfo = new JLabel("객 실");
		roomInfo.setBounds(0, 192, 57, 15);
		add(roomInfo);
		JLabel email = new JLabel("이메일");
		email.setBounds(0, 222, 57, 15);
		add(email);
		JLabel passport = new JLabel("여 권");
		passport.setBounds(0, 162, 57, 15);
		add(passport);
		JLabel req = new JLabel("요청 사항");
		req.setBounds(0, 282, 57, 15);
		add(req);			
		dateform = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		inD = new DateText();
		inD.setBounds(66, 99, 110, 22);
		add(inD);
		outD = new DateText();
		outD.setBounds(182, 99, 111, 22);
		add(outD);
		resetFTF();					
		nameIn = new JTextField();
		nameIn.setBounds(136, 129, 157, 22);
		add(nameIn);
		nameIn.setColumns(10);		
		DocumentFilter dfilter = new UpperCaseFilter();
		passIn = new JTextField();
	    ((AbstractDocument) passIn.getDocument()).setDocumentFilter(dfilter);
		passIn.setBounds(157, 159, 136, 22);
		add(passIn);
		passIn.setColumns(10);
		phoneIn = new JTextField();
		phoneIn.setBounds(66, 249, 227, 22);
		add(phoneIn);
		phoneIn.setColumns(10);
		emailIn = new JTextField();
		((AbstractDocument) emailIn.getDocument()).setDocumentFilter(dfilter);
		emailIn.setBounds(66, 219, 227, 22);
		add(emailIn);
		emailIn.setColumns(10);
		reqIn = new JTextArea();
		JScrollPane reqScroll = new JScrollPane(reqIn);		
		reqScroll.setBounds(66, 279, 227, 44);
		add(reqScroll);
		reqIn.setColumns(10);
		reqIn.setBorder(new JTextField().getBorder());
		title = new Combo(titleComboPath);
		//cTitle.setItem(titleComboPath);
		title.setBounds(66, 129, 64, 22);
		add(title);
		nation = new Combo(nationComboPath);
		//cNation.setItem(nationComboPath);
		nation.setBounds(66, 159, 86, 22);
		add(nation);
		roomType = new Combo(roomTComboPath);
		//roomType.setItem(roomTComboPath);
		roomType.setBounds(66, 189, 119, 22);
		add(roomType);
		roomNo = new Combo(roomNComboPath);
		//roomNo.setItem(roomNComboPath); 
		roomNo.setBounds(191, 189, 102, 22);
		add(roomNo);		
		rToggle = new Toggle();
		rToggle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rToggle.set(r1, r2);
		rToggle.setBounds(0, 395, 52, 28);
		rToggle.setStatus(true);
		add(rToggle);
		inToggle = new Toggle();
		inToggle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		inToggle.set(in1, in2);
		inToggle.setBounds(55, 395, 52, 28);
		add(inToggle);
		outToggle = new Toggle();
		outToggle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		outToggle.set(out1, out2);
		outToggle.setBounds(110, 395, 52, 28);
		add(outToggle);
		payToggle = new Toggle("결 제");
		payToggle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
		payToggle.setBounds(165, 395, 55, 60);
		add(payToggle);
		Border b = new Border() {			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}			
			@Override
			public boolean isBorderOpaque() {	return false;	}
			@Override
			public Insets getBorderInsets(Component c) {
				return new Insets(0,0,0,0);
			}
		};
		saveBtn = new JButton("확 인");
		saveBtn.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 16));		
		saveBtn.setBounds(225, 395, 71, 60);
		add(saveBtn);
		newBtn = new JButton("신규");
		newBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		newBtn.setBounds(0, 426, 79, 28);
		newBtn.setBorder(b);
		add(newBtn);
		rmvBtn = new JButton("삭제");
		rmvBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		rmvBtn.setBounds(83, 426, 79, 28);
		add(rmvBtn);
		newBtn.setBorder(b);				
		newBtn.setName("new");
		rmvBtn.setName("rmv");
		saveBtn.setName("save");				
		title.setName("title");
		nation.setName("nation");
		roomType.setName("roomType");
		roomNo.setName("roomNo");		
		inToggle.setName("inToggle");
		outToggle.setName("outToggle");
		rToggle.setName("rToggle");
		payToggle.setName("payToggle");	
		nameIn.setName("n");
		passIn.setName("pp");
		phoneIn.setName("p");
		emailIn.setName("e");
		reqIn.setName("r");
		inD.setName("i");
		outD.setName("o");		
		JPanel pricePanel = new JPanel();
		pricePanel.setBackground(new Color(192, 192, 192));
		pricePanel.setBounds(5, 333, 285, 56);
		pricePanel.setLayout(null);
		add(pricePanel);		
		priceInfo=welcome;
		price = new JTextArea(priceInfo);
		price.setRows(2);
		price.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		price.setBackground(new Color(240, 248, 255));
		price.setEditable(false);
		price.setBounds(2,2,281, 52);
		price.setMargin( new Insets(8,8,8,8) );
		pricePanel.add(price);		
		ActionListener a = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch( ((Component) e.getSource()).getName() ) {
				case "save":
					saveAction();
					break;
				case "rmv":
					removeAction();
					break;
				case "new":
					newAction();
					break;
				}				
			}
		};
		newBtn.addActionListener(a);
		rmvBtn.addActionListener(a);
		saveBtn.addActionListener(a);
		i = new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				edited = true;
				if(roomType.getSelectedIndex()>0) {
					try {
						//System.out.println("인풋 필드에 가격 표시");
						Double p = roomType.getSelectedIndex()==1? normal:roomType.getSelectedIndex()==2?good:best;
						Date in = dateform.parse(inD.getText());
						Date out = dateform.parse(outD.getText());
						long diff = out.getTime() - in.getTime();
						price.setText(p+"원"+"("+roomType.getSelectedItem().toString()+ " 객실) X "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+"(박)\n\r요금은 "
								+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)*p+"원입니다.");						
					    //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
				}
			}
		};
		title.addItemListener(i);
		nation.addItemListener(i);
		roomType.addItemListener(i);
		roomNo.addItemListener(i);
		outToggle.addItemListener(i);
		inToggle.addItemListener(i);
		rToggle.addItemListener(i);		
		KeyListener k = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("수정댐");
				edited = true;
				if(e.getSource() instanceof DateText) {
					i.itemStateChanged(null);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		};
		nameIn.addKeyListener(k);
		passIn.addKeyListener(k);
		phoneIn.addKeyListener(k);
		emailIn.addKeyListener(k);
		reqIn.addKeyListener(k);
		inD.addKeyListener(k);
		outD.addKeyListener(k);
	}
	
	protected void saveAction() {
		//System.out.println("로디드:"+loaded+"에디티드:"+edited);
		if(!edited) {
			popUp("변경 사항이 없습니다.");
		} //변경 사항 없으면 무시
		else {
			if(loaded) {
				if(inD.getText().equals("")||outD.getText().equals("")||
						nameIn.getText().equals("")||passIn.getText().equals("")||
						emailIn.getText().equals("")|| phoneIn.getText().equals("")) {
					popUp("고객 정보를 정확히 입력해주세요");
					return;
				} else if(!(title.getSelectedIndex()>0)||!(nation.getSelectedIndex()>0)||
						!(roomType.getSelectedIndex()>0)) {
					popUp("'호수'이외에는 모든 항목이 선택되어야 합니다.");
					return;
				}
				if(ask("수정된 고객 정보를 저장하시겠습니까?")) {
					listener.editData(getData());
				}
			} else {
				if(inD.getText().equals("")||outD.getText().equals("")||
						nameIn.getText().equals("")||passIn.getText().equals("")||
						emailIn.getText().equals("")|| phoneIn.getText().equals("")) {
					popUp("고객 정보를 정확히 입력해주세요");
					return;
				} else if(!(title.getSelectedIndex()>0)||!(nation.getSelectedIndex()>0)||
						!(roomType.getSelectedIndex()>0)) {
					popUp("'호수'이외에는 모든 항목이 선택되어야 합니다.");
					return;
				}
				if(ask("새로운 고객 정보를 저장하시겠습니까?")) {
					listener.addData(getData());
				}
			}
		}
	}
	
	protected void removeAction() {
		if(!loaded) {
			popUp("삭제할 정보가 없습니다.");			
		} //불러온 것 없으면 무시
		else {
			if(ask("고객 정보를 삭제하시겠습니까?")) {
				listener.removeData(getData());
			}
		}
	}
	
	protected void newAction() {
		if(!edited) {clearAll();}
		else {
			if(ask("저장하지 않고 새로운 고객정보를 입력하시겠습니까?")) {
				clearAll();
			}
		}
	}
	
	public void setData(Vector<Object> c) throws NullPointerException {
		//System.out.println(edited);
		if(edited) {
			if(!ask("변경 사항을 버리고 고객 정보를 불러오시겠습니까?")) {
				return;
			} 
		}
		//System.out.println("스트링"+(String) c.get(4));
		loadedId = (Integer) c.get(0);
		rToggle.setSelected((boolean) c.get(1));
		inToggle.setSelected((boolean) c.get(2));
		outToggle.setSelected((boolean) c.get(3));	
		payToggle.setSelected((boolean) c.get(4));
		inD.setText((String) c.get(5));
		outD.setText((String) c.get(6));
		title.setSelectedItem(c.get(7));
		nameIn.setText((String) c.get(8));
		nation.setSelectedItem(c.get(9));
		passIn.setText((String) c.get(10));
		roomType.setSelectedItem(c.get(11));
		roomNo.setSelectedItem(c.get(12));	
		emailIn.setText((String) c.get(13));
		phoneIn.setText((String) c.get(14));		
		reqIn.setText((String) c.get(15));
		i.itemStateChanged(null);	
		loaded = true;
		edited=false;
	}
	
	public Vector<Object> getData() {
		Vector<Object> input = new Vector<Object>();
		//System.out.println("inD:"+inD.getText());
		System.out.println("수정고객의 id"+loadedId);
		if(loaded) {
			input.add(loadedId);
		} else {
			input.add(Integer.valueOf(-1));
		}
		input.add(rToggle.getStatus());
		input.add(inToggle.getStatus()); 
		input.add(outToggle.getStatus()); 
		input.add(payToggle.getStatus()); 
		input.add(inD.getText()); 
		input.add(outD.getText()); 
		input.add(title.getSelectedItem().toString()); 
		input.add(nameIn.getText()); 
		input.add(nation.getSelectedItem().toString());
		input.add(passIn.getText()); 
		input.add(roomType.getSelectedItem().toString()); 
		input.add(roomNo.getSelectedItem().toString()); 
		input.add(emailIn.getText()); 
		input.add(phoneIn.getText()); 		
		input.add(reqIn.getText());
		input.add(getPrice());
		clearAll();
		System.out.println("생성된고객:"+input.toString());
		return input;
	}
		
	public void clearAll() {
		// TODO Auto-generated method stub
		rToggle.setSelected(true);
		inToggle.setSelected(false);
		outToggle.setSelected(false);
		payToggle.setSelected(false);
		title.setSelectedIndex(0);
		nation.setSelectedIndex(0);
		roomType.setSelectedIndex(0);
		roomNo.setSelectedIndex(0);
		nameIn.setText("");
		passIn.setText("");
		emailIn.setText("");
		phoneIn.setText("");		
		reqIn.setText("");
		resetFTF();
		price.setText(welcome);
		loaded = false;
		edited = false;
	}
	
	public void resetFTF() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY,12);
		c.set(Calendar.MINUTE,00);
		Date d = c.getTime();
		inD.setValue(d);
		c.add(Calendar.DAY_OF_MONTH, 2);
		d = c.getTime();
		outD.setValue(d);
	}
	
	private Double getPrice() {
		try {
			Date in = dateform.parse(inD.getText());
			Date out = dateform.parse(outD.getText());
			long diff = out.getTime() - in.getTime();
			Double p = roomType.getSelectedIndex()==1? normal:roomType.getSelectedIndex()==2?good:best;
			Double pp = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)*p;
			return pp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0d;
		}
	}
	
	private Boolean ask(String s) {
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
}
