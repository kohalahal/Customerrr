package com.koala.view.custom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Combo extends JComboBox<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6965228271374791452L;
	String[] item;
	DefaultComboBoxModel<String> att;
	
	public Combo() {}
	
	public Combo(String filePath) {
		setItem(filePath);
	}
	
	public Combo(String[] att) {
		setItem(att);
	}
	
	public void setItem(String[] att) {
		this.att = new DefaultComboBoxModel<String>(att);
		setModel(this.att);
	}
	
	public void setItem(String filePath) {
		File file = new File(filePath);
		try {
			Scanner sc = new Scanner(file);
			int i = 0;
			int cnt = 0;
			while(sc.hasNextLine()) {
				sc.nextLine();
				cnt++;
			}
			sc.close();
			Scanner sc2 = new Scanner(file);
			item = new String[cnt];
			while(sc2.hasNextLine()) {
				item[i++] = sc2.nextLine();
			}
			sc2.close();
			att = new DefaultComboBoxModel<String>(item);
			setModel(att);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("오류:"+filePath+"파일이 없습니다.");
		}
	}
	
	@Override
    public int getSelectedIndex() {
        Object sObject = dataModel.getSelectedItem();
        int i,c;
        String obj;

        for ( i=1,c=dataModel.getSize();i<c;i++ ) {
            obj = dataModel.getElementAt(i);
            if ( obj != null && obj.equals(sObject) )
                return i;
        }
        return -1;
    }
}
