/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
public class subAndDate {
    String subCode, day;
    int Credit;
    
    public subAndDate(String subCode, String day,int Credit)
    {
        this.subCode =subCode;
        this.day =day;
        this.Credit =Credit;
        
    }
    public String getSub(){
    return subCode;
    }
    public String getday(){
    return day;
    }
    public int getCredit(){
    return Credit;
    }
            
}
