
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;
    public  DistanceFilter(Location location, double maxDistance)
    {
        this.maxDistance = maxDistance;
        this.location = location;
    }
    public  boolean satisfies(QuakeEntry qe)
    {
        return (qe.getLocation().distanceTo(location) < maxDistance );
    }
    
    public String getName()
    {
        return "DistanceFilter";
    }
}
