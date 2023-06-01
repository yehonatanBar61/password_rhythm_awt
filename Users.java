import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

public class Users {
    public volatile ArrayList<User> users;


    public Users(){
        this.users = new ArrayList<User>();
    }

    public void add(User a){
        this.users.add(a);
    }

    public User get_by_username(String username) throws Exception{
        for (User user : users) {
            if (user.get_username().equals(username)) {
                return user;
            }
        }
        throw new ArithmeticException("Username is not in list");
    }

    public void remove(User u){
        users.remove(u);
    }

    public boolean password_already_exists(String pass, Stack<Double> st){
        for (User user : users) {
            if (user.is_the_password(pass, st)) {
                return true;
            }
        }
        return false;
    }

    public int get_usercount(){
        return this.users.size();
    }

    public boolean username_already_exists(String username){
        for (User user : this.users) {
            if (user.get_username().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
