import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

class Client {
	public static void main(String args[]) throws Exception {
		Socket s = new Socket("127.0.0.1", 6566);
		Random rand = new Random();
		
		Integer client_id = rand.nextInt(100);
		
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = "", str2 = "";
		dout.flush();
		
		str.concat(client_id.toString()+": ");
		while (!str.equals("stop")) {
			str = br.readLine();
			dout.writeUTF(str);
			dout.flush();
			str2 = din.readUTF();
			System.out.println("Server says: " + str2);
		}

		dout.close();
		s.close();
	}
}
