package test_first_week;

public class Main {
     public static void main(String...args) {
        int x=30;
        Integer X = x;
        increment(X);
        System.out.println(X);
        Integer a = 50;
        Integer b = 50;
         if (a == b) {
             System.out.println(a.hashCode());
             System.out.println(b.hashCode());
         } else {
             System.out.println(false);
         }
         Integer c = 500;
         Integer d = 500;
         if (c == d) {
             System.out.println(true);
         } else {
             System.out.println(false);
             System.out.println(c.hashCode());
             System.out.println(d.hashCode());
         }
         System.out.println(c);
         System.out.println(d);
         String str = "123";
         String str123 = "123";
         System.out.println(str.hashCode());
         System.out.println(str123.hashCode());
     }
    public static void increment(Integer N) {
        N = N+1;
    }
    public static void A() {}
    public static void B() {}
}
final class First {
    private int a=1;
    int b=2;
}