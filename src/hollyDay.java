/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
public class hollyDay {
    Date d1,d2;
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    Calendar c3 = Calendar.getInstance();
    Calendar c4 = Calendar.getInstance();
    Calendar c5 = Calendar.getInstance();
    Calendar c6 = Calendar.getInstance();
 hollyDay(Date d1,Date d2){
   this.d1 =d1;
   this.d2 = d2;
   c1.setTime(d1);
   c2.setTime(d2);
   c3.setTime(d1);
   c4.setTime(d2);
   c5.setTime(d1);
   c6.setTime(d2);
   
   }
    
    public int satCount(){
    int saturday = 0;
    while (! c1.after(c2)) {
    if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ){
                saturday++; 
            }
    c1.add(Calendar.DATE, 1);
    }
    return saturday;
    }
    public int sunCount(){
    int sundays = 0;
     while (! c3.after(c4)) {
      if(c3.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                sundays++;
            }
     c3.add(Calendar.DATE, 1);
     }
    
    return sundays;
    
    }
    
    public int dayCount(){
    int days=0;
    
    while (! c5.after(c6)) {
     days++;
     c5.add(Calendar.DATE, 1);
     }
    
    return days;
    }
    
    
  }
