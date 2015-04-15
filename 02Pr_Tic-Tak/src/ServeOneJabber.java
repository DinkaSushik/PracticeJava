package src;
import java.io.*;
import java.net.*;

public class ServeOneJabber extends Thread {
	public ServeOneJabber(Socket s) throws IOException {
		socket = s;
		byteOut = socket.getOutputStream();
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(byteOut)), true);
		start();
	}

	public void run() {
		try{
			System.out.println("Connection opened: " + socket);

			String pass = in.readLine();
			if(pass.equals(PASS)){
				out.println("correct");
				out.println(PATH.substring(PATH.lastIndexOf('/')+1, PATH.length()));
				
				FileInputStream fin = new FileInputStream(new File(PATH));
				int b = fin.read();
				while(b != -1){
					byteOut.write(b);
					b = fin.read();
				}
				fin.close();
				byteOut.write(-1);
			}else{
				out.println("incorrect");
			}
		}catch(IOException e){
			System.err.println("IO Exception");
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket wasn't closed.");
			}
		}
	}

	private Socket socket;
	private OutputStream byteOut;
	private BufferedReader in;
	private PrintWriter out;
	private static final String PASS = "pass";
	private static final String PATH = ".//t.png";
}
