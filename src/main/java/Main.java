import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{

    public static void main(String[] args){

        String apiUrl = "http://api.weatherapi.com/v1/current.json";

        String apiKey = "7b24dbb9d8a34787ad7110801231010";

        String location = "Jakarta";

        String requestUrl = apiUrl + "?key=" + apiKey + "&q=" + location;

//        getAPIData apiData = new getAPIData(requestUrl);

//        apiData.getRawCurrentWeather();

        getFormatCurrentWeather();

    }

    public static void getFormatCurrentWeather(){

        String jsonResponse =
                "{\"location\":{\"name\":\"Jakarta\",\"region\":\"Jakarta Raya\",\"country\":\"Indonesia\",\"lat\":-6.21,\"lon\":106.85,\"tz_id\":\"Asia/Jakarta\",\"localtime_epoch\":1696937749,\"localtime\":\"2023-10-10 18:35\"},\"current\":{\"last_updated_epoch\":1696937400,\"last_updated\":\"2023-10-10 18:30\",\"temp_c\":29.0,\"temp_f\":84.2,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":4.3,\"wind_kph\":6.8,\"wind_degree\":50,\"wind_dir\":\"NE\",\"pressure_mb\":1011.0,\"pressure_in\":29.85,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":75,\"cloud\":25,\"feelslike_c\":30.8,\"feelslike_f\":87.4,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":1.0,\"gust_mph\":12.9,\"gust_kph\":20.7}}";

        try{

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // extract data
            JsonNode location = jsonNode.get("location");
            String cityName = location.get("name").asText();
            String region = location.get("region").asText();
            String country = location.get("country").asText();

            JsonNode current = jsonNode.get("current");
            String lastUpdate = current.get("last_updated").asText();
            String tempC = current.get("temp_c").asText();
            String condition = current.get("condition").get("text").asText();
            String windKph = current.get("wind_kph").asText();
            String windDir = current.get("wind_dir").asText();

            System.out.printf("%s -- %s -- %s\n", cityName, region, country);
            System.out.println("=======================");
            System.out.println("Current Weather");
            System.out.println("Last Update: " + lastUpdate);
            System.out.println("Temperature: " + tempC);
            System.out.println("Condition: " + condition);
            System.out.println("Winds: " + windKph + ", Heading: " + windDir);

        }

        catch(Exception e){

            System.out.println("error");

        }

    }

}