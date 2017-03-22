
import java.rmi.*;  
import java.rmi.registry.*;  

public class MyServer
{  
	public static void main(String args[])
	{  
		try
		{  
			Concater stub=new ConcatRemote();  
			Naming.rebind("rmi://127.0.0.1:5001/Concater",stub);  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
	}  
}  

