package GUI;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import Main.Main;

public class FirstPage extends JFrame {
    public FirstPage() {
        JButton signupBTN = new JButton("Sign up");
        signupBTN.setPreferredSize(new Dimension(100, 50));
        JPanel signupPNL = new JPanel();
        signupPNL.setLayout(new BorderLayout());
        signupPNL.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        signupPNL.add(signupBTN, BorderLayout.CENTER);
        signupBTN.addActionListener(e -> {
            this.dispose();
            Main.GenerateSignUpGUI();
        });

        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel north = new JPanel();

        JLabel loginLBL = new JLabel("have an account");
        JButton loginBTN = new JButton("Login");
        JPanel loginPNL = new JPanel();
        loginPNL.add(loginLBL);
        loginPNL.add(loginBTN);
        loginPNL.setPreferredSize(new Dimension(100, 100));

        loginBTN.addActionListener(e -> {
            this.dispose();
            Main.GenerateLoginGUI();
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(signupPNL, BorderLayout.CENTER);
        this.add(east, BorderLayout.EAST);
        this.add(west, BorderLayout.WEST);
        this.add(north, BorderLayout.NORTH);
        this.add(loginPNL, BorderLayout.SOUTH);
        this.dispose();
        this.pack();
        this.setVisible(true);
    }
}
