/**
 * 
 */
package simon;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author slade
 *
 */

@Component
public class LondonFinder {
    
    
    private static final String ALL_USERS_URL = "https://bpdts-test-app.herokuapp.com/users";
	private static final String LONDON_USERS_URL = "https://bpdts-test-app.herokuapp.com/city/London/users";
	private static final double LONDON_LATITUDE = 51.509865;
    private static final double LONDON_LONGITUDE = -0.118092;
    
    @Autowired
    private HttpClient client;

    /**
     * Get people that are listed as in London or located within 50 miles
     * @return 
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Person> getPeopleListedInLondonOrLocatedWithinFiftyMilesOfLondon() throws IOException, InterruptedException {
        
        final List<Person> people = getPeopleListedInLondon();
        people.addAll(getPeopleLocatedWithinMilesOfLondon(50));       
        
        //removing duplicates
        return new ArrayList<>(new LinkedHashSet<Person>(people));
    }
     
    /**
     * Get people that are listed as in London
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Person> getPeopleListedInLondon() throws IOException, InterruptedException {
        
        final HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(LONDON_USERS_URL))
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        final ObjectMapper mapper = new ObjectMapper();  
        
        return mapper.readValue(response.body().toString(), new TypeReference<List<Person>>(){});
        
    }
     
    /**
     * Get people that are located within 50 milesof London
     * @return 
     * @throws IOException
     * @throws InterruptedException
     */
    public List<Person> getPeopleLocatedWithinMilesOfLondon(final int numberOfMiles) throws IOException, InterruptedException {
        
        final HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ALL_USERS_URL))
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        final ObjectMapper mapper = new ObjectMapper();
        final Person[] people = mapper.readValue(response.body().toString(), Person[].class);         
        final List<Person> peopleNearLondon = new ArrayList<>();
        
        for (final Person p: people) {
            final double distanceAway = DistanceCalculator.distanceInMiles(LONDON_LATITUDE, LONDON_LONGITUDE, p.getLatitude(), p.getLongitude());
            if (distanceAway <= numberOfMiles) {
                peopleNearLondon.add(p);
            }            
        }
        
        return peopleNearLondon;
        
    }

}
