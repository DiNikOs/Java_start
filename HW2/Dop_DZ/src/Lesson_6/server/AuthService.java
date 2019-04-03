package Lesson_6.server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;
    private String  stopBlacklist;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:userDB8.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) throws SQLException {
        String sql = String.format("SELECT nickname FROM main where " +
                "login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static String getBlacklist(String nick) throws SQLException {
        String sql = String.format("SELECT blackName FROM main where " +
                "nickname = '%s'", nick);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static StringBuilder getHistory() throws SQLException {
        String sql = "SELECT * FROM sendMsg";

        StringBuilder send = new StringBuilder("");
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            send.append(rs.getString("fromNick") + ": " + rs.getString("msg") + "\n");
        }
        return send;
    }


    public static void setHistory(String nick, String msg) throws SQLException {

        String sql = ("INSERT INTO sendMsg " +
                        " (fromNick, msg) " +
                "VALUES ('" + nick + "', '" + msg + "');");
        try {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Выгрузка в БД history успешно");
    }

    public static void updateBlacklist(String nick, String stopBlacklist) throws SQLException {

        String sql = String.format("UPDATE main set blackName='" + stopBlacklist +
                "' where nickname = '%s'", nick);
        try {
        stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
         System.out.println("Выгрузка в БД blacklist успешно");
    }

//    public String  getStopBlacklist() {
//        return  stopBlacklist;
//    }
//
//    public void setStopBlacklist(String stopBlacklist) {
//        this.stopBlacklistT = stopBlacklist;
//    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
