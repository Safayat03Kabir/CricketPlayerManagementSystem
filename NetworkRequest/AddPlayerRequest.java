package NetworkRequest;

import serverPackage.Player;

import java.io.Serializable;

public class AddPlayerRequest implements Serializable {
    private Player player;
    public AddPlayerRequest(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
}
