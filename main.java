import java.awt.*;
import java.util.Stack;


public class main {
    public static void main(String[] args) throws InterruptedException {
        awt_login login = new awt_login();
        Thread th = new Thread(login);
        th.start();
    }
}
