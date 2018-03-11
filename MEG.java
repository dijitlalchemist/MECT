/*
 * MAN EATER Cloud Technology v1.0 by Gary Wetter
 * 
 * Thursday, May 1st, 2014
 * 
 * This is MEG, the man eater gaurdian server.
 * Currently using TCP(Transmission Control Protocol).
 * 
 */

import java.io.*; 
import java.net.*; 
import java.util.*;

public class MEG 
{ 
  //declare sockets and ports
  private static ServerSocket servSock; 
  private static final int PORT = 1234; 
  
  public static void main(String[] args) 
  { 
    System.out.println("\n MEG v1.0\n Man Eater Guardian Server - MECT by\n Gary Wetter \n Thursday, May 1st, 2014\n");
    
    System.out.println("Please Standby...\n");
    
    System.out.println("Opening port...\n"); 
    
    try 
    {   
      //create server socket object
      servSock = new ServerSocket(PORT);
      System.out.println("Port Opened...\n");
    }       
    catch(IOException ioEx)       
    { //Step 1.    
      System.out.println("Unable to attach to port!");    
      System.exit(1); 
    } 
    
    do 
    { 
      System.out.println("MEG is Waiting on SNAKE to make penetration...\n");
      handleClient();
    }
    while(true);
  }
  
  private static void handleClient() 
  {    
    // declare socket object.
    Socket link = null; 
    
    try 
    { 
      // invoke the method accept of class ServerSocket, bind to socket object. 
      // Server is in a waiting status...
      link = servSock.accept();

      //Setup I/O streams.
      
      //get ref's from returned socket and wrap with scanner object to obtain string-oritend data
      Scanner input = new Scanner(link.getInputStream());  
      
      // wrap a PrintWriter object around the OutputStream object returned by method getOutputStream. 
      // flush the buffer on every call by setting construcor arg to true.
      PrintWriter output = new PrintWriter( link.getOutputStream(),true);
      
      // Send and receive data.
      
      //declare a varibale for message counter - lets keep track.
      int numMessages = 0; 
      
      //declare vairable to receive data using the nextLine()
      //first message in
      String message = input.nextLine(); 
      
      while (!message.equals("KILL IT!")) 
      { 
        // message out to server
        System.out.println("Message received.");
        
        //increment message count
        numMessages++; 
        
        if(message.equals("STATUS"))
        {
          //declare vairable to receive data using the println()
          //bind CUSTOM MESSAGE TO VAR
          message = " I have detected a man eater at 12 o'clock. Do not appraoch, flirt, or buy her a drink."; 
        }
        
        //declare vairable to receive data using the println()
        //bind message number and message
          output.println("SNAKE " + numMessages + ": " + message);
        
        //sequential messages in
        message = input.nextLine(); 
      } 
      
      //total message count sent on CLOSE
      output.println(numMessages + " messages received.");
    } 
    catch(IOException ioEx) 
    {     
      ioEx.printStackTrace(); 
    } 
    
    finally 
    { 
      try 
      {
        //Close the connection (after completion of the dialogue).
        System.out.println("\n* Closing connection... *");
        link.close(); //close method of socket class
        System.exit(0);
      }
      catch(IOException ioEx)     
      {        
        System.out.println("Unable to disconnect!");        
        System.exit(1);
      }
    }
  }
}