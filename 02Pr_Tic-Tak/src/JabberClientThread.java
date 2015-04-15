package src;
import java.net.*;
import java.io.*;

class JabberClientThread extends Thread {
	public JabberClientThread(InetAddress addr) {
		System.out.println("Starting client number " + id);
		threadcount++;

		try{
			socket = new Socket(addr, MultiServer.PORT);
		}catch(IOException e){
			System.err.println("Can not connect to the server.");
		}
		try {
			byteIn = socket.getInputStream();
			in = new BufferedReader(new InputStreamReader(byteIn));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			start();
		}catch(IOException e){
			try{
				socket.close();
			}catch(IOException e2){
				System.err.println("Socket was not closed.");
			}
		}
	}

	public static int threadCount() {
		return threadcount;
	}

	public void run(){
		try{
			System.out.println("Socket = " + socket);
			
			out.println("pass");
			String serverAnswer = in.readLine();
			if(serverAnswer.equals("correct")){
				FileOutputStream fout = new FileOutputStream(new File(".//1//"+in.readLine()));
				int b = byteIn.read();
				while(b != -1){
					fout.write(b);
					b = byteIn.read();
				}
				fout.close();
			}else{
				System.out.println("Password is incorrect.");
			}
		}catch(IOException e){
			System.err.println("IO Exception");
		}finally{
			try{
				socket.close();
			}catch(IOException e){
				System.err.println("Socket was not closed.");
			}
		}
	}

	private Socket socket;
	private InputStream byteIn;
	private BufferedReader in;
	private PrintWriter out;
	private static int counter = 0;
	private int id = counter++;
	private static int threadcount = 0;
}