import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getAPIData{

    // create HttpClient
    private HttpClient client = null;
    private HttpRequest request = null;
    private HttpResponse<String> response = null;
    public getAPIData(String reqUrl){

        // create HttpClient
        client = HttpClient.newHttpClient();

        // Build the request
        request = HttpRequest.newBuilder()
                .uri(URI.create(reqUrl))
                .build();

        try{

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        }

        catch(Exception e){

            System.out.println("Error occurred: " + e.getMessage());

        }

        System.out.println("API URL Called");

    }

    public void getRawCurrentWeather(){

            // print
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());


    }



}
