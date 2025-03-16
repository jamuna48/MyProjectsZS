package ChatBoxApplication;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	Scanner sc = new Scanner(System.in);

	public Server() {
		System.out.println("enter your valid portNumber......");
	    int portNo=sc.nextInt();
	    sc.nextLine();
		serverMessage(portNo);
	}

	public void serverMessage(int portNumber) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		PrintWriter write=null;
		DataOutputStream input = null;
		DataInputStream learn = null;
		InputStreamReader inputRead = null;
		BufferedReader readServer = null;
		try {
			serverSocket = new ServerSocket(portNumber);// trow unkonownHost exception,ioexception
			System.out.println("waiting for connection....");
			socket = serverSocket.accept();
			System.out.println("connected successfully.....");
			input = new DataOutputStream(socket.getOutputStream());

//			write = new PrintWriter(input);
			write = new PrintWriter(socket.getOutputStream(),true);

			learn = new DataInputStream(socket.getInputStream());

//			inputRead = new InputStreamReader(learn);

//			readServer = new BufferedReader(new InputStreamReader(System.in));

			String reply = "";
			String read = "";
			while (!reply.equalsIgnoreCase("bye")) {
			if (learn.available() > 0) {
				read =(String)learn.readUTF();
			} else {
				continue;
			}
			System.out.println("client's message : " + read);
				
		
			reply = sc.nextLine();
			write.println(reply);
//			write.flush();
			}

		} catch (Exception e) {
			System.out.println("error ocurred in socket program" + e.getMessage());
		}


	}

	public static void main(String args[]) {
       Server server=new Server();
	}
}
