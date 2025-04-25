package NetworkRequest;

import java.io.Serializable;

public class LoginRespond implements Serializable {
    private Boolean isValid;
    private String ClubName;
    public LoginRespond(Boolean isValid, String ClubName) {
        this.isValid = isValid;
        this.ClubName = ClubName;
    }
    public Boolean getIsValid() {
        return isValid;
    }
    public String getClubName() {
        return ClubName;
    }
}
