package sevenDayOfCode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Day1 {
	public static void main(String[] args) {

		String apiKey = "<apiKey>";
		URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
