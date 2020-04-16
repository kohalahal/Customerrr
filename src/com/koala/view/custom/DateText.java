package com.koala.view.custom;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class DateText extends JFormattedTextField {
	Format format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
  
   public DateText() {
      super();
      setHorizontalAlignment(CENTER);
      MaskFormatter maskFormatter = null;
      try {
         maskFormatter = new MaskFormatter("####/##/## ##:##");
      } catch (ParseException e) {
         e.printStackTrace();
      }
  
      maskFormatter.setPlaceholderCharacter('_');
      setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
      
   }
  
   public void setValue(Date date) {
      super.setValue(toString(date));
   }
  
   private String toString(Date date) {
      try {
         return format.format(date);
      } catch (Exception e) {
         return "";
      }
   }
}
