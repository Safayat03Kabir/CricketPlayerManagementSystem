package ClientRelatedPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkUtil {
    Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    //private String clientName;


    public NetworkUtil() {
        try {
            this.socket = new Socket("localHost", 1234);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            //this.clientName = "uninitializedClient";
            //this.listen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//    public Object listenFromServer(){
//        final Object[] o = {new Object()};
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(socket.isConnected()){
//
//                    try {
//                        o[0] = ois.readUnshared();
//                        break;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//
//
//                }
//            }
//        }).start();
//        return o[0];
//
//    }
    public Object listenFromServer() {
        try {
            return ois.readUnshared();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        return null;
        }
}


    public void writeToServer(Object o){
        try {
            oos.writeUnshared(o);
            oos.flush();
            //return listenFromServer(); // Wait for the response from the server
        } catch (IOException e) {
            System.out.println("Write method in Client.java");
            e.printStackTrace();
            //return null;
        }
    }
    public Object writeToClient(Object o){
        return o;
    }


/*
* what we are doing here is (1)Write to server
*                           (2)Write to the fxml homepage
* a separate thread listens the object returned from the client
* and writes to the fxml HomePage
* it can be invoked even if no object is "requested"
* */




}
