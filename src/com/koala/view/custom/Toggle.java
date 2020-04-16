package com.koala.view.custom;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;
import javax.swing.plaf.metal.MetalToggleButtonUI;

public class Toggle extends JToggleButton {
	private static final long serialVersionUID = -1687603129313141255L;
	public Color orange;
	public Color lightGray;
	private Boolean status;
	private String name;
	
	/**
	 * 
	 */
	public Toggle () {
		orange = new Color(16753510);
		//= new Color(16764108);
		lightGray =	new Color(13158600);
		status = false;
	}
	
	@Override
	public Insets getMargin() {
		// TODO Auto-generated method stub
		return new Insets(10,10,10,10);
	}
	
	public Boolean getStatus() {
		return status;
	}

	public void set(String s1, String s2) {
		setText(s1);
		setSelected(false);
    	setBackground(lightGray);
    	setForeground(Color.WHITE);
		setUI(new MetalToggleButtonUI() {
			
		    @Override
		    protected Color getSelectColor() {
		        return orange;
		    }
		});
		addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	setText(s1);
		        	setForeground(Color.BLACK);
		        	status = true;
		        } else {
		        	setText(s2);
		        	setForeground(Color.RED);
		        	status = false;
		        }
		    }
		});
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
