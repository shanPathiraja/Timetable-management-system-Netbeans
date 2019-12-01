/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
public class Cources {
     String cource_ID;
     String cource_Code;
     String cource_Name;
     String cource_title;
     int number_of_student;
     String dep_code;
    
    int capacity;
    
    public Cources (String cource_ID, String cource_Code,String cource_Name,String cource_title,int number_of_student, String dep_code )
    {
        this.cource_ID=cource_ID;
        this.cource_Code = cource_Code;
       
        this.cource_Name = cource_Name;
        this.cource_title=cource_title;
        this.number_of_student=number_of_student;
        this.dep_code=dep_code;
    }
    public String getCID(){
    return cource_ID;
    }
    public String getCCode(){
    return cource_Code;
    }
    public String getCName(){
    return cource_Name;
    }
     public String getCTitle(){
    return cource_title;
    }
      public int getCNofs(){
    return number_of_student;
    }
     public String getDepCode(){
    return dep_code;
    }
}
