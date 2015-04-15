package src;
import java.io.*;
import java.net.*;

public class Server {
	public static final int PORT = 8081;
	private static final String PASS = "pass";
	private static final String PATH = ".//t.png";

	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Started: " + serverSocket);
		try{
			Socket socket = serverSocket.accept();
			try{
				System.out.println("Connection opened: " + socket);
				OutputStream byteOut = socket.getOutputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(byteOut)), true);

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
			}finally{
				System.out.println("closing...");
				socket.close();
			}
		}finally{
			serverSocket.close();
		}
	}
}
