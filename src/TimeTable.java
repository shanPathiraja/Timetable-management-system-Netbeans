/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
public class TimeTable {
    String date, TblName,TblCode;
    
        public TimeTable(String date,String TblName,String TblCode)
        {
            this.date =date;
            this.TblName =TblName;
            this.TblCode =TblCode;
        
        }
        
        public String getDate()
        {
        
        return date;
        }
        public String gettblName()
        {
        return TblName;
        }
        public String getTblCode(){
        
        return TblCode;
        }
    
    
    
}
