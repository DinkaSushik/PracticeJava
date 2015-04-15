package src;
import java.net.*;
import java.io.*;

public class MultiClient {
	static final int MAX_THREADS = 5;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException{
		InetAddress addr = InetAddress.getByName(null);
		while(true){
			if (JabberClientThread.threadCount() < MAX_THREADS)
				new JabberClientThread(addr);
			Thread.currentThread().sleep(100);
		}
	}
}
