import java.io.*;
import java.net.*;
class client
{
 public static void main(String[] args) throws Exception
 {
  Socket sock = new Socket("127.0.0.1", 3000); 
  BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in)); 
  OutputStream ostream = sock.getOutputStream(); 
  PrintWriter pwrite = new PrintWriter(ostream, true); 
  InputStream istream = sock.getInputStream(); 
  BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));   
  System.out.println("Client ready, type and press Enter key");
  String receiveMessage, sendMessage,temp,temp1; 
  while(true) 
  {
   System.out.println("\nEnter operation to perform(add,sub,mul,div,rev,str)....");
   temp = keyRead.readLine();
   sendMessage=temp.toLowerCase(); 
   pwrite.println(sendMessage);
   if(temp.compareTo("rev")==0){
	System.out.println("Enter number to reverse :");
	sendMessage = keyRead.readLine();
	pwrite.println(sendMessage);
   }
   else if(temp.compareTo("str")==0){
	System.out.println("Enter string in lower case :");
	temp1 = keyRead.readLine();
	sendMessage=temp1.toLowerCase();
	pwrite.println(sendMessage);	
   }
   else{
   	System.out.println("Enter first parameter :");
   	sendMessage = keyRead.readLine(); 
   	pwrite.println(sendMessage);
   	System.out.println("Enter second parameter : ");
   	sendMessage = keyRead.readLine(); 
   	pwrite.println(sendMessage);
   }
   System.out.flush(); 
   if((receiveMessage = receiveRead.readLine()) != null) 
    System.out.println(receiveMessage); 
  }
 }
}
