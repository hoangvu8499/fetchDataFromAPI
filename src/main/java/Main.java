import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
public class Main {

//    private static HttpURLConnection connection;

    public static void main(String[] args) {
//        try {
//            // TYPE 1 TODO
//            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
//            connection = (HttpURLConnection) url.openConnection();
//
//            //Request setup
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//
//            int statusCode = connection.getResponseCode();
//            System.out.println(statusCode);

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//            // TYPE 2 TODO
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenApply(Main::mapperObject)
//                .join();
        try {
            String jsonResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            mapperObject(jsonResponse);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static List<album> mapperObject(String responseJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<album> albums = new ArrayList<>();
        try {
            albums = objectMapper.readValue(responseJson,  new TypeReference<List<album>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for(album object: albums) {
            System.out.println(object.toString());
        }

        return albums;
    }


}
