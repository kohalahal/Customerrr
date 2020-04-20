package com.koala.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
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
import com.koala.view.custom.Combo;
import com.koala.view.custom.DateText;
import com.koala.view.custom.Toggle;
import com.koala.view.custom.UpperCaseFilter;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.Font;

public class InputField extends JPanel {
	protected SimpleDateFormat dateform;
	public Color deepOrange;
	ImageIcon logo;
	String hotelName;
	String appName;
	protected JPanel frame;
	JPanel mainPanel;
	static public Color orange;
	Color pink;
	JPanel main;
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
	Color jaju;
	Double normal;
	Double good;
	Double best;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(1000,550);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		InputField i = new InputField();
		f.getContentPane().add(i);
		f.setVisible(true);
		Scanner sc = new Scanner(System.in);
		while(true) {
			sc.nextLine();
			System.out.println(i.getDays());
		}
	}
	
	public InputField() {
		//setBackground(Color.CYAN);
		setOpaque(false);
		jaju = new Color(9309247);
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
		//setSize(295, 455);
		setPreferredSize(new Dimension(317, 585));  

		setLayout(new BorderLayout());
		main = new JPanel();
		main.setLayout(null);
		main.setPreferredSize(new Dimension(317, 450));  
		pink = new Color(13041721);
		mainPanel = new JPanel();
		mainPanel.setBackground(pink);
		//mainPanel.setOpaque(false);
		mainPanel.setBorder(new LineBorder(pink,3));
		mainPanel.setBounds(0,0, 317, 118);
		main.add(mainPanel);
		mainPanel.setLayout(null);
		JLabel HLogo = new JLabel(logo);
		HLogo.setBounds(12, 10, 98, 101);
		mainPanel.add(HLogo);
		JLabel HotelName = new JLabel("호텔 코알라");
		HotelName.setBounds(133, 26, 130, 26);
		HotelName.setForeground(Color.white);
		mainPanel.add(HotelName);
		//frame.add(HotelName);
		HotelName.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		JLabel AppName = new JLabel("투숙객 관리 시스템");
		AppName.setForeground(Color.white);
		mainPanel.add(AppName);
		AppName.setBounds(109, 71, 185, 24);
		AppName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		main.setOpaque(false);
		JLabel checkInDate = new JLabel("날 짜");
		checkInDate.setBounds(10, 133, 40, 15);
		main.add(checkInDate);	
		JLabel name = new JLabel("성 명");
		name.setBounds(10, 163, 57, 15);
		main.add(name);
		JLabel phone = new JLabel("전화 번호");
		phone.setBounds(10, 283, 57, 15);
		main.add(phone);	
		JLabel roomInfo = new JLabel("객 실");
		roomInfo.setBounds(10, 223, 57, 15);
		main.add(roomInfo);
		JLabel email = new JLabel("이메일");
		email.setBounds(10, 253, 57, 15);
		main.add(email);
		JLabel passport = new JLabel("여 권");
		passport.setBounds(10, 193, 57, 15);
		main.add(passport);
		JLabel req = new JLabel("요청 사항");
		req.setBounds(10, 317, 57, 15);
		main.add(req);	
		Font info = new Font("나눔고딕", Font.BOLD, 13);
		checkInDate.setFont(info);
		checkInDate.setForeground(jaju);
		name.setFont(info);
		phone.setFont(info);
		roomInfo.setFont(info);
		email.setFont(info);
		passport.setFont(info);
		req.setFont(info);
		name.setForeground(jaju);
		phone.setForeground(jaju);
		roomInfo.setForeground(jaju);
		email.setForeground(jaju);
		passport.setForeground(jaju);
		req.setForeground(jaju);
		LineBorder line = new LineBorder(new Color(16753510), 1, true);
		dateform = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		inD = new DateText();
		inD.setBounds(76, 133, 110, 22);
		inD.setBorder(line);
		main.add(inD);
		outD = new DateText();
		outD.setBounds(192, 133, 111, 22);
		outD.setBorder(line);
		main.add(outD);
		resetFTF();					
		nameIn = new JTextField();
		nameIn.setBounds(146, 163, 157, 22);
		nameIn.setBorder(line);
		main.add(nameIn);
		nameIn.setColumns(10);		
		DocumentFilter dfilter = new UpperCaseFilter();
		passIn = new JTextField();
		passIn.setBorder(line);
	    ((AbstractDocument) passIn.getDocument()).setDocumentFilter(dfilter);
		passIn.setBounds(167, 193, 136, 22);
		main.add(passIn);
		passIn.setColumns(10);
		phoneIn = new JTextField();
		phoneIn.setBorder(line);
		phoneIn.setBounds(76, 283, 227, 22);
		main.add(phoneIn);
		phoneIn.setColumns(10);
		emailIn = new JTextField();
		emailIn.setBorder(line);
		((AbstractDocument) emailIn.getDocument()).setDocumentFilter(dfilter);
		emailIn.setBounds(76, 253, 227, 22);
		main.add(emailIn);
		emailIn.setColumns(10);
		JScrollPane reqScroll = new JScrollPane();		
		reqScroll.setBounds(76, 315, 227, 60);
		reqScroll.setBorder(line);
		main.add(reqScroll);
		reqIn = new JTextArea();
		reqIn.setBorder(line);
		reqScroll.setViewportView(reqIn);
		reqIn.setColumns(10);
		reqIn.setBorder(null);
		reqIn.setName("r");
	
		title = new Combo(titleComboPath);
		//cTitle.setItem(titleComboPath);
		title.setBounds(76, 163, 64, 22);
		main.add(title);
		nation = new Combo(nationComboPath);
		//cNation.setItem(nationComboPath);
		nation.setBounds(76, 193, 86, 22);
		main.add(nation);
		roomType = new Combo(roomTComboPath);
		//roomType.setItem(roomTComboPath);
		roomType.setBounds(76, 223, 119, 22);
		main.add(roomType);
		roomNo = new Combo(roomNComboPath);
		//roomNo.setItem(roomNComboPath); 
		roomNo.setBounds(201, 223, 102, 22);
		main.add(roomNo);	

		rToggle = new Toggle();
		rToggle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		rToggle.set(r1, r2);
		rToggle.setBounds(12, 472, 52, 28);
		rToggle.setStatus(true);
		//rToggle.setBorder(b);
		main.add(rToggle);
		inToggle = new Toggle();
		inToggle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		inToggle.set(in1, in2);
		inToggle.setBounds(67, 472, 52, 28);
		//inToggle.setBorder(b);
		main.add(inToggle);
		outToggle = new Toggle();
		outToggle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		outToggle.set(out1, out2);
		outToggle.setBounds(122, 472, 52, 28);
		//outToggle.setBorder(b);
		main.add(outToggle);
		payToggle = new Toggle("결 제");
		payToggle.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 15));
		payToggle.setBounds(181, 471, 46, 68);
		//payToggle.setBorder(b);
		main.add(payToggle);
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
		deepOrange = new Color(16735283);
		saveBtn = new JButton("확 인");
		saveBtn.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 16));		
		saveBtn.setBounds(232, 471, 71, 68);
		saveBtn.setBorder(wline);
		saveBtn.setUI(btn);
		saveBtn.setBackground(deepOrange);
		saveBtn.setOpaque(true);
		saveBtn.setFocusPainted(false);
		saveBtn.setForeground(Color.white);
		main.add(saveBtn);
		newBtn = new JButton("신규");
		newBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		newBtn.setBounds(12, 501, 79, 38);
		newBtn.setBorder(wline);
		newBtn.setUI(btn2);
		newBtn.setFocusPainted(false);
		newBtn.setBackground(orange);
		newBtn.setForeground(pink);
		main.add(newBtn);
		rmvBtn = new JButton("삭제");
		rmvBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		rmvBtn.setBounds(95, 501, 79, 38);
		rmvBtn.setBorder(wline);
		rmvBtn.setUI(btn2);
		rmvBtn.setFocusPainted(false);
		rmvBtn.setBackground(orange);
		rmvBtn.setForeground(pink);
		
		main.add(rmvBtn);
					
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
		inD.setName("i");
		outD.setName("o");		
		JPanel pricePanel = new JPanel();
		pricePanel.setBackground(Color.white);
		pricePanel.setBounds(18, 385, 285, 76);
		pricePanel.setLayout(null);
		
		main.add(pricePanel);	
		
		priceInfo=welcome;
		price = new JTextArea(priceInfo);
		price.setRows(2);
		price.setFont(new Font("나눔고딕", Font.BOLD, 16));
		price.setBackground(new Color(16760500));
		price.setEditable(false);
		price.setBounds(2,2,281, 72);
		price.setMargin( new Insets(18,15,18,15) );
		price.setForeground(jaju);
		pricePanel.add(price);		
		
		JPanel panel = new JPanel();
		panel.setBackground(pink);
		panel.setPreferredSize(new Dimension(10,36));
		
		add(panel, BorderLayout.SOUTH);
		add(main, BorderLayout.CENTER);
		main.setOpaque(false);
		
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
					price();
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
					price();
					try {
						if(dateform.parse(inD.getText()).compareTo(dateform.parse(outD.getText()))<1) {
						popUp("퇴실 날짜는 입실 날짜보다 적어도 하루 뒤여야 합니다.");
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}				
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
		inD.addKeyListener(k);
		outD.addKeyListener(k);
		reqIn.addKeyListener(k);
	}
	private void price() {
		try {
			System.out.println("인풋 필드에 가격 표시");
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
					popUp("고객 정보를 모두 입력해주세요");
					return;
				} else if(!(title.getSelectedIndex()>0)||!(nation.getSelectedIndex()>0)||
						!(roomType.getSelectedIndex()>0)) {
					popUp("객실의 '호수'이외에는 모든 항목이 선택되어야 합니다.");
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
					popUp("객실의 '호수'이외에는 모든 항목이 선택되어야 합니다.");
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
		inD.setText((String) c.get(4));
		outD.setText((String) c.get(5));
		title.setSelectedItem(c.get(7));
		nameIn.setText((String) c.get(8));
		nation.setSelectedItem(c.get(9));
		passIn.setText((String) c.get(10));
		roomType.setSelectedItem(c.get(11));
		roomNo.setSelectedItem(c.get(12));	
		emailIn.setText((String) c.get(13));
		phoneIn.setText((String) c.get(14));		
		reqIn.setText((String) c.get(15));
		payToggle.setSelected((boolean) c.get(17));
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
		input.add(inD.getText()); 
		input.add(outD.getText()); 
		input.add(getDays()); 
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
		input.add(payToggle.getStatus()); 
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
	
	public Integer getDays() {
		try {
			Date in = dateform.parse(inD.getText());
			Date out = dateform.parse(outD.getText());
			long diffInMillies = Math.abs(in.getTime() - out.getTime());
		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			return (int)(diff);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}		
	}
	
	private Double getPrice() {
		Double p = roomType.getSelectedIndex()==1? normal:roomType.getSelectedIndex()==2?good:best;
		return getDays()*p;
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
