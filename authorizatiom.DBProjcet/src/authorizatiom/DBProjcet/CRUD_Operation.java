package authorizatiom.DBProjcet;

import java.util.Scanner;

public class CRUD_Operation {
	Scanner sc = new Scanner(System.in);
	String query2;
	Db_Operation dbo = null;
	SQLConnectionToJava sq;
	
   public CRUD_Operation() {
	   
   }
	public CRUD_Operation(Db_Operation db) {
		dbo = db;
	}
	public void delete_operation(String username) {
		try {
		System.out.println("please choose 1 0r 2 \n" + "1.delete database \n" + "2.delete table \n 3.createOpertionas \n-1 for back <-");
		int choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			String show_db = "show databases;";
			dbo.excuteQuery1(show_db);
			System.out.println("enter your DATABASE name-----");
			String db_name = sc.nextLine();
			String query = "drop database " + db_name + ";";
			dbo.excuteQuery1(query);
			break;
		case 2:
			deleteTable();
			break;
		case 3:
			createOperation();
			break;
		case -1:
			sq.showMenu();
			break;
		default:
			System.out.println("please choose any option");
		}
		}catch(Exception e) {
			e.getMessage();
		}
	}

	public boolean useDatabase() {
		System.out.println("enter your database name____");
		String db_name = sc.nextLine();
		String query = "use " + db_name + ";";
		boolean flag = dbo.excuteQuery1(query);
		System.out.println("database changed------");
		return flag;

	}

	public void descTables() {
		System.out.println("enter your table name........");
		String tb_name = sc.nextLine();
		String table_name = "desc " + tb_name + ";";
		dbo.excuteQuery1(table_name);

	}

	public void showTables() {
		String tables = "show tables;";
		dbo.excuteQuery1(tables);

	}

	public boolean deleteTable() {
		System.out.println("if you forgot your database please \n 1.yes \n 2.no ");
		//String table_name="";
		int choice = sc.nextInt();
		sc.nextLine();
		if (choice == 1) {
			String show_db = "show databases;";
			dbo.excuteQuery1(show_db);
		}
		
		useDatabase();

		System.out.println("enter your tableName-----------");
		showTables();
		String table_name = sc.nextLine();
		
		String query = "drop table" + table_name + ";";
		boolean flag = dbo.excuteQuery1(query);
		return flag;

	}
	public void  createOperation() {
		
     System.out.println("enter the number to creating...\n 1.creating database \n 2.creating table \n -1 for back <");
     int num=sc.nextInt();
     sc.nextLine();
     switch(num) {
     case 1:
    	 creatingDb();
     case 2:
    	
    	 System.out.println("Do you want to create the table and insert value ? Enter the value \n 1.yes 2.back (<)");
    	 int choice= sc.nextInt();
    	 sc.nextLine();
    	 if(choice ==1) {
    		creatingTable();
    	 }
    	 if(choice==2) {
    		 sq.showMenu(); 
    	 }
    	 
    	  
     
     }
     
		
		
		
	}
	public boolean creatingTable() {
		boolean flag=true;
		boolean flag1=true;
		while(flag) {
			boolean isEX=useDatabase();
			if(!isEX) {
				System.out.println("enter you table name.. and give columns names with data type with closing brackets \n eg (id tinyint primary key auto_incrememnt,name varchar(20) not null)");
				String tableName=sc.nextLine();
				query2=" create table  "+tableName+";";
				flag1=dbo.excuteQuery1(query2);
				insertValue();	
				flag=false;
			}
			else {
				System.out.println("please enter your correct value");
			}
		}
		return flag1;
	}
   public boolean creatingDb() {
	   System.out.println("enter your database name....");
  	 String dbName=sc.nextLine();
  	 String query1="create database "+dbName+" ;";
  	 boolean flag=dbo.excuteQuery1(query1);
	 return flag;
   }
	public boolean insertValue() {
		
		String show_db = "show databases;";
		dbo.excuteQuery1(show_db);

		useDatabase();

		showTables();

		descTables();

		System.out.println("Enter the table name where you want to insert values:");
		String tableName = sc.nextLine();

		System.out.println("Enter the column names separated by commas (e.x., column1, column2):");
		String columns = sc.nextLine();

		System.out.println("Enter the values for these columns separated by commas (e.x., value1, value2):");
		String values = sc.nextLine();

		String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ");";
		boolean flag = dbo.excuteQuery1(query);

		if (!flag) {
			System.out.println("Values inserted successfully into " + tableName + "!");
		} else {
			System.out.println("Failed to insert values into " + tableName + ". Please check your input.");
		}

		return flag;
	}

}
