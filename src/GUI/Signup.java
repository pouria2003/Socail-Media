package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Signup extends JFrame{

    String username = null;
    String password = null;
    String firstname = null;
    String lastname = null;

    boolean isUsernameValid = false;
    boolean isPasswordValid = false;
    boolean isFirstnameValid = true;
    boolean isLastnameValid = true;



    public Signup() {
        JLabel descriptionLBL = new JLabel("fields with * sign are necessary");
        JPanel descriptionPNL = new JPanel(new FlowLayout(FlowLayout.LEADING));
        descriptionPNL.add(descriptionLBL);

        JButton signupBTN = new JButton("sign up");
        signupBTN.setBorder(BorderFactory.createEtchedBorder());
        signupBTN.setPreferredSize(new Dimension(100, 30));
        signupBTN.setEnabled(false);
        JPanel signupPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signupPNL.add(signupBTN);
        signupBTN.addActionListener(e -> {

        });

        JLabel usernameLBL = new JLabel("*username:");
        usernameLBL.setPreferredSize(new Dimension(60, 35));
        JTextField usernameTXT = new JTextField();
        usernameTXT.setPreferredSize(new Dimension(200, 35));
        JButton usernameValBTN = new JButton("validate");
        usernameValBTN.setBorder(BorderFactory.createEtchedBorder());
        usernameValBTN.setPreferredSize(new Dimension(50, 35));
        JLabel usernameValLBL = new JLabel();
        usernameValLBL.setPreferredSize(new Dimension(400, 20));
        JPanel usernamePNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        usernamePNL.setPreferredSize(new Dimension(420, 100));
        usernamePNL.add(usernameLBL);
        usernamePNL.add(usernameTXT);
        usernamePNL.add(usernameValBTN);
        usernamePNL.add(usernameValLBL);
        usernameValBTN.addActionListener(e -> {
            String un = usernameTXT.getText();
            try {
                User.User.UsernameValidation(un);
                if(DataBase.Signup.isUsernameExist(un)){
                    usernameValLBL.setText("this username is already taken");
                }
                else {
                    usernameValLBL.setText("username is valid");
                    username = un;
                    isUsernameValid = true;
                }
                signupBTN.setEnabled(isUsernameValid && isPasswordValid &&
                        isFirstnameValid && isLastnameValid);

            } catch (IllegalStateException ex) {
                usernameValLBL.setText(ex.getMessage());
                isUsernameValid = false;
            } catch (SQLException ex) {
                ex.printStackTrace();
                usernameValLBL.setText("a problem occurred in connecting to database please try later");
                isUsernameValid = false;
            }
        });

        JLabel passwordLBL = new JLabel("*password:");
        passwordLBL.setPreferredSize(new Dimension(60, 35));
        JPasswordField passwordTXT = new JPasswordField();
        passwordTXT.setPreferredSize(new Dimension(200, 35));
        JButton passwordValBTN = new JButton("validate");
        passwordValBTN.setBorder(BorderFactory.createEtchedBorder());
        passwordValBTN.setPreferredSize(new Dimension(50, 35));
        JLabel passwordValLBL = new JLabel();
        passwordValLBL.setPreferredSize(new Dimension(400, 20));
        JPanel passwordPNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        passwordPNL.setPreferredSize(new Dimension(420, 100));
        passwordPNL.add(passwordLBL);
        passwordPNL.add(passwordTXT);
        passwordPNL.add(passwordValBTN);
        passwordPNL.add(passwordValLBL);
        passwordValBTN.addActionListener(e -> {
            String pass = passwordTXT.getText();
            try {
                User.User.PasswordValidation(pass);
                passwordValLBL.setText("password is valid");
                password = pass;
                isPasswordValid = true;
                signupBTN.setEnabled(isUsernameValid && isPasswordValid &&
                        isFirstnameValid && isLastnameValid);
            } catch (IllegalStateException ex) {
                passwordValLBL.setText(ex.getMessage());
                isPasswordValid = false;
            }
        });

        JLabel firstnameLBL = new JLabel("firstname:");
        firstnameLBL.setPreferredSize(new Dimension(60, 35));
        JTextField firstnameTXT = new JTextField();
        firstnameTXT.setPreferredSize(new Dimension(200, 35));
        JButton firstnameValBTN = new JButton("validate");
        firstnameValBTN.setBorder(BorderFactory.createEtchedBorder());
        firstnameValBTN.setPreferredSize(new Dimension(50, 35));
        JLabel firstnameValLBL = new JLabel();
        firstnameValLBL.setPreferredSize(new Dimension(400, 20));
        JPanel firstnamePNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        firstnamePNL.setPreferredSize(new Dimension(420, 100));
        firstnamePNL.add(firstnameLBL);
        firstnamePNL.add(firstnameTXT);
        firstnamePNL.add(firstnameValBTN);
        firstnamePNL.add(firstnameValLBL);
        firstnameValBTN.addActionListener(e -> {
            String fn = firstnameTXT.getText();
            if(fn.equals(""))
                fn = null;
            try {
                Person.Person.NameValidation(fn);
                firstnameValLBL.setText("firstname is valid");
                firstname = fn;
                isFirstnameValid = true;
                signupBTN.setEnabled(isUsernameValid && isPasswordValid &&
                        isFirstnameValid && isLastnameValid);
            } catch (IllegalStateException ex) {
                firstnameValLBL.setText(ex.getMessage());
                isFirstnameValid = false;
            }

        });

        JLabel lastnameLBL = new JLabel("lastname:");
        lastnameLBL.setPreferredSize(new Dimension(60, 35));
        JTextField lastnameTXT = new JTextField();
        lastnameTXT.setPreferredSize(new Dimension(200, 35));
        JButton lastnameValBTN = new JButton("validate");
        lastnameValBTN.setBorder(BorderFactory.createEtchedBorder());
        lastnameValBTN.setPreferredSize(new Dimension(50, 35));
        JLabel lastnameValLBL = new JLabel();
        lastnameValLBL.setPreferredSize(new Dimension(400, 20));
        JPanel lastnamePNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        lastnamePNL.setPreferredSize(new Dimension(420, 100));
        lastnamePNL.add(lastnameLBL);
        lastnamePNL.add(lastnameTXT);
        lastnamePNL.add(lastnameValBTN);
        lastnamePNL.add(lastnameValLBL);
        lastnameValBTN.addActionListener(e -> {
            String ln = lastnameTXT.getText();
            if(ln.equals(""))
                ln = null;
            try {
                Person.Person.NameValidation(ln);
                lastnameValLBL.setText("lastname is valid");
                lastname = ln;
                isLastnameValid = true;
                signupBTN.setEnabled(isUsernameValid && isPasswordValid &&
                        isFirstnameValid && isLastnameValid);
            } catch (IllegalStateException ex) {
                lastnameValLBL.setText(ex.getMessage());
                isLastnameValid = false;
            }

        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6,1));
        this.add(descriptionPNL);
        this.add(usernamePNL);
        this.add(passwordPNL);
        this.add(firstnamePNL);
        this.add(lastnamePNL);
        this.add(signupPNL);
        this.setSize(420, 550);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Signup();
    }

}
