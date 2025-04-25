package NetworkRequest;

import java.io.Serializable;

public class SearchByClubAndCountry implements Serializable {
    private String countryName;
    private String clubName;

    public SearchByClubAndCountry(String clubName, String countryName) {
        this.countryName = countryName;
        this.clubName = clubName;
    }
    public String getCountryName() {
        return countryName;
    }
    public String getClubName() {
        return clubName;
    }
}
