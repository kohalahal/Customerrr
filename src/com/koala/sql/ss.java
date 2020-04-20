package com.koala.sql;

public class ss implements Runnable{
	public static void main(String[] args) {
		ss t = new ss();
		Thread tt = new Thread(t);
		tt.start();
	}

	@Override
	public void run() {
	
		// TODO Auto-generated method stub
			while(true) {
				try {
					Thread.sleep(3000);
					System.out.println("123");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		

	}
	

}
