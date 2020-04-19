package com.koala.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class nn {
	JFrame main;
	
	public nn() {
		main = new JFrame();
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon("C:\\refresh.png"));
		main.getContentPane().add(btnNewButton, BorderLayout.CENTER);
	}

}
