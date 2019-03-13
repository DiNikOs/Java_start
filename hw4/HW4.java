/**
 * Java2. Core. HW4
 * Передача написанного текста из TextField по нахжатию Enter или по щелчку мыши
 * @author Dmitriy Ostrovskiy
 * @version 0.1 date Mar 13, 2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HW4 {
    public static void main(String[] args) {

        new MyWindow();

    }
}

class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("Java Swing");
        setBounds(800,300,400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        bottomPanel.setPreferredSize(new Dimension(1,40));
        
        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        JButton jbtn = new JButton("Send");
        JTextArea jta = new JTextArea();
        JScrollPane jsp = new JScrollPane(jta);
        JTextField jtf = new JTextField();
        
        centerPanel.add(jsp, BorderLayout.CENTER);
        bottomPanel.add(jtf);
        bottomPanel.add(jbtn);

        jtf.setPreferredSize(new Dimension(300,28));
        jta.setEditable(false);
        jtf.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append(jtf.getText() + "\n");
                jtf.setText("");
                jtf.grabFocus();
            }
        });
        
        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.append(jtf.getText() + "\n");
                jtf.setText("");
                jtf.grabFocus();
            }
        });
        setVisible(true);
    }
}