package HW8;

import java.io.*;
import java.util.*;

public class Human {

    int id;
    int entutyId;   //количествое дуг в орграфе
    int parentId;   //количество вершин в орграфе
    static StringBuilder sb;
    private static List<String> chars = new ArrayList<String>();
    private static List<Integer> numb = new ArrayList<Integer>();
    static List<Human> children = new ArrayList<>();
    static List<Human> parents = new ArrayList<>();

    public Human(int id, int entutyId, int parentId) { // , String name
        this.id = id;
        this.entutyId = entutyId;
        this.parentId = parentId;
    }

    public static void readFileBuf () {
        try(FileReader reader = new FileReader("123/inData.txt"))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){
                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("**************");
    }

    public static void readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("123/inData.txt")));

            String c;
            sb = new StringBuilder();
            int g;
            while ((c = reader.readLine()) != null) {
                chars.add(c);
                sb.append(" "+ c);

            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = sb.toString();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbout = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            if (Character.isDigit(str.charAt(i))) {
                while (Character.isDigit(str.charAt(i))) {
                    sb.append(str.charAt(i));
                    i++;
                    if (i == str.length())
                        break;                }
                int k = Integer.parseInt(sb.toString());
                numb.add(k);
                sb.delete(0, sb.length());
            } else
                i++;
        }

        int a,b,d;
        a = b = d =0;
        for (int j = 0; j < numb.size(); j++) {

            if (j%3==0) {
                //System.out.println("j1= " + numb.get(j));
                a = numb.get(j);
            }
            if ((j-1)%3==0) {
                //System.out.println("j2= " + numb.get(j) );
                b = numb.get(j);
            }
            if ((j+1)%3==0) {
                //System.out.println("j3= " + numb.get(j) + "\n");
                d = numb.get(j);
                parents.add(new Human(a, b, d));
            }
        }
        //System.out.println("numb = " + numb.toString());
    }

    public static void main(String[] args) {
        readFileBuf();
        readFile();
        System.out.println("parants= " + parents);
        Tree tree = new Tree();
        for (Human hm: parents) {
            //System.out.println(hm);
            tree.insert(hm.id, hm.entutyId, hm.parentId);
        }

        Node find = tree.find(0);
        find.printNode();
        tree.print(find);
        System.out.println("children= " + Human.children);
    }
    @Override
    public String toString() {
        return  id + "," + entutyId + "," + parentId;
    }
}

class Node {

    public Integer key;         //ключ узла
    public Integer id;
    public Integer data;        //некоторые данные в узле
    public Node leftChild;      //левый потомок
    public Node rightChild;     //правый потомок
    int i = 0;
    /**
     * Метод выводит на экран содержимое узла
     */
    public void printNode(){
        if (i == 0) {
            System.out.println("KEY " + key + " DATA: " + data);
            Human.children.add(new Human(id, data, key));
        }
        i++;
    }
}

class Tree {

    Node root;
    /**
     * Поиск элемента в дереве по ключу
     */
    public Node find(int key){
        Node current = root;
        while(current.key!=key){
            if(current.key<key){
                current = current.leftChild;
            }else{
                current = current.rightChild;
            }
            if(current==null){
                return null;
            }
        }
        return current;
    }

    /**
     * Вставка в дерево.
     */
    public void insert(Integer id, Integer data, Integer key){
        Node node = new Node();
        node.id = id;
        node.key = key;
        node.data = data;

        if(root==null){
            root = node;
        }else{
            Node current = root;
            Node prev = null;
            while (true){
                prev = current;
                if(key<prev.data){
                    current = current.leftChild;
                    if(current==null) {
                        prev.leftChild = node;
                        return;
                    }
                }else{
                    current = current.rightChild;
                    if(current==null) {
                        prev.rightChild = node;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Вывод всех элементов дерева методом асиметричного обхода
     */
    public void print(Node startNode){
        if(startNode != null){              //условие сработает, когда мы достигним конца дерева и потомков не останется
            print(startNode.leftChild);     //рекурсивно вызываем левых потомков
            startNode.printNode();          //вызов метода принт
            print(startNode.rightChild);    //вызов правых
        }
    }
}