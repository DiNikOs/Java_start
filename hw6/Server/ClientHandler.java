package hw6.Server;
/**
 * Java2. Core. HW6
 * Сетевой чат. КлиентСервер
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 22, 2019
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    final String DISCONNECTED = " disconnected";
    final String EXIT_COMMAND = "/end"; // command for exit
    final String SUMBOL = "> ";

    private Server server;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private Scanner scanner;
    Object object = new Object();


    public ClientHandler(Server server, Socket socket) { // Server server
        synchronized (object) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream());
            scanner = new Scanner(System.in);


            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str;
                    System.out.println("Client connected");
                    try {
                        do {
                            System.out.print(SUMBOL);
                            str = in.readLine();
                            if (str.equalsIgnoreCase(EXIT_COMMAND + "C")) {
                                out.println(EXIT_COMMAND + "S");
                                break;
                            }
                            if (str.equalsIgnoreCase(EXIT_COMMAND + "B")) {
                                //out.println(EXIT_COMMAND + "S");
                               // break;
                            }
                            System.out.println("Client send: " + str);
                            out.flush();
                        } while (true);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        try {
                            socket.close();
                            System.out.println("Socket close " + socket.isClosed());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.removeClients (ClientHandler.this);
                        System.out.println("Client1 " + DISCONNECTED);
                    }

                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    String msg;
                    try {

                        do {
                            System.out.print(SUMBOL);
                            msg = scanner.nextLine();
                            if (msg.equalsIgnoreCase(EXIT_COMMAND + "B")) {
                                 out.println(EXIT_COMMAND + "Ser");
                                break;
                            }
                            out.println(msg); //
                            out.flush();
                        } while (true);


                    } finally {
                        try {
                        in.close();           //
                        System.out.println("in close2 ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        out.close();
                        System.out.println("out close2 ");
                        try {
                            //ServerSocket socket;
                            socket.close();
                            System.out.println("Socket close2 " + socket.isClosed());
                        } catch (IOException e) {      //
                            e.printStackTrace();       //
                        }
                        server.removeClients (ClientHandler.this);
                        System.out.println("Server1" + DISCONNECTED);
                    }

                }
            });

            thread1.start();
            thread2.start();

            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Socket close1 " + socket.isClosed());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
    }
}