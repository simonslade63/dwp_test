package simon;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

/**
 * A class to calculate the distance between 2 points in miles.
 *
 */
public class DistanceCalculator {
    
        private static final double METERS_IN_MILE = 1609.34;      
    
        private DistanceCalculator () {            
        }    
     
        public static double distanceInMiles(final double startLat, final double startLong,
                                      final double endLat, final double endLong) {

            final GeodesicData result =
                    Geodesic.WGS84.Inverse(startLat, startLong, endLat, endLong);
            
            final double distanceInMeters = result.s12;
            return  distanceInMeters / METERS_IN_MILE;
        }

}
