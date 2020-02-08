/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx.messages;

import java.util.Map;

/**
 *
 * @author rnoce
 */
public class ViewMsg {
    
    private String linkArticle;
    private Map<String,String> filters;

    public ViewMsg(String linkArticle, Map<String, String> filters) {
        this.linkArticle = linkArticle;
        this.filters = filters;
    }

    public String getLinkArticle() {
        return linkArticle;
    }

    public Map<String, String> getFilters() {
        return filters;
    }
    
    
    
}
