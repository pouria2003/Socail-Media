package Main;

import DataBase.ReadUser;
import GUI.FirstPage;
import GUI.Login;
import GUI.Signup;
import User.User;

import java.sql.SQLException;

public class Main {
    User user = null;
    private Main() {}

    private static Main only_main_obj = null;

    public static Main getInstance() {
        if(only_main_obj == null) {
            only_main_obj = new Main();
        }
        return only_main_obj;
    }

    public void signUp(User user) {
        if(user == null)
            throw new NullPointerException();
        this.user = user;
        System.out.println("user entered : \n" + this.user.getUsername() + "\n" + this.user.getPassword());
    }

    public void login(User user) {
        if(user == null)
            throw new NullPointerException();
        this.user = user;
        System.out.println("user entered : \n" + this.user.getUsername() + "\n" + this.user.getPassword());
    }

    public static void GenerateSignUpGUI() {
        new Signup();
    }
    public static void GenerateFirstPageGUI() { new FirstPage(); }
    public static void GenerateLoginGUI() { new Login(); }

    public static void main(String[] args) {
        getInstance();
        GenerateFirstPageGUI();
    }
}
