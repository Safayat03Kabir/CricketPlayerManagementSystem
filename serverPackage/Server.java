package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private playerDatabase pd = new playerDatabase();

    private Boolean isRunning;
    //private playerDatabase playerDB;


    public Server() {
        isRunning = true;


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown signal received. Stopping server...");
            stopServer();
        }));

        try {
            serverSocket = new ServerSocket(1234);
            while(!serverSocket.isClosed()) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                    System.out.println("A new client has been accepted");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ClientHandler newClientHandler = new ClientHandler(socket, pd);
                Thread thread = new Thread(newClientHandler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            stopServer();
            System.out.println("Players Written to file");
        }


    }
    public void stopServer() {
        isRunning = false;
        try {

            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            pd.savePlayersToFile();
            pd.savePasswordsToFile();
            System.out.println("Players saved to file. Server stopped.");
        } catch (IOException e) {
            System.out.println("Error closing server: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
