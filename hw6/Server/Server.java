package hw6.Server;
/**
 * Java2. Core. HW6
 * Сетевой чат. Сервер
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 22, 2019
 */
import java.io.*;
import java.net.*;
import java.util.Vector;


class Server {

    final int SERVER_PORT = 8777;
    final String SERVER_START = "Server is started...";
    final String SERVER_STOP = "Server stopped.";
    final String CLIENT_JOINED = " Client connected";

    private Vector<ClientHandler> clients;
    private BufferedReader in;
    private PrintWriter out;


    public static void main(String[] args) {
        new  Server();
    }

    Server() {

        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {

            server = new ServerSocket(SERVER_PORT);
            System.out.println(SERVER_START);
            String str;
          //  String msg;

            while (true) {
                socket = server.accept();
                addClients(new ClientHandler(this,socket));
                System.out.println("#" + CLIENT_JOINED + clients.size());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                out.println("Connected to server");
                try {
                    str = in.readLine();

                    if (str.equalsIgnoreCase("/endSer")) {
                       // out.println("/end");
                        System.out.println(" Server: " + clients.size());
                        break;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (!clients.isEmpty()) clients.removeElementAt(clients.size() - 1);

                socket.close();
                System.out.println("Socket close Main " + socket.isClosed());
                System.out.println("Socket close Main " + clients.size());
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                server.close();

                System.out.println(SERVER_STOP + server.isClosed());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void addClients (ClientHandler client) {
        clients.add(client);
    }

    public  void removeClients (ClientHandler client) {
        clients.remove(client);
    }

}