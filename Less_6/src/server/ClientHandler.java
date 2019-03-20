package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private MainServer server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public ClientHandler (MainServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        String str;
                        while (true) {
                            str = in.readUTF();


                            if(str.equalsIgnoreCase("/end2")) {
                               // out.writeUTF("/end");
                                System.out.println("break");

                                break;
                           }

                            server.broadcastMsg(str);
                       }
                      // out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                            System.out.println("in close");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                            System.out.println("out close");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {

                            socket.close();
                            System.out.println("Socket close ClientHand " + socket.isClosed());
                        } catch (IOException e) {
                            e.printStackTrace();


                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
