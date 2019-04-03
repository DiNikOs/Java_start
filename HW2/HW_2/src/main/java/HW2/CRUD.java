package HW2;

/**
 * Java. Leve3 3Lesson 2
 * CRUD — Create, Read, Update, Delete
 *
 * @author DMITRIY OSTROVSKIY
 * @version 0.2 dated APR 01, 2019
 */

import java.sql.*;
import java.util.Scanner;

class CRUD  { // implements IConstants

    String TABLE;
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String SQLITE_DB = "jdbc:sqlite:usersDB.db";
    final String SURNAME = "surname";
    final String NAME = "name";
    final String SALARY = "salary";

    Scanner sc = new Scanner(System.in);

    Connection connect;
    Statement stmt;
    ResultSet rs;
    String sql;

    public static void main(String[] args) throws SQLException {
        new CRUD();

    }

    public void setTABLE(String table) {
        this.TABLE = table;
    }

    public String getTABLE() {
        return  TABLE;
    }


    CRUD() throws SQLException {

        while (true) {
                // Create
            System.out.println("---------------------------------");
            System.out.println("Выбирите действие:\n c/ - create\n a/ - add\n r/ - read\n u/ - update\n d/ - delete\n end/ - exit ");
            System.out.print("> ");
            String line  = sc.nextLine();
            if ("c/".equals(line)) {
                System.out.println("Введите название таблицы");
                String table = sc.nextLine();
                setTABLE(table);
                openDBFile(SQLITE_DB).createTable(
                        "DROP TABLE IF EXISTS  " + TABLE + ";" + //   works TABLE
                                "CREATE TABLE  " + TABLE +//  works TABLE
                                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                " surname TEXT NOT NULL," +
                                " name TEXT NOT NULL," +
                                " salary int NOT NULL);");
                System.out.println("DONE");
                // ADD
            } else if ("a/".equals(line)) {
                System.out.println("Введите через пробел: Фамилия Имя Зарплата, ...(d - значение по умолчанию, end/ - exit)");
                String add = "";
                int i = 0;
                int j = 1;
                connect.setAutoCommit(false);
                do {
                    add = sc.nextLine();
                    if (add.startsWith("d")) {
                        add = "Иванов" + ("" + j) + " Иван " + (10000 + i);
                    }
                    if (add.startsWith("end/")) break;
                    String[] column = add.split(" ");
                    openDBFile(SQLITE_DB).add(column[0], column[1], Integer.parseInt(column[2]));
                    i += 1000;
                    j++;
                } while (true);
                connect.setAutoCommit(true);
                System.out.println("DONE");
                // READ
            } else if ("r/".equals(line)) {
                openDBFile(SQLITE_DB).list();
                // UPDATE
            } else if ("u/".equals(line)) {
                System.out.println("Введите через пробел: Фамилия Имя(работника в базе) Зарплата(для изменнения), ...(d - значение по умолчанию, end/ - exit)");
                String upd = "";
                int i = 0;
                int j = 1;
                connect.setAutoCommit(false);
                do {
                    upd = sc.nextLine();
                    if (upd.startsWith("d")) {
                        upd = "Иванов" + ("" + j) + " Иван " + (10000 + i);
                    }
                    if (upd.startsWith("end/")) break;
                    String[] column = upd.split(" ");
                    openDBFile(SQLITE_DB).update(column[0], column[1], Integer.parseInt(column[2]));
                    i += 2000;
                    j++;
                } while (true);
                connect.setAutoCommit(true);
                System.out.println("DONE");
                // DELETE
            } else if ("d/".equals(line)) {
                System.out.println("Введите ЗП запись о которой удалить");
                int salary = Integer.parseInt(sc.nextLine());
                openDBFile(SQLITE_DB).delete(salary);
                System.out.println("DONE");
            } else if (line.equalsIgnoreCase("end/")) {
                System.out.println("END");
                break;
            } else {
                System.out.println("Unknown command!");
            }
        }

    }

    private CRUD openDBFile(String dbName) { // open/create database
        try {
            Class.forName(DRIVER_NAME);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    private void createTable(String sqlCreateTable) { // create table
        try {
            stmt.executeUpdate(sqlCreateTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void add(String surname, String name, int salary) { // add record
        try {
            stmt.executeUpdate("INSERT INTO " + getTABLE() +
                    " (surname, name, salary) " +
                    "VALUES ('" + surname + "', '" + name + "', '" + salary + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(String surname, String name, int salary) { // update passwd by login
        try {
            stmt.executeUpdate("UPDATE " + getTABLE() +
                    " set SALARY='" + salary +
                    "' where SURNAME='" + surname +
                    "' and NAME='" + name + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delete(int salary) { // delete record by login
        try {
            stmt.executeUpdate("DELETE from " + getTABLE() +
                    " where SALARY='" + salary + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void list() { // show all records
        try {
            System.out.println("Фамилия\t\tИмя\t\t\tЗарплата");
            rs = stmt.executeQuery("SELECT * FROM " + getTABLE() + ";");
            while (rs.next())
                System.out.println(rs.getString(SURNAME) + "\t\t" + rs.getString(NAME) + "\t\t" + rs.getString(SALARY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
