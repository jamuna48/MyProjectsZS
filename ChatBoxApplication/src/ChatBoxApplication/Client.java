package ChatBoxApplication;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	Scanner sc = new Scanner(System.in);

	public Client() {
		System.out.println("enter your valid portNumber......");
	    int portNo=sc.nextInt();
	    sc.nextLine();
		clientMessage(portNo);
	}

	public void clientMessage(int portNumber) {
		Socket socket = null;
		DataOutputStream input = null;
		DataInputStream learn = null;
		InputStreamReader inputRead = null;
		BufferedReader readServer = null;
		PrintWriter write=null;
		try {
			socket = new Socket("localhost", portNumber);// trow unkonownHost exception,ioexception

			input = new DataOutputStream(socket.getOutputStream());

//			 write = new PrintWriter(input);
			write = new PrintWriter(socket.getOutputStream(),true);

			learn = new DataInputStream(socket.getInputStream());

			inputRead = new InputStreamReader(learn);

//			readServer = new BufferedReader(new InputStreamReader(System.in));

			String reply = "";
			String read = "";
			
			while (!reply.equalsIgnoreCase("bye")) {
				System.out.println("enter your message...");
				reply =sc.nextLine();
				write.println(reply);
//				write.flush();

			
				if (learn.available() > 0) {
					read =(String)learn.readUTF();
				} else {
					continue;
				}
							
				System.out.println("server's message : " + read);
				
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void main(String args[]) {
		Client client=new Client ();

	}

}
