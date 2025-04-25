package NetworkRequest;

import java.io.Serializable;

public class SearchByPosition implements Serializable{
    private String position;
    public SearchByPosition(String position){
        this.position = position;
    }
    public String getPosition() {
        return position;
    }
}
