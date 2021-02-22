
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeDat,int from)
    {
        int maxIndex = from;
        for (int i=from+1; i< quakeDat.size(); i++) {
            if (quakeDat.get(i).getDepth() > quakeDat.get(maxIndex).getDepth()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }
        
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in)
    {
       for (int i=0; i< 50; i++) {
            int maxIndex = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIndex);
            in.set(i,qmax);
            in.set(maxIndex,qi);
       }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) 
    {
        for(int i = 0; i <in.size()-1;i++)
        {
            onePassBubbleSort(in, i);
            //System.out.println("Printing Quakes after pass "+i);
            // for (QuakeEntry qe: in) { 
                // System.out.println(qe);
            // } 
        }

    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted)
    {
        for(int i =1; i< quakeData.size() - numSorted; i++)
        {
            double curr = quakeData.get(i).getMagnitude();
            double prev = quakeData.get(i-1).getMagnitude();
            
            if (prev > curr) {
                QuakeEntry qePrev = quakeData.get(i-1);
                quakeData.set(i-1,quakeData.get(i));
                quakeData.set(i, qePrev);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) 
    {
        double prevMag = quakes.get(0).getMagnitude();
        for(int i =1; i< quakes.size();i++)
        {
            double currMag = quakes.get(i).getMagnitude();
            if(prevMag> currMag)
                return false;
            prevMag = currMag;
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in)
    {
        int i;
        for(i = 0; i <in.size()-1;i++)
        {
            onePassBubbleSort(in, i);
            if(checkInSortedOrder(in))
                break;
            // System.out.println("Printing Quakes after pass "+i);
            // for (QuakeEntry qe: in) { 
                // System.out.println(qe);
            // } 
        }
        System.out.println("Total passes needed to sort = "+(i+1));
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       int i;
       for (i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if(checkInSortedOrder(in))
                break;
       }
       System.out.println("Total passes needed to sort = "+(i+1));
    }
    
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("The sorted quakes are --");
        // for (QuakeEntry qe: list) { 
            // System.out.println(qe);
        // } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
    }
}
