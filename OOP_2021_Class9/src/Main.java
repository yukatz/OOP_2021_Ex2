public class Main {
    public static void main(String[] args) {
        ServerConn con1 = new ServerConn("www.google.com");
        ServerConn con2 = new ServerConn("www.google.com");
        System.out.println(con1==con2);

    }

    public static void doSomething(){
        ServerConn c1 = ServerConn.getInstance("www.google.com");
        }
}
