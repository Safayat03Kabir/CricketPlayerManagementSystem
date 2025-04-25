package NetworkRequest;

import java.io.Serializable;

public class SellPlayerRequest implements Serializable {
    String playerName;
    Double Price;
    public SellPlayerRequest(String playerName, Double Price) {
        this.playerName = playerName;
        this.Price = Price;
    }
    public String getPlayerName() {
        return playerName;
    }
    public Double getPrice() {
        return Price;
    }
}
