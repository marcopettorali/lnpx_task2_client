/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx.messages;

import java.util.Map;
import lnpx.*;

/**
 *
 * @author rnoce
 */
public class FindMsg {
    
    private String Keyword;
    private Map<String,String> SelectedFilters;

    public FindMsg(String Keyword, Map<String,String> SelectedFilters) {
        this.Keyword = Keyword;
        this.SelectedFilters = SelectedFilters;
    }

    public String getKeyword() {
        return Keyword;
    }

    public Map<String,String> getSelectedFilters() {
        return SelectedFilters;
    }
    
    
    
}
