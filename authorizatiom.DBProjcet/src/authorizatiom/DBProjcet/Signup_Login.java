package authorizatiom.DBProjcet;

import java.util.Scanner;

public class Signup_Login extends  Authentication {
	private String name;
	private String password;
	private String mail;
	Scanner scan=new Scanner(System.in);

	public Signup_Login() {
		super();
		System.out.println("Welcome to connecting DB visulizer");
		System.out.println("choose number 1 or 2"+"\n"+"1.SIGNUP"+"\n"+"2.LOGIN");
		int choice=scan.nextInt();
		scan.nextLine();
		
		switch (choice) {
		case 1:
		usersData();	
		signUp(name,password); 
		break;
		case 2:
			usersData();	
			logIn(name, password);
			break;
		default:
			System.out.println("please choose 1or 2 for signup or login");
		
		}
	}
	
	public void usersData() {
		System.out.println("enter your userName......");
		name=scan.nextLine();
		
		System.out.println("enter your password.....");
		password=scan.nextLine();
	
	}
	public static void main(String[] args) {
		 Signup_Login result=new  Signup_Login();
		

	}
}
