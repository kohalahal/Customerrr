package com.koala.dao;

import java.util.Vector;

public class Customer extends Vector<Object>{
	public Customer() {
		super(18,0);
	}

	public Customer(Integer i, Boolean booked, Boolean checkedIn, Boolean checkedOut,
			String dayIn, String dayOut, Integer days, String title, String name,
			String nation,	String passport, String roomType, String roomNo, 
			String email,	String phone,
			String request, Double price, Boolean payed) {
		this();
		this.add(0, Integer.valueOf(i));
		this.add(1, booked);
		this.add(2, checkedIn);
		this.add(3, checkedOut);
		this.add(4, dayIn);
		this.add(5, dayOut);
		this.add(6, days);
		this.add(7, title);
		this.add(8, name);
		this.add(9, nation);
		this.add(10, passport);
		this.add(11, roomType);
		this.add(12, roomNo);
		this.add(13, email);
		this.add(14, phone);
		this.add(15, request);
		this.add(16, price);
		this.add(17, payed);
	}
		
	@Override
	public String toString() {
		return get(0)+" "+get(1)+" "+get(2)+" "+get(3)+" "+get(4)+" "+get(5)+" "+get(6)+" "+get(7)+" "+get(8)+" "+get(9)+" "+get(10)+" "+get(11)+" "+get(12)+" "+get(13)+" "+get(14)+" "+get(15)+" "+get(16)+" "+get(17);
	}
	
	@Override
	public synchronized Object clone() {
		// TODO Auto-generated method stub
		return super.clone();
	}
}

