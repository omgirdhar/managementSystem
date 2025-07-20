package com.example.demo.constants;

import java.util.Arrays;
import java.util.List;

public class EnumConstants {

	/* ordinal works with the order of defined fields
	ex. if active(1), inactive(0) is the order then active holds the value 1 
	but the order of active is 0, so in db if it is showing 0 for active it is not wrong 
	cause you put the active on the first place */
    public enum Status{
    	INACTIVE(0), ACTIVE(1);

        private int status;

        Status(int status){
            this.status = status; 
        }

        public int getStatus(){
            return status;
        }
    }

    public enum Role{
        SUPERADMIN(0), ADMIN(1), EMPLOYEE(2);
    	
    	private int role;

        Role(int role){
            this.role = role; 
        }

        public int getRole(){
            return role;
        }
        
        public static List<Role> getAssignableRoles() {
            return Arrays.asList(ADMIN, EMPLOYEE);
        }
    }
    
}
