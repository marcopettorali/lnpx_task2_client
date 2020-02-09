package lnpx;

public class Trend {

    private String word;
    private Long value;

    public Trend(String word, Long value) {
        this.word = word;
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public Long getValue() {
        return value;
    }

}
