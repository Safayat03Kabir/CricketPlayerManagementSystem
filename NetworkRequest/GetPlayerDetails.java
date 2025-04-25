package NetworkRequest;

import java.io.Serializable;

public class GetPlayerDetails implements Serializable {
    String playerName;
    public GetPlayerDetails(String playerName) {
        this.playerName = playerName;
    }
    public String getPlayerName() {
        return playerName;
    }

}
