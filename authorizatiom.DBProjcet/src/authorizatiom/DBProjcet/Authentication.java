package authorizatiom.DBProjcet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication   {
    private String url="jdbc:mysql://localhost:3306";
    private String name="root";
    private String password="Jamuna@434";
	Connection connect=null;
	Statement state=null;
	ResultSet rs=null;
	ResultSetMetaData setaMeta=null;
	PreparedStatement pstmt =null;
	int count=0;

	Scanner sc=new Scanner(System.in);
	Db_Operation db=new Db_Operation("jdbc:mysql://localhost:3306/","root","Jamuna@434");
	CRUD_Operation open=new CRUD_Operation(db) ;
	

	public Authentication() {
		
		String query1="use USERSNAME;";
		try {
			connect=DriverManager.getConnection(url,name,password);
			state=connect.createStatement();
			boolean flag=state.execute(query1);
//			System.out.println("welcome"+flag);
			
             
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public boolean signUp(String name,String password) {
		String query2="insert into userTable values('"+name+"','"+password+"');";
		
	
		boolean isExecute=false;
		try {
		      isExecute= state.execute(query2);
			System.out.println("successfully"+isExecute);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
		if(!isExecute) {
		System.out.println("Login your userid........\n enter your valid userName");
		String userName=sc.nextLine();
		System.out.println("enter your password...");
		String userPassword=sc.nextLine();
			logIn(userName,userPassword);	
		}
		}
		return isExecute;
		
	}

	
	public void logIn(String name, String password) {
	    String query = "SELECT * FROM userTable WHERE userName = ? AND password = ?;";
	    boolean loginSuccess = false;
	    String newUsername="";
	    String newPassword="";
	    
	    try {
	        pstmt = connect.prepareStatement(query);
	        pstmt.setString(1, name);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();

	        
	        if (rs.next()) { 
	            loginSuccess = true;
	            System.out.println("+--------------------------------------------+");
	            System.out.println("\u001B[32m" + "      WELCOME TO DB VISUALIZER 🥰");
	            System.out.println("\u001B[0m" + "+--------------------------------------------+");
	            SQLConnectionToJava con = new SQLConnectionToJava();
	        } else {
	            System.out.println("\n" + "\u001B[31m" + "INVALID USERNAME AND PASSWORD....");
	            System.out.println("\u001B[0m");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    if (!loginSuccess) {
	        System.out.println("Do you want to: \n1. Sign Up \n2. Retry Login \n3.ForgetPassword (choose 1 or 2 or 3):");
	        int choice = sc.nextInt();
	        sc.nextLine();
	        if(choice==1 || choice==2) {
	        System.out.println("Enter new username:");
              newUsername = sc.nextLine();
            System.out.println("Enter new password:");
            newPassword = sc.nextLine();
	        }
	        switch (choice) {
	            case 1:
	               
	                signUp(newUsername, newPassword);
	                break;
	            case 2:
	                retry(newUsername, newPassword);
	                break;
	            case 3:
	            	System.out.println("enter your mail valid Mailname....");
	        		String mail=sc.nextLine();
	        		SendMail.sendMail(mail);
	        		isValid();
	        		if(isValid()) {
	        			changePassword();
	        		}
	                break;
	        }
	    }
	}
	
	
    public void retry(String name,String password) {
    	
    	
    	if(count==3) {
    		System.out.println("\n"+"\u001B[32m"+"Your login attempts have exceeded the maximum limit");
    		System.out.println("\u001B[0m");
    		count=0;
    	    
    	}
    	else {
    		count++;
    		System.out.println("count ==="+count);
    		logIn(name,password);
    		
    	}
    }
    
    public boolean isValid() {
    	System.out.println("enter you otp...");
    	String otp=sc.nextLine();
        boolean flag=false;
        String orgOtp =SendMail.getOtpString();
        System.out.println(orgOtp);
       if(otp.equals(otp)){
    	   flag=true;
       }
       else {
    	   System.out.println("please enter valid otp ");
       }
    return flag;
    }
	
     public void  changePassword() {
    	   System.out.println("enter your valid username...");
    	   String name=sc.nextLine();
    	   System.out.println("enter your new password..");
    	   String password=sc.nextLine();
    	   
    	   boolean flag=open.useDatabase();
    	   if(!flag) {
    		String query="update userTable set password='"+password+"' where userName='"+name+"';";
    		 boolean isExe=db.excuteQuery1(query);
    		 if(!isExe) {
    			 System.out.println("password successfully changed");
    			 System.out.println(" LOGIN YOUR ACCOUNT \n enter your userName...");
    			 String uName=sc.nextLine();
    			 System.out.println("enter new password..");
    			 String newPass=sc.nextLine();
    			 logIn(uName,newPass);
    		 }
    		  
    	   }
    	   
    	 
     }
	

	public static void main(String[] args) {
		
		 Authentication auth=new  Authentication();
	}

}
