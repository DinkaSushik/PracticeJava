package src;
import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) throws IOException{
		InetAddress address = InetAddress.getByName(null);
		System.out.println("Address = "+ address);
		Socket socket = new Socket(address, Server.PORT);
		
			}else{
				System.out.println("Password is incorrect.");
			}
		}finally{
			System.out.println("closing...");
			socket.close();
		}
	}
}
