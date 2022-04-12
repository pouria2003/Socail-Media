package User;

import DataBase.ReadUser;
import DataBase.Signup;
import java.sql.SQLException;

public class UserRelated {

    public static void registerUser(String username, String password,
                                    String firstname, String lastname) {

        User new_user = null;
        try {
            new_user = new User(username, password, firstname, lastname);
        } catch (Exception e) {
            /// do something with GUI
        }

        try{
            Signup.SignUserUp(new_user);
        } catch (SQLException e) {
            /// do something with GUI
        }
    }

    public static User CreateExistingUser(String userName) {
        User user = null;
        try{
            user = ReadUser.readUser(userName);
        } catch (SQLException e) {
            /// do something with GUI
        }
        return user;
    }
}
