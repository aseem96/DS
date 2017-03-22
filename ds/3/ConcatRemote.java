
import java.rmi.*;  
import java.rmi.server.*;  

@SuppressWarnings("serial")
public class ConcatRemote extends UnicastRemoteObject implements Concater{  

	protected ConcatRemote() throws RemoteException
	{
		super();
	}

	public String strconcat(String s1, String s2) throws RemoteException
	{
      return (s1+s2);
    }  
}  
