import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryReader {

	public static void main(String[] args) throws Exception {
		String querystring;

		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
		

		System.out.println("Enter Your Query String");
		
		querystring=bufferedReader.readLine();

		System.out.println("The Resultant String:"+querystring);

		Query query=new Query();

		query.parseQuery(querystring);
		

	}

}
