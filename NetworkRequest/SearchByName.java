package NetworkRequest;

import java.io.Serializable;

public class SearchByName implements Serializable{
    private String name;
    public SearchByName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
