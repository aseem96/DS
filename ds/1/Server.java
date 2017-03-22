
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
	static ServerSocket ss = null;

	public static void main(String args[]) {

		System.out.println("Server is now running.");

		Runnable listen = new Runnable() {
			public void run() {
				int i = 1;
				try {
					System.out.println("Listening at port 6566");
					ss = new ServerSocket(6566);
					while (true) {
						Socket s = ss.accept(); 
						if (s != null && i < 10) {
							System.out.println("Creating thread number " + i);
							i++;
							new Thread(new ClientThread(s)).start();
						} else
							break;
					}
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		new Thread(listen).start();
	}
}

