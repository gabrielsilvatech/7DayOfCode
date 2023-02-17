package sevenDayOfCode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
	public static void main(String[] args) throws Exception {

		String json = buscarFilme();

		String listaTodosOsFilmes = json.substring(json.indexOf('[') + 1 , json.indexOf(']'));
		String[] listaFilmes = listaTodosOsFilmes.split("(?<=}),");
		
		List<String> titles = new ArrayList<String>();
		List<String> urlImages = new ArrayList<String>();

		for (int i = 0; i < listaFilmes.length; i++) {
			titles.add(listaFilmes[i].substring(listaFilmes[i].indexOf("\"title")  ,
					listaFilmes[i].indexOf(",", listaFilmes[i].indexOf("\"title"))));
		}
		
		titles.forEach(System.out::println);
		
		
		
		for (int i = 0; i < listaFilmes.length; i++) { 
			urlImages.add(listaFilmes[i].substring(listaFilmes[i].indexOf("\"image") ,
					listaFilmes[i].indexOf(",", listaFilmes[i].indexOf("\"image"))));
		}

		urlImages.forEach(System.out::println);

	}

	

	private static String buscarFilme() throws Exception {
		String apiKey = "<apiKey>";
		URI uri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey);

		HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json").GET()
				.build();

		HttpClient client = HttpClient.newBuilder().build();

		// HttpResponse<String> response = client.send(request,
		// HttpResponse.BodyHandlers.ofString());

		return client.send(request, BodyHandlers.ofString()).body();

	}

}
