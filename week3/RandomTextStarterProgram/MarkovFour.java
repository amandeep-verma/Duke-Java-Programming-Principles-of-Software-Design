
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.*;
public class MarkovFour {
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
        setRandom(715);
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public ArrayList<String> getFollows(String key)
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

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k=0; k < numChars- 2; k++){
            ArrayList<String> ar  = getFollows(key);
            if(ar.size() == 0)
                break;
            index = myRandom.nextInt(ar.size());
            String next = ar.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        return sb.toString();
    }
}
