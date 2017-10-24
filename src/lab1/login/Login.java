package lab1.login;

public class Login {
    private String login;
    private String password;

    public Login(String _login, String _password){
        login = _login;
        password = _password;
    }

    public boolean check(String _login, String _password){
        if(login.equals(_login) && password.equals(_password)){
            return true;
        }else{
            return false;
        }
    }

}