package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServer {
    private Vector<ClientHandler> clients;
    private DataInputStream in;
    private DataOutputStream out;

   // private DataInputStream in;

    public MainServer() {
        ServerSocket server = null;
        Socket socket = null;



        clients = new Vector<>();

        try {

            server = new ServerSocket(8189);
            String str;

            System.out.println("Server start!");

            while (true) {

                socket = server.accept();

                try {
                    out = new DataOutputStream(socket.getOutputStream());
                    in = new DataInputStream(socket.getInputStream());
                    str = in.readUTF();
                    if(str.equalsIgnoreCase("/end")) {
                        out.writeUTF("/end2");
                        System.out.println(" MainServer " +  clients.size() );
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                clients.add(new ClientHandler(this,socket));

                System.out.println("Client connected " +  clients.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

           try {
               if (!clients.isEmpty()) clients.removeElementAt(clients.size()-1);

               socket.close();
               System.out.println("Socket close Main " + socket.isClosed());
               System.out.println("Socket close Main " + clients.size());
           } catch (IOException e) {
               e.printStackTrace();
           }


            try {
                server.close();
                System.out.println("Server close Main " + server.isClosed());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void broadcastMsg (String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

}
