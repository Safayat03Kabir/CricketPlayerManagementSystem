package NetworkRequest;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    public String ClientName;
    public String Password;
    public LoginRequest(String ClientName, String Password) {
        this.ClientName = ClientName;
        this.Password = Password;
    }
    public String GetClientName() {
        return ClientName;
    }
    public String GetPassword() {
        return Password;
    }
}
