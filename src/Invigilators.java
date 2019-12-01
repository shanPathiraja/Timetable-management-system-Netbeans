/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
public class Invigilators {
    String invigilator_id,invigilator_Name,Address,Tell_no;
    
    
    public Invigilators (String invigilator_id,String invigilator_Name,String Tell_no,String Address)
    {
        this.invigilator_Name=invigilator_Name;
        this.invigilator_id = invigilator_id;
        this.Address=Address;
        this.Tell_no = Tell_no;
    }
    public String getInvName(){
    return invigilator_Name;
    }
    public String getInvCode(){
    return invigilator_id;
    }
    public String getAddress(){
    return Address;
    }
    public String getTell(){
    return Tell_no;
    }
}
