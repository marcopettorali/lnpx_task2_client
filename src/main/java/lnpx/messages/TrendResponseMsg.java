package lnpx.messages;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendResponseMsg implements Serializable {

    private Map<String,Integer> TrendingKeywords;

    public TrendResponseMsg(Map<String,Integer> tk) {
        this.TrendingKeywords=tk;
    }

    public Map<String,Integer> getTrendingKeywords() {
        return TrendingKeywords;
    }

    
}
