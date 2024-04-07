package client;

public class Credentials {
    private final String login;
    private final String password;

    public static Credentials fromCourier (Courier courier){
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public static Credentials authorization (String login, String password){
        return new Credentials(login, password);
    }

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
