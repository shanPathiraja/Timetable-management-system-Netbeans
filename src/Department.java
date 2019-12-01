/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shanb
 */
public class Department {
    
     String dep_ID,Department_Code,Department_Name;
    
    int capacity;
    
    public Department (String dep_ID, String Department_Code,String Department_Name)
    {
        this.dep_ID=dep_ID;
        this.Department_Code = Department_Code;
       
        this.Department_Name = Department_Name;
    }
    public String getDepID(){
    return dep_ID;
    }
    public String getDepCode(){
    return Department_Code;
    }
    public String getDepName(){
    return Department_Name;
    }
    
}
