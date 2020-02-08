/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

/**
 *
 * @author rnoce
 */
public class SingleWordAnalysis {
    
    String Word;
    int NumberOfOccurrence;

    public SingleWordAnalysis(String Word, int NumberOfOccurrence) {
        this.Word = Word;
        this.NumberOfOccurrence = NumberOfOccurrence;
    }

    public String getWord() {
        return Word;
    }

    public int getNumberOfOccurrence() {
        return NumberOfOccurrence;
    }
    
    
    
    
}
