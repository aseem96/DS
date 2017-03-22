import java.io.*;
import java.net.*;
class server
{ 
 public static void main(String[] args) throws Exception 
 { 
  ServerSocket sersock = new ServerSocket(3000); 
  System.out.println("Server ready"); 
  Socket sock = sersock.accept( ); 
  BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in)); 
  OutputStream ostream = sock.getOutputStream(); 
  PrintWriter pwrite = new PrintWriter(ostream, true);  
  InputStream istream = sock.getInputStream(); 
  BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));   
  String receiveMessage, sendMessage,fun;
  int a,b,c,rem;
  while(true) 
  {
   fun = receiveRead.readLine();
   if(fun != null) 
    System.out.println("Operation : "+fun);
   if(fun.compareTo("rev")==0){
	a = Integer.parseInt(receiveRead.readLine());
	System.out.println("Parameter : "+a);
	c = 0;
	while(a>0){
		rem = a % 10;
		a = a / 10;
		c = c * 10 + rem;
	}
	System.out.println("Reverse = "+c);
	pwrite.println("Reverse = "+c);	
   }
   else if(fun.compareTo("str")==0){
	receiveMessage = receiveRead.readLine();
	sendMessage = receiveMessage.toUpperCase();
	System.out.println("Upper Case = "+sendMessage);
	pwrite.println("Upper Case = "+sendMessage);	
   }
   else{
   	a = Integer.parseInt(receiveRead.readLine());
   	System.out.println("Parameter 1 : "+a);
   	b = Integer.parseInt(receiveRead.readLine());
	System.out.println("Parameter 2 : "+b);
   	if(fun.compareTo("add")==0)
   	{
   	 c=a+b;
   	 System.out.println("Addition = "+c);
   	 pwrite.println("Addition = "+c); 
   	}
   	if(fun.compareTo("sub")==0)
   	{
   	 c=a-b;
   	 System.out.println("Substraction = "+c);
   	 pwrite.println("Substraction = "+c); 
   	}
   	if(fun.compareTo("mul")==0)
   	{
   	 c=a*b;
   	 System.out.println("Multiplication = "+c);
  	 pwrite.println("Multiplication = "+c); 
  	 }
  	 if(fun.compareTo("div")==0)
  	 {
  	  c=a/b;
  	  System.out.println("Division = "+c);
  	  pwrite.println("Division = "+c); 
  	 }
   }
   System.out.flush();
  } 
 } 
}
