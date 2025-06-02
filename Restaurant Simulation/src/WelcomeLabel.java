import java.util.ArrayList;
public class WelcomeLabel {
    public static ArrayList <Order> lastorder;
    public static double totalincoming;

    public static void main(String[] args) {
        totalincoming=0;
        lastorder=new ArrayList<>();
        new Frame(); //gives you the sign/log in
    }
}
//built in accounts:
/*
Admin : 0000
Yousef : 1234
Maher : 2222
Yazan : 1111
Joud : 3333
*/