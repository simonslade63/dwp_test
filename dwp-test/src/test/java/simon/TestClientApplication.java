package simon;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class TestClientApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		LondonFinder lf = context.getBean(LondonFinder.class);
		
		System.out.println(lf.getPeopleListedInLondonOrLocatedWithinFiftyMilesOfLondon());

		context.close();
	}

}
