package Lesson_7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;

public class MainServer {
    private Vector<ClientHandler> clients;
    public HashMap<String, Integer> hs; //= new HashSet<>();
    public Map<Integer, String> hw = new HashMap<>();
    public String send;

    public MainServer() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();
        hs = new HashMap<>();

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this,socket, this,hs); //
                //new ClientHandler(hs);
               // System.out.println(clients.toString());
             //   System.out.println("server client= " + clients.firstElement());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public HashMap<String, Integer> getHs() {
        return hs;
    }

    public void setHs(HashMap<String, Integer> hs) {
        this.hs = hs;
    }

    public void removeNickName (String nick, Integer count) {
        hs.remove(nick, count);
    }


    public void addNickName (String nick, Integer count) {
//        Integer countHs = 0;
//        hw.put(count, nick );
//        for (Map.Entry<Integer, String> entry : hw.entrySet()) {
//            if (nick.equals(entry.getValue())) {
//                hs.put(nick, (entry.getKey() + 1));
//            } hs.put(nick, count);
//        }

       // for (Map.Entry<String, Integer> entry : hs.entrySet()) {
       //     if (count.equals(entry.getValue())) {
       //         hs.put(nick, (entry.getValue() + 1));
       //     }
       // }
        hs.put(nick, count);

    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

//    public void castMsg(String nick, String msg) {
//        for (ClientHandler o : clients) {
//            o.sendMsg(msg);
//        }
//    }



    public void castMsg(String nick, String msg) {
        Set<Map.Entry<String, Integer>> set = hs.entrySet();
        Integer indexNick =0;
        for (Map.Entry<String, Integer> me : set) {

            if(me.getKey().equals(nick)) {
                indexNick = me.getValue();
            }
        }



        System.out.println(nick + ", " + msg );
        System.out.println("index nick = " + indexNick );
        for (ClientHandler o : clients) {
            System.out.println(clients.get(1));
            //  System.out.println(o.toString());
            if (o.equals(indexNick-1)) {
                o.sendMsg(nick + msg);
                break;//   System.out.println(o.toString());
            }
        }
    }
//
//    public void castMsg(String nick, String msg) {
//        System.out.println(nick + ", " + msg );
//        System.out.println(clients);
//

    // if (clients.equals(nick)) {
    //    System.out.println("OK");
    //} else System.out.println("NOK");
//  //      for (ClientHandler o : clients) {
//          //  System.out.println(o.toString());
//            if (o.equals(nick)) {
//                 o.sendMsg(msg);
//
//
//             //   System.out.println(o.toString());
//            }
//        }
 //   }




    public Integer contains(String nick) {
        return hs.get(nick); // return null if no nick
   }




    //public getKeyValue

}
