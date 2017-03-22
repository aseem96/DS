
import java.rmi.*;  

public interface Concater extends Remote{

	public String strconcat(String s1, String s2)throws RemoteException; 
}

