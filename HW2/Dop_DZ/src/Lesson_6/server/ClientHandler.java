package Lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.currentTimeMillis;

public class ClientHandler {

    private MainServer server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private List<String> blacklist;
    long t1;
    long t2;
    private boolean tr;
   // private static String name;

    public String getNick() {
        return nick;
    }

    private String nick;

    public ClientHandler(MainServer server, Socket socket) {
        Object object = new Object();
        synchronized (object) {
            try {
                this.blacklist = new ArrayList<>();
                this.server = server;
                this.socket = socket;
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                          //  t1 = currentTimeMillis();
                            while (true) {
                                String str = in.readUTF();
                                if (str.equals("/end2")) {
                                    out.writeUTF("/serverclosed");
                                    break;
                                }

                                if (str.startsWith("/auth")) {
                                    String[] tokens = str.split(" ");
                                    System.out.println(str + "tokens=" + tokens.length);
                                    String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);

                                    if (newNick != null) {
                                        if (!server.isNickBusy(newNick)) {
                                            sendMsg("/authok");
                                            nick = newNick;
                                           // setName(nick);
                                            server.subscribe(ClientHandler.this);
                                            System.out.println("Клиент авторизовался");
                                            String blackName = AuthService.getBlacklist(nick);
                                            setAuth(true);
                                            System.out.println(blackName);
                                            if (blackName != null) {
                                                String[] tokenBlackName = blackName.split(" ");
                                                for (int i = 0; i < tokenBlackName.length; i++) {
                                                    if (!blacklist.contains(tokenBlackName[i]))
                                                        blacklist.add(tokenBlackName[i]);
                                                }
                                                System.out.println("Array blacklist =" + blacklist.toString());
                                            }
                                            StringBuilder history = AuthService.getHistory();
                                            sendMsg(history.toString());
                                         //   sendMsg("Test");
                                            break;
                                        } else {
                                            sendMsg("Учетная запись уже используется!");
                                        }
                                    } else {
                                        sendMsg("Неверный логин/пароль");
                                    }
                                }
                            }

                            while (true) {

                                String str = in.readUTF();

                                if (str.startsWith("/")) {
                                    if (str.equals("/end")) {
                                        out.writeUTF("/serverclosed");
                                        break;
                                    }
                                    if (str.startsWith("/w ")) {
                                        String[] tokens = str.split(" ", 3);
                                        System.out.println(ClientHandler.this + " " + blacklist.contains(nick) + tokens[1]);
                                        if (blacklist.contains(nick)) {
                                            System.out.println("Вы не можете отправить сообщение пользователю с ником " + tokens[1]);
                                        } else {
                                            server.sendPersonalMsg(ClientHandler.this, tokens[1], tokens[2]);
                                        }
                                    }
                                    if (str.startsWith("/blacklist ")) {
                                        String[] tokens = str.split(" ");
                                        if (!blacklist.contains(tokens[1])) {
                                            blacklist.add(tokens[1]);
                                            sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                        } else sendMsg("Пользователь " + tokens[1] + " уже в черном списке!");
                                    }
                                    if (str.startsWith("/blacklistOf ")) {
                                        String[] tokens = str.split(" ");
                                        blacklist.remove(tokens[1]);
                                        sendMsg("Вы удалили пользователя " + tokens[1] + " из черного список");
                                    }

                                } else {
                                    if (blacklist.contains(nick)) {
                                        System.out.println("Вы не можете отправить сообщение пользователю с ником " + nick);
                                    } else
                                      //  AuthService.setHistory(nick, str);
                                        server.broadcastMsg(ClientHandler.this, nick + " : " + str);
                                }
                            }

                            while (true) {


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
                                System.out.println("socket close");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                String black = "";

                                if (blacklist.size() > 0) {
                                    for (int i = 0; i < blacklist.size(); i++) {
                                        black = black + blacklist.get(i) + " ";
                                    }
                                }
                                AuthService.updateBlacklist(nick, black);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            System.out.println("server unsubscribe");
                            server.unsubscribe(ClientHandler.this);

                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        t1 = currentTimeMillis();
                        while (!getAuth()) {
                            t2 = currentTimeMillis();
                            if (t2 - t1 > 10000) {
                                try {
                                    out.writeUTF("/closeAuth");
                                    // server.unsubscribe(ClientHandler.this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    System.out.println("Close socket client ");
                                    break;
                                }
                            }
                        }
                    }
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static String getName () {
//        return name;
//    }
//
//    public static void setName (String nick) {
//        name = nick;
//    }

    public boolean checkBlackList(String nick) {
        return blacklist.contains(nick);
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuth(boolean tr) {
        this.tr = tr;
    }

    public boolean getAuth() {
        return tr;
    }


}
