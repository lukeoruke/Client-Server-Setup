import java.net.*;
import java.io.*;
import java.util.*;
class Client {
  public static void main(String[] args) {

    //Obtain information about Server
    Scanner scan = new Scanner (System.in);
    System.out.println("Enter the IP");
    String hostName = scan.next();
    System.out.println("Enter the port");
    int portNumber = scan.nextInt();

    DatagramSocket aSocket = null; //create socket
    try {
      //sending data
      aSocket = new DatagramSocket();
      InetAddress address = InetAddress.getByName(hostName); 

      while(true){
        //send information to server
        System.out.println("Enter the message");
        String message = scan.next();
        byte[] m = new byte[1024]; //create a byte buffer to store message
        m = message.getBytes(); 
        DatagramPacket request = new DatagramPacket(m,m.length,address, portNumber); //data that will be sent
        aSocket.send(request); //send data to server
        
        //accepting data from the server (Should be capitalized)
        byte[] buffer = new byte[1024];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(reply); //recieve data from server
        //print the result
        System.out.println("Reply: "+new String(reply.getData()));
      }

    }catch(SocketException e){
      System.out.println("Socket: "+ e.getMessage());
    }catch(IOException e){
      System.out.println("IO: "+ e.getMessage());
    }finally{
      if(aSocket != null)
        aSocket.close();
    }
  }




  
}
