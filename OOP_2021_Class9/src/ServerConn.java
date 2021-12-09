public class ServerConn {
    private static ServerConn _instanse = null;
    public static ServerConn getInstance(String url) {
        if (_instanse == null) {
            _instanse = new ServerConn(url);
        }
        return _instanse;
    }

    private String conn;

    ServerConn(String url){
        conn = url;
    }


}
