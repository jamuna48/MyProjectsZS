package authorizatiom.DBProjcet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLConnectionToJava {
	private String url;
	private String userName;
	private String password;
	private String ipAddress;
	Scanner scan = new Scanner(System.in);
	String query = "";
	Db_Operation db;
	CRUD_Operation co;

	public SQLConnectionToJava() {

		System.out.println("enter your userName......");
		this.userName = scan.nextLine();
		System.out.println("enter your password......");
		this.password = scan.nextLine();
		System.out.println("enter your ip address");
		this.ipAddress = scan.nextLine();
		url = "jdbc:mysql://" + ipAddress + ":3306/";

		db = new Db_Operation(url, userName, password);

		co = new CRUD_Operation(db);

		showMenu();
	}

	public void showMenu() {

		boolean flag = true;
		while (flag) {
			System.out.println(flag);
			System.out.println("please select 1  to 6");
			System.out.println("1.show databases");
			System.out.println("2.use database name ");
			System.out.println("3.show tables");
			System.out.println("4.show data from the table");
			System.out.println("6.show history");
			System.out.println("7.deleteOPeration");
			System.out.println("8.insert.OPERATION..");
			System.out.println("9.createOpertions");
			System.out.println("10.LOG OUT");

			int choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {

			case 1:
				query = "show databases;";
				break;
			case 2:
				System.out.println("enter your database name");

				String dbName = scan.nextLine();
				query = "use " + dbName + ";";
				if (!db.excuteQuery1(query)) {
					System.out.println("DATA BASE CHANGED");
				}
				break;
			case 3:
				query = "show tables;";
				break;

			case 4:
				System.out.println("enter your table name...");
				String table = scan.nextLine();
				query = "select * from " + table + ";";
				break;

			case 6:
				Db_Operation db1=new Db_Operation("jdbc:mysql://localhost:3306/USERSHISTORY","root","Jamuna@434");

				if (userName.equals("root")) {
					String display = "select * from usersQueryHistory;";
					db1.excuteQuery1(display);
				} else {
					System.out.println("\033[31m" + "Access Denied : this is only for super admin");
					System.out.println("\033[0m");
					System.out.println("Do you want yours history...... type yes or no");
					String isNeed = scan.nextLine();
					db.yoursHistory(userName, isNeed);
				}
				break;
			case 7:
				co.delete_operation(userName);
				break;
			case 8:
				co.insertValue();
				break;
			case 9:
				co.createOperation();
				break;
			case 10:
				System.out.println("log out successfully");
				flag = false;
				break;
			default:
				System.out.println("please choose any value");

			}
			if (choice != 2 && choice != 6 && choice != 7 && choice != 8 && choice != 10) {
				db.excuteQuery1(query);

			}
			if (choice != 10) {
				db.insertQuery(userName, query);
			}

		}

	}

	public static void main(String[] args) {
		SQLConnectionToJava answer = new SQLConnectionToJava();

	}

}
