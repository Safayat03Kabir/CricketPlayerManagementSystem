package NetworkRequest;

import java.io.Serializable;

public class MaximumAgeOfClub implements Serializable{
    private String clubName;
    public MaximumAgeOfClub(String clubName) {
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }
}
