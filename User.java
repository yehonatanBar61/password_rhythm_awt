import java.util.Stack;

public class User {
    //pass and time stack should be private for now they are not
    String password;
    private Stack<Double> time_st;
    String user_name;


    public User(){
        this.user_name = "";
        this.password = "";
        this.time_st = null;
    }

    public User(String user_name, String password, Stack<Double> st){
        this.user_name = user_name;
        this.password = password;
        this.time_st = st;
    }

    public String get_username(){
        return this.user_name;
    }

    public void Set_pass(String s){
        this.password = s;
    }

    public void Set_timeSt(Stack<Double> time_st){
        this.time_st = time_st;
    }

    public void Set_username(String username){
        this.user_name = username;
    }

    public boolean is_the_password(String pass, Stack<Double> st){
        return pass.equals(this.password) && algo.stack_compare(st, time_st);
    }
}
