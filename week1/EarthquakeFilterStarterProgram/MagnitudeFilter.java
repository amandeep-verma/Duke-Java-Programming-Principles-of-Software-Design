
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double maxMag;
    private double minMag;
    public MagnitudeFilter(double minMag, double maxMag)
    {
        this.minMag = minMag;
        this.maxMag = maxMag;
    }
    public  boolean satisfies(QuakeEntry qe)
    {
        return (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag );
    }
    
        public String getName()
    {
        return "MagnitudeFilter";
    }
}
