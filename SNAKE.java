/*
 * MAN EATER Cloud Technology v1.0 by Gary Wetter
 * 
 * Thursday, May 1st, 2014
 * 
 * This is SNAKE, MEG's slave to help protect men from Mary Mary v2.0 and her man eaters.
 * Currently using TCP(Transmission Control Protocol).
 * 
 */

import java.io.*; import java.net.*; import java.util.*; 

public class SNAKE 
{     
  private static InetAddress host;         
  private static final int PORT = 1234;
  
  public static void main(String[] args) 
  {
    System.out.println("\n SNAKE v1.0\n Man Eater Guardian Server SLAVE - MECT by\n Gary Wetter \n Thursday, May 1st, 2014\n");
    
    System.out.println("Please Standby...\n");
    
    System.out.println("Penetrating MEG...\n"); 
    
    try
    { 
      host = InetAddress.getLocalHost();
    }
    catch(UnknownHostException uhEx)
    { 
      System.out.println("Host ID not found!");
      System.exit(1);
    }
    
    accessServer();
  }
  
  private static void accessServer()
  { 
    Socket link = null; //Step 1. //Step 1.
    
    try
    {
      link = new Socket(host,PORT); 
      Scanner input = new Scanner(link.getInputStream()); //Step 2. 
      PrintWriter output = new PrintWriter( link.getOutputStream(),true); //Step 2. 

       //Set up stream for keyboard entry...
      Scanner userEntry = new Scanner(System.in);
      String message, response;
      
      do
      { 
        System.out.print("Enter Command: ");
        message = userEntry.nextLine();
        output.println(message); //Step 3.
        response = input.nextLine(); //Step 3.
        System.out.println("\n MEG >> "+response);
      }
      while (!message.equals("KILL IT!"));
    }
    catch(IOException ioEx)
    {
      ioEx.printStackTrace();
    }
    
    finally
    { 
      try
      { 
        System.out.println("\n* Closing connection... *");
        link.close(); //Step 4.
      }
      catch(IOException ioEx)
      {
        System.out.println("Unable to disconnect!");
        System.exit(1);
      }
    }
  }
}