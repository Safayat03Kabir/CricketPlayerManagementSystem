package NetworkRequest;

import java.io.Serializable;

public class SearchBySalaryRange implements Serializable {
    private Double upperBound;
    private Double lowerBound;
    public SearchBySalaryRange(Double upperBound, Double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
    public Double getUpperBound() {
        return upperBound;
    }
    public Double getLowerBound() {
        return lowerBound;
    }
}
