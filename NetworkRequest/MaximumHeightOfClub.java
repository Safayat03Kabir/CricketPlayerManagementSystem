package NetworkRequest;

import java.io.Serializable;

public class MaximumHeightOfClub implements Serializable {
    private String clubName;
    public MaximumHeightOfClub(String clubName) {
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }
}
