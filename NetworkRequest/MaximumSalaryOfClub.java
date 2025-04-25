package NetworkRequest;

import java.io.Serializable;

public class MaximumSalaryOfClub implements Serializable {
    private String clubName;
    public MaximumSalaryOfClub(String clubName) {
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }
}
