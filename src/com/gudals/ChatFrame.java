package com.gudals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatFrame extends JFrame {
    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JTextArea ja = new JTextArea();
    private JTextField jf = new JTextField("");
    private JScrollPane sp = new JScrollPane(ja);
    String outmsg = null;

    public ChatFrame(){}// ChatFrame

    public void viewon(){
        ja.append("System : 이름을 입력하시오\n");
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outmsg = jf.getText();
                jf.setText("");
            }
        };
        jf.addActionListener(action);

        ja.setEnabled(false);
        ja.setFont(new Font("textarea", Font.BOLD, 30));
        ja.setDisabledTextColor(Color.BLACK);
        ja.setLineWrap(true);
        ja.setCaretPosition(ja.getText().length());
        ja.setBackground(Color.gray);
        jf.setSize(500, 150);
        jf.setFont(new Font("textfield", Font.BOLD, 30));

        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jp1.setLayout(new BorderLayout());
        jp1.setPreferredSize(new Dimension(500,600));
        jp2.setLayout(new BorderLayout());
        jp2.setPreferredSize(new Dimension(500, 150));

        jp1.add(sp);
        jp2.add(jf);

        add(jp1, BorderLayout.NORTH);
        add(jp2, BorderLayout.SOUTH);

        setTitle("멀티채팅 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }// viewon

    public void addmsg(String msg){
        ja.append(msg+"\n");
    }// addmsg
}// class
