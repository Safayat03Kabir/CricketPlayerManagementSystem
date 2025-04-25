package java.Chalachali_korar_object;

import serverPackage.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class ArrayListBooleans implements Serializable {
    private Boolean isForMarket;
    private Boolean isForClub;
    private ArrayList<Player> array;
    public ArrayListBooleans(Boolean isForMarket, Boolean isForClub, ArrayList<Player> array) {
        this.isForMarket = isForMarket;
        this.isForClub = isForClub;
        this.array = array;
    }
    public Boolean getIsForMarket() {
        return isForMarket;
    }

    public void setIsForMarket(Boolean isForMarket) {
        this.isForMarket = isForMarket;
    }
    public Boolean getIsForClub() {
        return isForClub;
    }
    public void setIsForClub(Boolean isForClub) {
        this.isForClub = isForClub;

    }
    public ArrayList<Player> getArray() {
        return array;
    }


}
