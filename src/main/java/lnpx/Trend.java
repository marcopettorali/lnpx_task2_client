package lnpx;

public class Trend {

    private String Keyword;
    private Long Percentage;

    public Trend(String word, Long value) {
        this.Keyword = word;
        this.Percentage = value;
    }

    public String getKeyword() {
        return Keyword;
    }

    public Long getPercentage() {
        return Percentage;
    }

}
