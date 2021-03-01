
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1= key2;
            key2 = next;
        }

        return sb.toString().trim();
    }
    
    private int indexOf(String[] words,String target1, String target2, int start)
    {
        for(int k= start; k<words.length-1;k++){
            if(words[k].equals(target1) && words[k+1].equals(target2))
                return k;
        }
        return -1;
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> ar = new ArrayList<String>();
        int Index = indexOf(myText,key1,key2, 0);

        while(Index>=0 && Index < myText.length-2)
        {
            ar.add(myText[Index+2]);
            Index = indexOf(myText,key1,key2, Index+1);
        }
        return ar;
    }

    public String toString()
    {
        return "MarkovWordTwo";
    }
}
