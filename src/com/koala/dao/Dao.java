package com.koala.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.koala.controller.EventListener;

public class Dao {
	private Vector<Customer> list;
	EventListener listener;
	String path;
	File DBFile;
	int id;
	FileReader reader;
	
	public Dao() {
		path = "DB.json";
		DBFile = new File(path);
		id = 0;
		if(!DBFile.exists())
			try {
				if(DBFile.createNewFile()) System.out.println("DB 파일 새로 생성");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("DB 파일 생성 실패");
			}
		synchDB();
	}
	
	public Dao(EventListener l) {
		this();
		this.listener = l;
	}
	
	public static void main(String[] args) {
		Dao d = new Dao();
		d.getList();
	}
	
	//자료 조작 : 리스트에, 디비에, 그리고 리스트 리턴
	public Vector<Vector<Object>> addCustomer(Vector<Object> c) {
		int i = getCustomerIdx(c);
		if(i!=-1) { //중복 자료이면
			System.out.println("새로운 고객이 중복자료 저장안됨");
			listener.dataError("똑같은 고객 정보가 이미 존재합니다.");
			return null;
		}
		Customer add = vToC(c);
		add.set(0, id++);
		list.add(add);
		try {
			exportDB();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getList();
	}

	public Vector<Vector<Object>> editCustomer(Vector<Object> c) { 
		int idx = getCustomerIdx((Integer)c.get(0));
		if(idx==-1) {
			listener.dataError("수정할 대상을 찾지 못했습니다.");
			return null;
		} else {
		System.out.println("고객 수정일어남 idx:"+idx+"c:"+c.toString());
		list.set(idx, vToC(c));
		try {
			exportDB();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getList();
		}
	}

	public Vector<Vector<Object>> removeCustomer(Vector<Object> c) {
		int i = getCustomerIdx(c);
		if(i==-1) { //없으면
			return null;
		}
		list.remove(i);
		try {
			exportDB();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getList();
	}
		
	private int getCustomerIdx(Vector<Object> c) {
		int i = -1;
		if(list.size()>0) {
			for(Customer v:list) {
				if( v.get(1).equals(c.get(1)) && v.get(2).equals(c.get(2)) && v.get(3).equals(c.get(3)) &&
						v.get(4).equals(c.get(4)) && v.get(5).equals(c.get(5)) && v.get(6).equals(c.get(6)) &&
						v.get(7).equals(c.get(7)) && v.get(8).equals(c.get(8)) && v.get(9).equals(c.get(9)) &&
						v.get(10).equals(c.get(10)) && v.get(11).equals(c.get(11)) && v.get(12).equals(c.get(12)) &&
						v.get(13).equals(c.get(13)) && v.get(14).equals(c.get(14)) && 
						v.get(15).equals(c.get(15)) && v.get(16).equals(c.get(16))&& 
						v.get(17).equals(c.get(17)) && v.get(18).equals(c.get(18)) ) {
					i = list.indexOf(v);
					break;
				}
			}
		}
		return i;
	}
	
	private int getCustomerIdx(int id) {
		int i = -1;
		if(list.size()>0) {
			for(Customer v:list) {
				if( v.get(0).equals(Integer.valueOf(id))) {
					i = list.indexOf(v);
					break;
				}
			}
		}
		return i;
	}
		
	public Vector<Vector<Object>> getList() {
		Vector<Vector<Object>> tmp = new Vector<Vector<Object>>();
		for(Customer c:list) {
			tmp.add(c);
		}
		return tmp;
	}
	
	private void synchDB() { //초기 씽크		
		try {			
			FileReader reader = new FileReader(DBFile);
			JSONTokener tokener = new JSONTokener(reader);
			try {	
			    JSONArray jArr = new JSONArray(tokener);
			    reader.close();
			    list = JarrToVc(jArr);
			    System.out.println("리스트에 DB 받아옴");	
			    if(list==null||list.size()<1) {
		    		list = new Vector<Customer>();
		    		System.out.println("빈 리스트 생성");
			    }		    
				for(int i = 0; i < list.size(); i++) {
					if( (int)(list.get(i).get(0)) >= id) {
						//S//ystem.out.println("id보다큰 리스트id:"+(int)list.get(i).get(0));
						id=(int)list.get(i).get(0)+1;
						//i = -1;
					}
				}
				System.out.println("설정된 초기 id값:"+id);
			} catch (IOException | JSONException  e) {
				// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("DB 정보 없음");
			list = new Vector<Customer>();
    		System.out.println("빈 리스트 생성");
			}			
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
	    //e.printStackTrace();
	    System.out.println("DB 파일 없음");
		} 
		System.out.println("씽크끝"+list.toString());
	}

	public void exportDB() throws JSONException {
		try {
			FileWriter writer = new FileWriter(DBFile);			
			System.out.println("DB로 나가는 리스트:"+list.toString());
			writer.write(vCToJarr(list).toString());
			writer.flush();
			writer.close();
			System.out.println("갱신된 리스트를 DB로 내보냄");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("저장:DB 파일 쓰기 실패");
		}
	}
	
	private Customer vToC(Vector<Object> c) {
		// TODO Auto-generated method stub
		return new Customer ((Integer)c.get(0), (Boolean)c.get(1), (Boolean)c.get(2), (Boolean)c.get(3),
				(String)c.get(4), (String)c.get(5), (Integer)c.get(6), (String)c.get(7), (String)c.get(8), 
				(String)c.get(9), (String)c.get(10), (String)c.get(11), (String)c.get(12),	
				(String)c.get(13), (String)c.get(14), (String)c.get(15), (Double)c.get(16), (Boolean)c.get(17));
	}
	public Vector<Customer> JarrToVc(JSONArray arr) throws JSONException {
		Vector<Customer> tmp = new Vector<Customer>();
		if(arr!=null) {
			for(int i = 0; i < arr.length(); i++) {
				tmp.add(jToC(arr.getJSONObject(i)));
			}
		}
		return tmp;
	}
	public JSONArray vCToJarr(Vector<Customer> v) throws JSONException {
		if(v.size()>0) {
			JSONArray tmp = new JSONArray();
			for(Customer c:v) {
				tmp.put(cToJ(c));
			}
			return tmp;
		}
		return new JSONArray();
	}
	public JSONObject cToJ(Customer c) throws JSONException {
		if(c.get(0)!=null) {
			JSONObject tmp = new JSONObject();
			tmp.put("id", c.get(0));
			tmp.put("booked", c.get(1));
			tmp.put("checkedIn", c.get(2));
			tmp.put("checkedOut", c.get(3));
			tmp.put("dayIn", c.get(4));
			tmp.put("dayOut", c.get(5));
			tmp.put("days", c.get(6));
			tmp.put("title", c.get(7));
			tmp.put("name", c.get(8));
			tmp.put("nation", c.get(9));
			tmp.put("passport", c.get(10));
			tmp.put("roomType", c.get(11));
			tmp.put("roomNo", c.get(12));
			tmp.put("email", c.get(13));
			tmp.put("phone", c.get(14));
			tmp.put("request", c.get(15));
			tmp.put("price", c.get(16));
			tmp.put("payed", c.get(17));
			return tmp;
		}
		return null;
	}
	public Customer jToC(JSONObject j) throws JSONException {
		return new Customer( j.getInt("id"),  
				j.getBoolean("booked"), j.getBoolean("checkedIn"), j.getBoolean("checkedOut"),
				j.getString("dayIn"), j.getString("dayOut"), j.getInt("days"), j.getString("title"), j.getString("name"), 
				j.getString("nation"), j.getString("passport"),
				j.getString("roomType"), j.getString("roomNo"),
				j.getString("email"), j.getString("phone"), j.getString("request"), j.getDouble("price"),  j.getBoolean("payed"));
	}
		
}
