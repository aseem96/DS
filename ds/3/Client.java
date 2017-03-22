
import java.rmi.*;  

public class Client
{  
	public static void main(String args[])
	{  
		try
		{  
			Concater stub=(Concater)Naming.lookup("rmi://127.0.0.1:5001/Concater");  
			System.out.println(stub.strconcat("Good", "Morning")); 
				
		}catch(Exception e)
		{
			System.out.println(e);
		}  
	}  
}  

