import java.net.*;
import java.io.*;
import java.util.*;
class Server {
  public static void main(String[] args) {
    Scanner scan = new Scanner (System.in);
    System.out.println("Enter the port");
    int portNumber = scan.nextInt();
    
    DatagramSocket aSocket = null; //create socket
    try{
      aSocket = new DatagramSocket(portNumber);
      while(true){
        byte[] buffer = new byte[1024]; //create a byte buffer to store message
        //accept data from server
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(request); //receieve packet from client
        String statement = new String(request.getData());
        System.out.println("Packet recieved:" + statement);

        //send data to client
        String data = statement.toUpperCase(); //change the message from client into uppercase
        buffer = data.getBytes();
        InetAddress clientAddress = request.getAddress();
        int clientPort = request.getPort();
        DatagramPacket reply = new DatagramPacket(buffer,buffer.length,clientAddress,clientPort);
        aSocket.send(reply); //send the newly updated message to client
      }
    }catch(SocketException e){
      System.out.println("Socket: " + e.getMessage());
    }catch(IOException e){
      System.out.println("IO: "+ e.getMessage());
    }finally{
      if(aSocket != null)
       aSocket.close();
      }
  }
  
}