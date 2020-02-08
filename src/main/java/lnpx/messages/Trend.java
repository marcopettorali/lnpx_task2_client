/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx.messages;

/**
 *
 * @author rnoce
 */
public class Trend {
    
    private String word;
    private int value;

    public Trend(String word, int value) {
        this.word = word;
        this.value = value;
    }

    public String getWord() {
        return word;
    }

    public int getValue() {
        return value;
    }
    
    
    
}
