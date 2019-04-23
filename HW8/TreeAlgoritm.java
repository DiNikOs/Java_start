package HW8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TreeAlgoritm {

    private int n; //количество вершин в орграфе
    private int m; //количествое дуг в орграфе
    private ArrayList adj[]; //список смежности
    private boolean used[]; //массив для хранения информации о пройденных и не пройденных вершинах

    private BufferedReader cin;
    private PrintWriter cout;
    private StringTokenizer tokenizer;

    //процедура обхода в глубину
    private void dfs(int v) {
//если вершина является пройденной, то не производим из нее вызов процедуры
        if (used[v]) {
            return;
        }
        used[v] = true; //помечаем вершину как пройденную
        cout.print((v + 1) + " ");
//запускаем обход из всех вершин, смежных с вершиной v
        for (int i = 0; i < adj[v].size(); ++i) {
            int w = (int)adj[v].get(i);
            dfs(w); //вызов обхода в глубину от вершины w, смежной с вершиной v //
            }
        } //процедура считывания входных данных с консоли


    private void readData() throws IOException {
        cin = new BufferedReader(new InputStreamReader(System.in));
        cout = new PrintWriter(System.out);
        tokenizer = new StringTokenizer(cin.readLine());
        n = Integer.parseInt(tokenizer.nextToken()); //считываем количество вершин графа
        m = Integer.parseInt(tokenizer.nextToken()); //считываем количество ребер графа //инициализируем список смежности графа размерности n
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList();
        }
        //считываем граф, заданный списком ребер
        for (int i = 0; i < m; ++i) {
            tokenizer = new StringTokenizer(cin.readLine());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            v--;
            w--;
            adj[v].add(w);
            adj[w].add(v); }
        used = new boolean[n];
        Arrays.fill(used, false);
//        if (cin.equals(1000)) {
//            System.out.println(Arrays.toString(adj));
//        }
    }
    private void run() throws IOException {
        readData();
        for (int v = 0; v < n; ++v) {
            dfs(v);
        }
        cin.close();
        cout.close();
    }
    public static void main(String[] args) throws IOException {
        TreeAlgoritm treeAlgoritm = new TreeAlgoritm();
        treeAlgoritm.run();
    }

}
