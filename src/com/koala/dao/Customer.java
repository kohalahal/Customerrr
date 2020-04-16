package com.koala.dao;

import java.util.Vector;

public class Customer extends Vector<Object>{

	public static void main(String[] args) {
		Customer a = new Customer(0, true, true, true, "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
		Customer b = new Customer(1, true, true, true, "44", "55", "66", "77", "88", "99","10", "12", "13", "14");
		Customer c = new Customer(1, true, true, true, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
		Vector<Object> d = c;
		System.out.println(d.toString());
		System.out.println(d.get(14));
		System.out.println(a);
		System.out.println(b);

	}
	
	public Customer() {
		super(15,0);
	}
	
	public Customer(int i, Boolean booked, Boolean checkedIn, Boolean checkedOut,
			String dayIn, String dayOut, String title, String name, String nation,
			String passport, String roomType, String email,	String phone,
			String request) {
		this();
		this.add(0, Integer.valueOf(i));
		this.add(1, booked);
		this.add(2, checkedIn);
		this.add(3, checkedOut);
		this.add(4, dayIn);
		this.add(5, dayOut);
		this.add(6, title);
		this.add(7, name);
		this.add(8, nation);
		this.add(9, passport);
		this.add(10, roomType);
		this.add(11, null);
		this.add(12, email);
		this.add(13, phone);
		this.add(14, request);
	}
	
	public Customer(int i, Boolean booked, Boolean checkedIn, Boolean checkedOut,
			String dayIn, String dayOut, String title, String name, String nation,
			String passport, String roomType, String roomNo, String email,	String phone,
			String request) {
		this();
		this.add(0, Integer.valueOf(i));
		this.add(1, booked);
		this.add(2, checkedIn);
		this.add(3, checkedOut);
		this.add(4, dayIn);
		this.add(5, dayOut);
		this.add(6, title);
		this.add(7, name);
		this.add(8, nation);
		this.add(9, passport);
		this.add(10, roomType);
		this.add(11, roomNo);
		this.add(12, email);
		this.add(13, phone);
		this.add(14, request);
	}
		
	@Override
	public String toString() {
		return get(0)+" "+get(1)+" "+get(2)+" "+get(3)+" "+get(4)+" "+get(5)+" "+get(6)+" "+get(7)+" "+get(8)+" "+get(9)+" "+get(10)+" "+get(11)+" "+get(12)+" "+get(13)+" "+get(14);
	}

}

