
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key)
    {
        ArrayList<String> ar  = new ArrayList<String>();
        StringBuilder text = new StringBuilder(myText);
        text.deleteCharAt(text.length()-1);
        int Index = text.indexOf(key);
        while(Index>=0)
        {
            ar.add(myText.substring(Index+key.length(),Index+key.length()+1));
            Index = text.indexOf(key, Index+1);
        }
        return ar;
    }
}
