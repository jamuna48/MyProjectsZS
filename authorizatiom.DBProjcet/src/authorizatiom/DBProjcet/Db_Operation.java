package authorizatiom.DBProjcet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Db_Operation {
	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	
//	boolean flag = false;
	public Db_Operation() {
		
	}

	public Db_Operation(String url, String userName, String password) {
		try {
			conn = DriverManager.getConnection(url, userName, password);
			this.stmt = conn.createStatement();
//			System.out.println("Connection created");
		} catch (SQLException e) {
			System.out.println("+--------------------------------------------+");
			   System.out.println("\u001B[32m"+e.getMessage());
			   System.out.println("\u001B[0m"+"+--------------------------------------------+");
		}
		
	}

	

	public boolean excuteQuery1(String query) {
   boolean flag = false;
		try {

			flag = stmt.execute(query);
			if (flag) {

				rs = stmt.executeQuery(query);
				rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				printTable(rs);
			}
		} catch (SQLException e) {
			 System.out.println("\033[31m"+e.getMessage());
			 System.out.println("\033[0m");
		}
            
		return flag;

	}
	void printTable(ResultSet resultSet) throws SQLException {
	    // Define color codes for formatting
	    String RESET = "\u001B[0m"; // Reset color
	    String HEADER_COLOR = "\u001B[34m"; // Blue color for header
	    String DATA_COLOR = "\033[38;2;128;0;0m"; // Green color for data
	    String BORDER_COLOR = "\033[38;5;2m"; // Yellow color for borders

	    ResultSetMetaData metaData = resultSet.getMetaData();
	    int columnCount = metaData.getColumnCount();
	    int[] columnWidths = new int[columnCount];

	    // Calculate the max width for each column
	    for (int i = 1; i <= columnCount; i++) {
	        columnWidths[i - 1] = Math.max(30, metaData.getColumnName(i).length());
	    }

	    
	    for (int width : columnWidths) {
	        System.out.print(BORDER_COLOR + "-".repeat(width) + "-+-" + RESET);
	    }
	    System.out.println();

	    
	    for (int i = 1; i <= columnCount; i++) {
	        System.out.printf(HEADER_COLOR + "%-" + columnWidths[i -1] + "s | " + RESET, metaData.getColumnName(i));
	    }
	    System.out.println();


	    for (int width : columnWidths) {
	        System.out.print(BORDER_COLOR + "-".repeat(width) + "-+-" + RESET);
	    }
	    System.out.println();

	    
	    while (resultSet.next()) {
	        for (int i = 1; i <= columnCount; i++) {
	            String value = resultSet.getString(i);
	            System.out.printf(DATA_COLOR + "%-" + columnWidths[i - 1] + "s | " + RESET, value != null ? value : "NULL");
	        }
	        System.out.println();
	    }

	    
	    for (int width : columnWidths) {
	        System.out.print(BORDER_COLOR + "-".repeat(width) + "-+-" + RESET);
	    }
	    System.out.println();
	}
	
	public boolean insertQuery(String userName,String query) {
		
//		String startQuery="use USERSHISTORY;";
		String exeQuery2="insert into usersQueryHistory(user_name,query_name) values(?,?);";
		boolean flag=true ;
		try {
			 Db_Operation db=new Db_Operation("jdbc:mysql://localhost:3306/USERSHISTORY","root","Jamuna@434");
//			 boolean isEntry=stmt.execute(startQuery);
			 PreparedStatement ps1 = db.conn.prepareStatement(exeQuery2);
			 ps1.setString(1, userName);
			 ps1.setString(2,query);
			 //ResultSet rs = ps1.executeQuery();
			 int count = ps1.executeUpdate();
			 System.out.println(count);
              
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
    public void yoursHistory(String username,String isNeed) {
    	
    	String query="select user_name, query_name, time from usersQueryHistory where user_name='"+username+"' order by time desc;";
    	if(isNeed.equalsIgnoreCase("yes")) {
    		Db_Operation db=new Db_Operation("jdbc:mysql://localhost:3306/USERSHISTORY","root","Jamuna@434");
    		db.excuteQuery1(query);
    	}else {
    		System.out.println("THANK YOU ");
    	}
    }
}
