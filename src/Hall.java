/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */


public class Hall {
    String hall_Code,hall_Name;
    
    int capacity;
    
    public Hall (String hall_Code,String hall_Name,int capacity)
    {
        this.hall_Code=hall_Code;
        this.hall_Name = hall_Name;
       
        this.capacity = capacity;
    }
    public String getHallCode(){
    return hall_Code;
    }
    public String getHallName(){
    return hall_Name;
    }
    public float getHallCapacity(){
    return capacity;
    }
  
}
