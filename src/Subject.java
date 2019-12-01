/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
public class Subject implements Comparable<Subject>{
    String sub_Code,sub_Name,course_code;
    float exame_Hours;
    int Credit;
    boolean token = false;
    
    public Subject (String sub_Code,String sub_Name,String course_code,float exame_Hours,int Credit)
    {
        this.sub_Name=sub_Name;
        this.sub_Code = sub_Code;
        this.exame_Hours=exame_Hours;
        this.Credit = Credit;
        this.course_code=course_code;
    }
    public String getSubName(){
    return sub_Name;
    }
    public String getSubCode(){
    return sub_Code;
    }
    public String getCourseCode(){
        return course_code;
    }
    public float getExameHour(){
    return exame_Hours;
    }
    public float getCredit(){
    return Credit;
    }
    public void tokenT(){
        
        token= true;
    }
    public void tokenF(){
        token = false;
    }
    @Override     
  public int compareTo(Subject sub) {          
    return (this.getCredit() < sub.getCredit() ? -1 : 
            (this.getCredit()== sub.getCredit()? 0 : 1));     
  }   
    
}
