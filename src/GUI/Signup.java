package GUI;

import javax.swing.*;
import java.awt.*;

public class Signup extends JFrame{



    private JLabel descriptionLBL;

    private JLabel usernameLBL;
    private JTextField usernameTXT;
    private JButton usernameValBTN;
    private JLabel usernameValLBL;

    private JLabel passwordLBL;
    private JPasswordField passwordTXT;
    private JButton passwordValBTN;
    private JLabel passwordValLBL;

    private JLabel firstnameLBL;
    private JTextField firstnameTXT;
    private JButton firstnameValBTN;
    private JLabel firstnameValLBL;

    private JLabel lastnameLBL;
    private JTextField lastnameTXT;
    private JButton lastnameValBTN;
    private JLabel lastnameValLBL;

    private JButton signupBTN;

    private JPanel descriptionPNL;
    private JPanel usernamePNL;
    private JPanel passwordPNL;
    private JPanel firstnamePNL;
    private JPanel lastnamePNL;
    private JPanel signupPNL;

    public Signup() {
        descriptionLBL = new JLabel("fields with * sign are necessary");
        descriptionPNL = new JPanel(new FlowLayout(FlowLayout.LEADING));
        descriptionPNL.add(descriptionLBL);

        usernameLBL = new JLabel("*username:");
        usernameLBL.setPreferredSize(new Dimension(60, 35));
        usernameTXT = new JTextField();
        usernameTXT.setPreferredSize(new Dimension(200, 35));
        usernameValBTN = new JButton("validate");
        usernameValBTN.setBorder(BorderFactory.createEtchedBorder());
        usernameValBTN.setPreferredSize(new Dimension(50, 35));
        usernameValLBL = new JLabel();
        usernameValLBL.setPreferredSize(new Dimension(100, 20));
        usernamePNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        usernamePNL.setPreferredSize(new Dimension(420, 100));
        usernamePNL.add(usernameLBL);
        usernamePNL.add(usernameTXT);
        usernamePNL.add(usernameValBTN);
        usernamePNL.add(usernameValLBL);
        
        passwordLBL = new JLabel("*password:");
        passwordLBL.setPreferredSize(new Dimension(60, 35));
        passwordTXT = new JPasswordField();
        passwordTXT.setPreferredSize(new Dimension(200, 35));
        passwordValBTN = new JButton("validate");
        passwordValBTN.setBorder(BorderFactory.createEtchedBorder());
        passwordValBTN.setPreferredSize(new Dimension(50, 35));
        passwordValLBL = new JLabel();
        passwordValLBL.setPreferredSize(new Dimension(100, 20));
        passwordPNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        passwordPNL.setPreferredSize(new Dimension(420, 100));
        passwordPNL.add(passwordLBL);
        passwordPNL.add(passwordTXT);
        passwordPNL.add(passwordValBTN);
        passwordPNL.add(passwordValLBL);

        firstnameLBL = new JLabel("firstname:");
        firstnameLBL.setPreferredSize(new Dimension(60, 35));
        firstnameTXT = new JTextField();
        firstnameTXT.setPreferredSize(new Dimension(200, 35));
        firstnameValBTN = new JButton("validate");
        firstnameValBTN.setBorder(BorderFactory.createEtchedBorder());
        firstnameValBTN.setPreferredSize(new Dimension(50, 35));
        firstnameValLBL = new JLabel();
        firstnameValLBL.setPreferredSize(new Dimension(100, 20));
        firstnamePNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        firstnamePNL.setPreferredSize(new Dimension(420, 100));
        firstnamePNL.add(firstnameLBL);
        firstnamePNL.add(firstnameTXT);
        firstnamePNL.add(firstnameValBTN);
        firstnamePNL.add(firstnameValLBL);

        lastnameLBL = new JLabel("lastname:");
        lastnameLBL.setPreferredSize(new Dimension(60, 35));
        lastnameTXT = new JTextField();
        lastnameTXT.setPreferredSize(new Dimension(200, 35));
        lastnameValBTN = new JButton("validate");
        lastnameValBTN.setBorder(BorderFactory.createEtchedBorder());
        lastnameValBTN.setPreferredSize(new Dimension(50, 35));
        lastnameValLBL = new JLabel();
        lastnameValLBL.setPreferredSize(new Dimension(100, 20));
        lastnamePNL = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        lastnamePNL.setPreferredSize(new Dimension(420, 100));
        lastnamePNL.add(lastnameLBL);
        lastnamePNL.add(lastnameTXT);
        lastnamePNL.add(lastnameValBTN);
        lastnamePNL.add(lastnameValLBL);

        signupBTN = new JButton("sign up");
        signupBTN.setBorder(BorderFactory.createEtchedBorder());
        signupBTN.setPreferredSize(new Dimension(100, 30));
        signupPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signupPNL.add(signupBTN);

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
