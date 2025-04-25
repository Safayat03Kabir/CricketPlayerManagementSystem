package NetworkRequest;

import java.io.Serializable;

public class SignUpRequest implements Serializable {
    String ClubName;
    String Password;
    public SignUpRequest(String ClubName, String Password) {
        this.ClubName = ClubName;
        this.Password = Password;
    }
    public String getClubName() {
        return ClubName;
    }
    public String getPassword() {
        return Password;
    }
}
