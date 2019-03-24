package Lesson_7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;

public class ClientHandler {

    private MainServer server;
    private Socket socket;
    private MainServer hs;
    private MainServer sendToNick;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private Integer count = 0;
   // private String hs;
   // ArrayList<String> list;

    public ClientHandler(MainServer server, Socket socket, MainServer hs, HashMap<String, Integer> strings) { //HashSet<ClientHandler> clientHandlers
        try {
            this.server = server;
            this.socket = socket;
            this.sendToNick = sendToNick;
            this.hs = hs;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
          // list.add("Start");


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        while (true) {
                            String str = in.readUTF();

                            if(str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                System.out.println(hs.toString());



                               // String tryName = ;
                               // System.out.println(hs.toString());
                              //  System.out.println(hs.toString());
                               // hs.getHs();
                                if (hs.contains(newNick)==null) {
                                   // System.out.println(hs.getKey());
                                  System.out.println("Add nik");
                               //    // System.out.println(hs.toString());
                                }
                                else newNick = null;
                              //  String enter = AuthService.getEnterByLoginAndPass(tokens[1], tokens[2]);
                               // System.out.println("enter = " + enter);
                                if(newNick != null) {
                                    sendMsg("/authok");
                                    count++;
                                    nick = newNick;
                                    server.subscribe(ClientHandler.this);
                                    hs.addNickName(nick, count);
                                    System.out.println("s LocalPort= " + socket.getLocalPort());
                                    System.out.println("s LocalSocketAddress= " + socket.getLocalSocketAddress());
                                    System.out.println("s Port= " + socket.getPort());

                                    System.out.println("*******************");
                                    break;
                                } else {
                                    sendMsg("Неверный логин/пароль");
                                }
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/w")) {
                                String[] tokens = str.split(" ");
                                try {
                                    if (tokens[1] !=null ) {
                                        System.out.println(tokens[1] + " " + str.substring(tokens[0].length()+tokens[1].length()+1));
                                        server.castMsg(tokens[1], ("" + str.substring(tokens[0].length()+tokens[1].length()+1)));
                                }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("not Nick");
                                }

                            }

                            if (str.equals("/end")) {
                                count--;
                                hs.removeNickName(nick, count);
                                out.writeUTF("/serverclosed");
                                server.broadcastMsg(nick + " DISCONNECT" );
                                break;
                            }
                            server.broadcastMsg(nick + " : " + str);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
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

