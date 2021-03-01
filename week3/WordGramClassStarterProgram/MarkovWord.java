
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }

        return sb.toString().trim();
    }
    
    private int indexOf(String[] words,WordGram target, int start)
    {
        for(int k= start; k<words.length;k++){
            // might want to check target with indexes
            if(words[k].equals(target))
                return k;
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram  key) {
        ArrayList<String> ar = new ArrayList<String>();
        int Index = indexOf(myText,key, 0);

        while(Index>=0 && Index < myText.length-1)
        {
            ar.add(myText[Index+1]);
            Index = indexOf(myText,key, Index+1);
        }
        return ar;
    }
    
}
