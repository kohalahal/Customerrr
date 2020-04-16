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

public class Dao {
	private Vector<Customer> list;
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
	
	public static void main(String[] args) {
		Dao d = new Dao();
		Customer a = new Customer(0, true, true, true, "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
		Customer b = new Customer(1, true, true, true, "44", "55", "66", "77", "88", "99","10", "12", "13", "14");
		Customer c = new Customer(1, true, true, true, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		System.out.println(d.list==null);
		d.synchDB();
	}
	
	
	//자료 조작 : 리스트에, 디비에, 그리고 리스트 리턴
	public Vector<Vector<Object>> addCustomer(Customer c) {
		int i = getCustomerIdx(c);
		if(i!=-1) { //중복 자료이면
			return null;
		}
		c.set(0, id++);
		list.add(c);
		synchDB();
		return getList();
	}
	public Vector<Vector<Object>> editCustomer(Customer before, Customer after) { //커스터머 비포에 id정보가 뭐가담겨있남?ㄴㄴ
		int i = getCustomerIdx(before);
		if(i==-1) { //중복 정보가 있으면
			return null;
		}
		after.set(0, list.get(i).get(0));
		list.set(i, after);
		synchDB();
		return getList();
	}
	public Vector<Vector<Object>> removeCustomer(Customer c) {
		int i = getCustomerIdx(c);
		if(i==-1) { //없으면
			return null;
		}
		list.remove(i);
		synchDB();
		return getList();
	}
	
	
	
	
	private int getCustomerIdx(Customer c) {
		int i = -1;
		if(list.size()>0) {
			for(Customer v:list) {
				if( v.get(1).equals(c.get(1)) && v.get(2).equals(c.get(2)) && v.get(3).equals(c.get(3)) &&
						v.get(4).equals(c.get(4)) && v.get(5).equals(c.get(5)) && v.get(6).equals(c.get(6)) &&
						v.get(7).equals(c.get(7)) && v.get(8).equals(c.get(8)) && v.get(9).equals(c.get(9)) &&
						v.get(10).equals(c.get(10)) && v.get(11).equals(c.get(11)) && v.get(12).equals(c.get(12)) &&
						v.get(13).equals(c.get(13)) && v.get(14).equals(c.get(14)) ) {
					i = list.indexOf(v);
				}
			}
		}
		return i;
	}
		
	public Vector<Vector<Object>> getList() {
		try {
			return JarrToV(vCToJarr(list));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
	
	private void synchDB() {
		try {			
			FileReader reader = new FileReader(DBFile);
			JSONTokener tokener = new JSONTokener(reader);
			try {	
		    JSONArray jArr = new JSONArray(tokener);
		    reader.close();
		   //db가 안비었으면 비교, 리스트 우대
		    	if(list==null) {
		    		list = JarrToVC(jArr);
		    		System.out.println("리스트에 DB 받아옴");
		    	} else {
		    		if(JarrToVC(jArr).size()==list.size()) {
		    			
		    		} else {
		    			System.out.println("DB와 리스트 불일치");
		    			exportDB();
		    		}		    		
		    	}
		
			   System.out.println("리스트:"+list.toString());
			   for(Customer c:list) {
				   if( (int)(c.get(0)) > id) id=(int)c.get(0)+1;
			   }
			} catch (IOException | JSONException  e) {
				// TODO Auto-generated catch block
			    //e.printStackTrace();
			    System.out.println("DB 정보 없음");
			    if(list==null) {
		    		list = new Vector<Customer>();
		    		System.out.println("빈 리스트");
		    	} else {
		    		try {
						exportDB();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}

			}		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		    //e.printStackTrace();
		    System.out.println("DB 파일 없음");
		} 
		
	}

	public void exportDB() throws JSONException {
		try {
			FileWriter writer = new FileWriter(DBFile);
			System.out.println(list.toString());
			System.out.println(vCToJarr(list).toString());
			writer.write(vCToJarr(list).toString());
			writer.flush();
			writer.close();
			System.out.println("리스트를 DB로 내보냄");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("저장:DB 파일 쓰기 실패");
		}
	}
	
	
	public JSONObject cToJ(Customer c) {
		if(c.get(0)!=null) {
			Vector<Object> tmp = c;
			return vToJ(c);
		}
		return null;
	}
	public JSONArray vCToJarr(Vector<Customer> v) {
		if(v.size()>0) {
			JSONArray tmp = new JSONArray();
			for(Customer c:v) {
				tmp.put(cToJ(c));
			}
			return tmp;
		}
		return null;
	}
	public Customer jToC(JSONObject j) throws JSONException {
		return new Customer( j.getInt("id"),  j.getBoolean("booked"), j.getBoolean("checkedIn"), j.getBoolean("checkedOut"),
				j.getString("dayIn"), j.getString("dayOut"), j.getString("title"), j.getString("name"), 
				j.getString("nation"), j.getString("passport"),
				j.getString("roomType"), j.getString("roomNo"),
				j.getString("email"), j.getString("phone"), j.getString("request"));
	}
	public Vector<Customer> JarrToVC(JSONArray arr) throws JSONException {
		Vector<Customer> tmp = new Vector<Customer>();
		if(arr!=null) {
			for(int i = 0; i < arr.length(); i++) {
				tmp.add(jToC(arr.getJSONObject(i)));
			}
		}
		return tmp;
	}
	static JSONObject vToJ(Vector<Object> v) {
		try {
		JSONObject tmp = new JSONObject();
		tmp.put("id", v.get(0));
		tmp.put("booked", v.get(1));
		tmp.put("checkedIn", v.get(2));
		tmp.put("checkedOut", v.get(3));
		tmp.put("dayIn", v.get(4));
		tmp.put("dayOut", v.get(5));
		tmp.put("title", v.get(6));
		tmp.put("name", v.get(7));
		tmp.put("nation", v.get(8));
		tmp.put("passport", v.get(9));
		tmp.put("roomType", v.get(10));
		if(v.get(11)!=null) tmp.put("roomNo", v.get(11));
		else tmp.put("roomNo", "");
		tmp.put("email", v.get(12));
		tmp.put("phone", v.get(13));
		tmp.put("request", v.get(14));
		return tmp;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public JSONArray vToJarr(Vector<Vector<Object>> v) throws JSONException {
		JSONArray tmp = new JSONArray();
		if(v!=null) {
			for(Vector<Object> e:v) {
				tmp.put(vToJ(e));
			}
		}
		return tmp;
	}	
	public Vector<Object> jToV(JSONObject j) throws JSONException {
		Vector<Object> tmp = new Vector<Object>();
		if(j.has("id")) tmp.add(j.getInt("id"));
		else tmp.add(Integer.valueOf(0));
		if(j.has("booked")) tmp.add(j.getBoolean("booked"));
		else tmp.add(Boolean.FALSE);
		if(j.has("staying")) tmp.add(j.getBoolean("staying"));
		else tmp.add(Boolean.FALSE);
		if(j.has("checkOut"))tmp.add(j.getBoolean("checkOut"));
		else tmp.add(Boolean.FALSE);
		if(j.has("bookIn")) tmp.add(j.getString("bookIn"));
		else tmp.add("10/10/10 10:10");
		if(j.has("bookOut")) tmp.add(j.getString("bookOut"));
		else tmp.add("10/10/10 10:10");
		if(j.has("title")) tmp.add(j.getString("title"));
		else tmp.add("");
		if(j.has("name")) tmp.add(j.getString("name"));
		else tmp.add("");
		if(j.has("nation")) tmp.add(j.getString("nation"));
		else tmp.add("");
		if(j.has("passport")) tmp.add(j.getString("passport"));
		else tmp.add("");
		if(j.has("roomType")) tmp.add(j.getString("roomType"));
		else tmp.add("");
		if(j.has("roomNo")) tmp.add(j.getString("roomNo"));
		else tmp.add("");
		if(j.has("email")) tmp.add(j.getString("email"));
		else tmp.add("");
		if(j.has("phone")) tmp.add(j.getString("phone"));
		else tmp.add("");
		if(j.has("request")) tmp.add(j.getString("request"));
		else tmp.add("");
		return tmp;		
	}
	public Vector<Vector<Object>> JarrToV(JSONArray arr) throws JSONException {
		Vector<Vector<Object>> tmp = new Vector<Vector<Object>>();
		if(arr!=null) {
			for(int i = 0; i < arr.length(); i++) {
				tmp.add( jToV( (JSONObject)(arr.get(i)) ) );
			}
		}
		return tmp;
	}
}
