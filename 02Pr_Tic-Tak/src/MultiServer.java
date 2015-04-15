package src;
import java.io.*;
import java.net.*;

public class MultiServer {
	static final int PORT = 8080;
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server has started.");
		
		try{
			while(true){
				Socket socket = serverSocket.accept();
				try{
					new ServeOneJabber(socket);
				}catch(IOException e){
					socket.close();
				}
			}
		}finally{
			serverSocket.close();
		}
	}
}
