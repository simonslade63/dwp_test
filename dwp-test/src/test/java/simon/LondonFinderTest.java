package simon;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LondonFinderTest {
	
	@InjectMocks
	private LondonFinder londonFinder;
	
	@Mock
	private HttpClient client;
	
	@Mock
	private HttpResponse<Object> response;
	
	
	@Test
	public void testGetPeopleListedInLondonOrLocatedWithinFiftyMilesOfLondon() throws IOException, InterruptedException {
		
		when(client.send(any(), any ())).thenReturn(response);		
		when(response.body()).thenReturn("[\r\n" + 
				"  {\r\n" + 
				"    \"id\": 135,\r\n" + 
				"    \"first_name\": \"Mechelle\",\r\n" + 
				"    \"last_name\": \"Boam\",\r\n" + 
				"    \"email\": \"mboam3q@thetimes.co.uk\",\r\n" + 
				"    \"ip_address\": \"113.71.242.187\",\r\n" + 
				"    \"latitude\": -6.5115909,\r\n" + 
				"    \"longitude\": 105.652983\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": 396,\r\n" + 
				"    \"first_name\": \"Terry\",\r\n" + 
				"    \"last_name\": \"Stowgill\",\r\n" + 
				"    \"email\": \"tstowgillaz@webeden.co.uk\",\r\n" + 
				"    \"ip_address\": \"143.190.50.240\",\r\n" + 
				"    \"latitude\": -6.7098551,\r\n" + 
				"    \"longitude\": 111.3479498\r\n" + 
				"  },"	+ 
				"{\r\n" + 
				"    \"id\": 266,\r\n" + 
				"    \"first_name\": \"Ancell\",\r\n" + 
				"    \"last_name\": \"Garnsworthy\",\r\n" + 
				"    \"email\": \"agarnsworthy7d@seattletimes.com\",\r\n" + 
				"    \"ip_address\": \"67.4.69.137\",\r\n" + 
				"    \"latitude\": 51.6553959,\r\n" + 
				"    \"longitude\": 0.0572553\r\n" + 
				"  }]");	
		
		List<Person> people = londonFinder.getPeopleListedInLondonOrLocatedWithinFiftyMilesOfLondon();		
		
		verify (client, times(2)).send(any(), any ()); 
		assertEquals(3, people.size()); //should only be 3 as although the last one will  will be returned twice, duplicates will have been removed. 
		
	}

	@Test
	public void testGetPeopleListedInLondon() throws IOException, InterruptedException {
		when(client.send(any(), any ())).thenReturn(response);		
		when(response.body()).thenReturn("[\r\n" + 
				"  {\r\n" + 
				"    \"id\": 135,\r\n" + 
				"    \"first_name\": \"Mechelle\",\r\n" + 
				"    \"last_name\": \"Boam\",\r\n" + 
				"    \"email\": \"mboam3q@thetimes.co.uk\",\r\n" + 
				"    \"ip_address\": \"113.71.242.187\",\r\n" + 
				"    \"latitude\": -6.5115909,\r\n" + 
				"    \"longitude\": 105.652983\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": 396,\r\n" + 
				"    \"first_name\": \"Terry\",\r\n" + 
				"    \"last_name\": \"Stowgill\",\r\n" + 
				"    \"email\": \"tstowgillaz@webeden.co.uk\",\r\n" + 
				"    \"ip_address\": \"143.190.50.240\",\r\n" + 
				"    \"latitude\": -6.7098551,\r\n" + 
				"    \"longitude\": 111.3479498\r\n" + 
				"  }]");
		
		List<Person> people = londonFinder.getPeopleListedInLondon();
		
		verify (client, times(1)).send(any(), any ()); 
		assertEquals(2, people.size()); 
	}

	@Test
	public void testGetPeopleLocatedWithinMilesOfLondon() throws IOException, InterruptedException {
		when(client.send(any(), any ())).thenReturn(response);		
		when(response.body()).thenReturn("[\r\n" + 
				"  {\r\n" + 
				"    \"id\": 135,\r\n" + 
				"    \"first_name\": \"Mechelle\",\r\n" + 
				"    \"last_name\": \"Boam\",\r\n" + 
				"    \"email\": \"mboam3q@thetimes.co.uk\",\r\n" + 
				"    \"ip_address\": \"113.71.242.187\",\r\n" + 
				"    \"latitude\": -6.5115909,\r\n" + 
				"    \"longitude\": 105.652983\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": 396,\r\n" + 
				"    \"first_name\": \"Terry\",\r\n" + 
				"    \"last_name\": \"Stowgill\",\r\n" + 
				"    \"email\": \"tstowgillaz@webeden.co.uk\",\r\n" + 
				"    \"ip_address\": \"143.190.50.240\",\r\n" + 
				"    \"latitude\": -6.7098551,\r\n" + 
				"    \"longitude\": 111.3479498\r\n" + 
				"  },"	+ 
				"{\r\n" + 
				"    \"id\": 266,\r\n" + 
				"    \"first_name\": \"Ancell\",\r\n" + 
				"    \"last_name\": \"Garnsworthy\",\r\n" + 
				"    \"email\": \"agarnsworthy7d@seattletimes.com\",\r\n" + 
				"    \"ip_address\": \"67.4.69.137\",\r\n" + 
				"    \"latitude\": 51.6553959,\r\n" + 
				"    \"longitude\": 0.0572553\r\n" + 
				"  }]");
		
		List<Person> people = londonFinder.getPeopleLocatedWithinMilesOfLondon(50);
		
		verify (client, times(1)).send(any(), any ()); 
		assertEquals(1, people.size()); 
	}

}
