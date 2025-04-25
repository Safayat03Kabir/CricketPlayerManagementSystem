package NetworkRequest;

import java.io.Serializable;

public class BuyPlayerRequest implements Serializable {
    private String playerName;
    private final String buyerName;
    public BuyPlayerRequest(String playerName, String buyerName) {
        this.playerName = playerName;
        this.buyerName = buyerName;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getBuyerName() {
        return buyerName;
    }
}
