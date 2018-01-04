package com.xav.model;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
public class ObjectToJsonEx {
public static void main(String[] args) {
	TicketInfo emp = new TicketInfo();
	emp.setCategory("1");
	emp.setFullName("abhi");
	emp.setUserName("akumar1");
	
     ObjectMapper mapperObj = new ObjectMapper();
      
     try {
         // get Employee object as a json string
         String jsonStr = mapperObj.writeValueAsString(emp);
         System.out.println(jsonStr);
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
}
}
