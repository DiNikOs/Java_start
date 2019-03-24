package hw6.Client;
 /**
 * Java2. Core. HW6
 * Сетевой чат. Клиент
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 22, 2019
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

class Client {

    final String SERVER_ADDR = "localhost"; //
    final int SERVER_PORT = 8777;
    final String SUMBOL = "> ";
    final String CONNECT_SERVER = "Connection to server.";
    final String CONNECT_CLOSED = "Connection closed.";
    final String EXIT_COMMAND = "/end"; // command for exit

    private Scanner scanner;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) {
        new Client();
    }

    Client() {
        Object object = new Object();
        synchronized (object) {
            try {
                socket = new Socket(SERVER_ADDR, SERVER_PORT); //  Socket
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream()); // PrintWriter
                scanner = new Scanner(System.in);

                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String str;
                        try { //()
                            System.out.println(CONNECT_SERVER);
                            do {
                               System.out.print(SUMBOL);
                                str = in.readLine();

                                  if (str.equalsIgnoreCase(EXIT_COMMAND + "S")) {
                                      out.println(EXIT_COMMAND + "B");
                                      break;
                                  }
                                System.out.println("Server send: " + str);
                                out.flush();
                            } while (true); //
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                in.close();
                                System.out.println("in close1 ");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            out.close();
                            System.out.println("out close1 ");
                            try {
                                //ServerSocket socket;
                                socket.close();
                                System.out.println("Socket close1 " + socket.isClosed());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Thread 1 close");
                            closeApp();
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
                                if (msg.equalsIgnoreCase(EXIT_COMMAND)) {
                                     out.println(EXIT_COMMAND + "C");
                                    // break;//*
                                }
                                if (msg.equalsIgnoreCase("closeScanner")) {
                                    //break;
                                }
                                out.println(msg);
                                out.flush();
                            } while (true); //
                        } finally {
                            try {
                                System.out.println("Thread close2 ");
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                thread1.start();
                thread2.start();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            }
        }
        public void closeApp () {
            try {
                socket.close();
                System.out.println("Close programm");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

}