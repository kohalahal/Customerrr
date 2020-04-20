package com.koala.view.custom;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.LookAndFeel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class Combo extends JComboBox<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6965228271374791452L;
	String[] item;
	DefaultComboBoxModel<String> att;
	
	public Combo() {
		setUI(new MyComboBoxUI());
		setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		LineBorder line = new LineBorder(new Color(16753510), 1, true);
		//srcAtt.setBounds(450, 2, 92, 21);
		setBorder(line);
	}
	
	public Combo(String filePath) {
		this();
		setItem(filePath);
	}
	
	public Combo(String[] att) {
		this();
		setItem(att);
	}
	
	public static class MyComboBoxUI extends BasicComboBoxUI {
		@Override
	 	protected void installDefaults() {
            super.installDefaults();
            //okAndFeel.uninstallBorder(comboBox); //Uninstalls the LAF border for both button and label of combo box.
        }

        @Override
        protected JButton createArrowButton() {
            //Feel free to play with the colors:
            final Color background = Color.WHITE;    //Default is UIManager.getColor("ComboBox.buttonBackground").
            
            final Color triangle = new Color(13041721);              //Default is UIManager.getColor("ComboBox.buttonDarkShadow"). The color of the triangle.
            final Color highlight = background;               //Default is UIManager.getColor("ComboBox.buttonHighlight"). Another color to show the button as highlighted.
            final JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, new Color(16773102), new Color(16753510), triangle, highlight);
            button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
            return button;
        }
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
