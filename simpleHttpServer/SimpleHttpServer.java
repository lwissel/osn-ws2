import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

   private String rootDirectory;
   private int port;
	
	public SimpleHttpServer(String rootDirectory, int port) {
      this.rootDirectory = rootDirectory;
      this.port = port;
	}

	/**
	 * #1. Create a server socket, listening on <port>
	 * #2. Use a loop to wait and accept new connections
	 * #3. When you accept a new connection, create a <handler> to handle it.
	 * #4. You are not required to consider how to stop the loop
	 */
	public void start() {
		ServerSocket serverSocket;

      try {
         serverSocket = new ServerSocket(port);

         while (true) {
            Socket clientSocket = serverSocket.accept();
            SimpleHttpHandler connectionHandler = new SimpleHttpHandler(rootDirectory);
            connectionHandler.handle(clientSocket);
         }
      }
      catch (IOException e) {
         System.out.println("Listen: " + e.getMessage());
      }
	}
	
	/*
	 * Parameters:
	 * <root directory> <port>
	 * 
	 * DO NOT Modify the main method
	 * 
	 */
	public static void main(String[] args){
		SimpleHttpServer server = new SimpleHttpServer(args[0], Integer.parseInt(args[1]));
		server.start();
	}

}
