package client.sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
//import java.lang.*;

public class Main extends Application {
    final String IP_ADRESS = "localhost";
    final int PORT = 8189;
    Socket socket;
   // DataInputStream in;
    DataOutputStream out;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("cmd 2K19 Chat");
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {

      //
      // Socket socket = new Socket(IP_ADRESS, PORT);
      // DataInputStream in = new DataInputStream(socket.getInputStream());
      // DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        launch(args);
    }

    @Override
    public void stop() { //
        // //Здесь Вы можете прописать все действия при закрытии Вашего приложения.
            try {
                socket = new Socket(IP_ADRESS, PORT);
                out = new DataOutputStream(socket.getOutputStream()); //
//
                out.writeUTF("/end");
                out.writeUTF("/end2");
                out.writeUTF("/end3");
           //     out.flush();
                socket.close();
               System.out.println("Window close" );
                System.out.println("Socket close ClientMain " + socket.isClosed());
           //     //out.writeUTF("Window close" );
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }


    @Override
    public void init(){
        //Инициализация любых данных, до включения основного потока Start в работу.
        //К теме не относится, но тоже полезно!
    }
}
