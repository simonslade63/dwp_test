package simon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DistanceCalculatorTest {
	
	
	@Test
	public void testDistanceInMiles() {
		
		final double seoulLat  = 37.5326;
		final double seoulLong = 127.024612;
		final double manchesterLat = 53.483959;
		final double manchesterLong = -2.244644;
		assertEquals(5472,DistanceCalculator.distanceInMiles(seoulLat, seoulLong, manchesterLat, manchesterLong), 0.1);
	}

}
