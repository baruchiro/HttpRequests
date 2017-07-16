import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequest {

	public static String ExecuteRequest(HttpURLConnection httpRequest) throws IOException {

		// execute the request and get the response to InputStream
		InputStream inputStream = httpRequest.getInputStream(); // throws
																// IOException

		// Read from the InputStream
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		// Build String from reader
		StringBuffer response = new StringBuffer();

		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		reader.close();

		return response.toString();
	}

	public static String GET(String httpURL) {

		try {
			URL url = new URL(httpURL); // throw MalformedURLException

			HttpURLConnection httpRequest = (HttpURLConnection) url.openConnection();
			httpRequest.setRequestMethod("GET"); // throw ProtocolException

			return ExecuteRequest(httpRequest);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();

		} catch (ProtocolException e) {
			e.printStackTrace();
			return e.getMessage();

		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static String POST(String httpURL, String payload){
		try {
			payload="payload="+payload;
			URL url = new URL(httpURL); // throw MalformedURLException

			HttpURLConnection httpRequest = (HttpURLConnection) url.openConnection();
			httpRequest.setRequestMethod("POST"); // throw ProtocolException

			//Http request Headers
			httpRequest.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpRequest.setRequestProperty("Content-Length", Integer.toString(payload.getBytes().length));
			httpRequest.setDoOutput(true);	//use the URL Connection for input

			DataOutputStream outputStream = new DataOutputStream(httpRequest.getOutputStream());
			outputStream.writeBytes(payload);
			outputStream.close();

			return ExecuteRequest(httpRequest);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();

		} catch (ProtocolException e) {
			e.printStackTrace();
			return e.getMessage();

		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	public static void main(String args[]) {

		System.out.println("GET:http://httpbin.org/ip");
		System.out.println(GET("http://httpbin.org/ip"));

		System.out.println("POST:http://httpbin.org/post");
		System.out.println(POST("http://httpbin.org/post","Hello World"));
	}
}
