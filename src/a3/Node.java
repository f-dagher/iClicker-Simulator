package a3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Node {
	
	static int N;

	public static void main(String[] args) {
		try 
		{	
//			System.out.println("Client Side...");
//			
//			System.out.println("Enter the I.P Address you wish to connect to...");
			Scanner scanner = new Scanner(System.in);
			
			String hostName = "localhost";
//			System.out.println("Enter the Port Number as specified by the Server");
			int portNumber = 24606;
			int count = 0;
			
			System.out.println("Type JOIN to connect the clicker");
			
			if (scanner.nextLine().equals("JOIN"))
			{
				Socket socket = new Socket(hostName, portNumber);
				
				//Setup streams in order to read and write to the server 
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		        
		        //Create new file
//		        File file = new File("Received.out");
//		        FileWriter fr = new FileWriter(file, false);
//		        BufferedWriter fWrite = new BufferedWriter(fr);
		        
		        String userInput;
		        System.out.println("What is your Student Number");
		
		        
		        
		        
		        while(!(userInput = scanner.nextLine()).equals("DONE"))
		        {
			        if (count == 0)
			        {
			        	System.out.println("Type the Question number, followed by the answer with a space in between and press enter. For example: 11 A");
			        	System.out.println("When you are done enter: DONE");
			        }
			       	out.println(userInput);
			       	count++;
			        
		        }
		        
		        socket.close();
		        out.close();
		        in.close();
		        stdIn.close();
			}
			
			
			//Connect to server
			
	        System.out.println("Exited");
	     
	        //close all streams
	        scanner.close();
	        


	        
		} catch (IOException ioe) {
			System.err.println("Error in I/O");
            System.err.println(ioe.getMessage());
            System.exit(-1);
		}

	}

}
