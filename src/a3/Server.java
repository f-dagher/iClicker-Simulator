import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * Creates multiple threads for each connection
 */

//class inputThread implements Runnable{
//	private String input;
//	
//	inputThread( String input){
//		this.input = input;
//	}
//	
//	public void run() {
//		if (input.equals("EXIT"))
//		{
//			System.exit(0);
//		}
//	}
//}

class serverThread implements Runnable{
	private Socket clientSocket;
	
	
	serverThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	/*
	 * Send back the time to the client. Runs for every thread
	 */
	public void run() {
		Logger logger = Logger.getLogger("My Log");
		int flag = 0;
		int c = 0;
		String done = "DONE";

		try {
			System.out.println("Connection from: " + clientSocket.getInetAddress().getHostAddress() + " at " + new Date().toString());			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			ArrayList<String> ans = new ArrayList<String>();
			Hashtable<String, String> ht = new Hashtable<String, String>();
			String finalAns = "";
			//Date date = new Date();
			
			
			//put all inputs into an arraylist
			String inputLine;
			while((inputLine = in.readLine()) != null)
			{	
				ans.add(inputLine);
				System.out.println(ans.toString());
				//System.out.println(inputLine);
				if (inputLine.equals(done))
					break;
			}
			System.out.println(ans.toString());
			String str;
			
			
			
			//split the string for every index of the array list at a space
			//put the first half as the key into a hash map and the second part as it value
			for(int i = 1; i < ans.size(); i++)
			{
				str = ans.get(i);
				String[] split = str.split(" ");
				String key = split[0];
				String value = split[1];
				
				//if the user changed their answer, update the hashtable
				//i.e if the key already exists that means the user has answered it before and so replace the answer with the updated answer.
				if(ht.containsKey(key)) 
				{
					ht.remove(key);
					ht.put(key, value);
				}
				else
					ht.put(key, value);
			}
			System.out.print(ans.get(0)+ " ");
			System.out.println(ht.toString());
			

			
			out.close();
			
			// log the events
	        logger.addHandler(Server.fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        Server.fh.setFormatter(formatter);
	        
	        if(c==0)
	        {
	        
		        finalAns = ("\n\nStudent: " + ans.get(0)) + "\n"; 
		        for(int i = 0; i < ht.size(); i++)
		        {
		        	String ques = Integer.toString(i +1);
		        	finalAns = finalAns + ("q" + ques + ": "+ ht.get(ques)) + "\n";
		        	System.out.println("q" + ques + ": "+ ht.get(ques));
		        }
		        logger.info(finalAns);
		        c++;
	        }      
	        out.close();
	        clientSocket.close();	    
	        
			
									
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

public class Server {
	
	final static int PORTNUMBER = 24606;
	private static ServerSocket serverSocket;
    private static serverThread serverThread;
    private static Thread thread;
    private static Thread exitThread;
    private static inputThread inputThread;
    
    public static FileHandler fh;
    
/*/
 * wait for a connection and once a connection has been made, create a new thread to do the intended calculations
 */
	public static void main(String[] args) {
	
			try 
			{
				//create the file only once
				fh = new FileHandler("server.log");
				Scanner scanner = new Scanner(System.in);
				
				System.out.println("Welcome to the Server");
				int portNumber = PORTNUMBER;  

				//Setup a connection
				serverSocket = new ServerSocket(portNumber);
				
				System.out.println("What is the time limit for the quiz in seconds? ");
				int time = scanner.nextInt();
				time = time*1000;
				long startTime = System.currentTimeMillis();
				System.out.println("Waiting for connections...");
				
				//Time out the server so that no more students can connect 
				serverSocket.setSoTimeout(time);
				
				//listen for a connection, and create a new thread for the connection
				while(true)
				{	
					serverThread = new serverThread(serverSocket.accept());
					//inputThread = new inputThread(scanner.nextLine());
					thread = new Thread(serverThread);
					//exitThread = new Thread(exitThread);
					//exitThread.start();
					thread.start();
				}
				
				//serverSocket.close();
				
				
			} catch (IOException ioe) {
				System.err.println("Error in I/O");
	            System.err.println(ioe.getMessage());
	            System.exit(-1);
			}
		}

	

}
