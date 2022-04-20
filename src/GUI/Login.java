package GUI;

import DataBase.ReadUser;
import Exceptions.DataBaseExceptions.UsernameNotExistException;
import Exceptions.DataBaseExceptions.WrongPasswordException;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Login extends JFrame {
    public Login() {
        JLabel usernameLBL = new JLabel("username");
        JTextField usernameTXT = new JTextField();
        usernameTXT.setPreferredSize(new Dimension(200, 25));
        JPanel usernamePNL = new JPanel();
        usernamePNL.add(usernameLBL);
        usernamePNL.add(usernameTXT);
        usernamePNL.setLayout(new FlowLayout());
        usernamePNL.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 20));

        JLabel passwordLBL = new JLabel("password");
        JPasswordField passwordTXT = new JPasswordField();
        passwordTXT.setPreferredSize(new Dimension(200, 25));
        JPanel passwordPNL = new JPanel();
        passwordPNL.setLayout(new FlowLayout());
        passwordPNL.add(passwordLBL);
        passwordPNL.add(passwordTXT);
        passwordPNL.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 20));

        JLabel loginErrorLBL = new JLabel();
        JButton loginBTN = new JButton("Login");
        JPanel loginPNL = new JPanel();
        loginPNL.add(loginErrorLBL);
        loginPNL.add(loginBTN);
        loginPNL.setLayout(new GridLayout(2, 1));
        loginPNL.setBorder(BorderFactory.createEmptyBorder(5, 20 ,5, 20));

        loginBTN.addActionListener(e -> {
            User user = null;
            try{
                user = ReadUser.readUser(usernameTXT.getText(), passwordTXT.getText());
                Main.Main.getInstance().login(user);
            } catch (UsernameNotExistException ex) {
                loginErrorLBL.setText("username does not exist");
                usernameTXT.setText("");
                passwordTXT.setText("");
            } catch (WrongPasswordException ex) {
                loginErrorLBL.setText("password is not correct");
                passwordTXT.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "an error occurred! please try later",
                        "Something Wrong",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(usernamePNL);
        this.add(passwordPNL);
        this.add(loginPNL);
        this.setLayout(new GridLayout(3, 1));
        this.pack();
        this.setVisible(true);
    }
}
