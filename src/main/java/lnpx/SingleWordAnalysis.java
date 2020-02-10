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
    int Occurrence;

    public SingleWordAnalysis(String Word, int NumberOfOccurrence) {
        this.Word = Word;
        this.Occurrence = NumberOfOccurrence;
    }

    public String getWord() {
        return Word;
    }

    public int getOccurrence() {
        return Occurrence;
    }

    public void setWord(String Word) {
        this.Word = Word;
    }

    public void setOccurrence(int Occurrence) {
        this.Occurrence = Occurrence;
    }
    
    
    
    
}
