
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {

	private Socket s;

	public ClientThread(Socket _s) {
		this.s = _s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String str = "", str2 = "";
			while (!str.equals("stop")) {
				str = din.readUTF();
				System.out.println("Client says: " + str);
				dout.writeUTF("Echo : " + str);
				str2 = br.readLine();
				dout.writeUTF(str2);
				dout.flush();
			}
			din.close();
			s.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
