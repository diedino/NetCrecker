import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        ContactCardImpl cc = new ContactCardImpl();
        cc.getInstance(a);
    }
}
