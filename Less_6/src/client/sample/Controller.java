package client.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  { //
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button btn;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
   // PrintWriter out;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    @Override
    public void initialize(URL location, ResourceBundle resource) {

        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
           // out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String str;
                   // String endStr;
                    try {
                        while (true) {
                            str = in.readUTF();

                            textArea.appendText(str + "\n");

                            //if((str = in.readUTF())==null) {
                            System.out.println("Client send" + str);
                            System.out.println("Socket close Controller 1" + socket.isClosed());
                            if(str.equalsIgnoreCase("/end3")) {
                                out.writeUTF("/end2");
                                out.writeUTF("/end");
                                socket.close();
                                System.out.println("Client break");

                                break;
                            }
                        }
                        //out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                   try {                          //
                       socket.close();           //
                       System.out.println("Socket close Controller 2" + socket.isClosed());
                   } catch (IOException e) {      //
                       e.printStackTrace();       //
                   }
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
