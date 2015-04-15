package src;
import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) throws IOException{
		InetAddress address = InetAddress.getByName(null);
		System.out.println("Address = "+ address);
		Socket socket = new Socket(address, Server.PORT);
		try{
			System.out.println("Socket = " + socket);
			InputStream byteIn = socket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(byteIn));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			
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
		}finally{
			System.out.println("closing...");
			socket.close();
		}
	}
}
