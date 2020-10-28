import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParseTest {
    private static final String filePath = "/home/whoami/IdeaProjects/Flights/src/main/java/flights.json";

    public static void main(String[] args) {
        List<Integer> price = getPriceFromJson(filePath);
        int min = price.get(0);
        int max = price.get(0);
        int avg = 0;

        for (int j : price) {
            min = Math.min(min, j);
            max = Math.max(max, j);
            avg += j;
        }

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Avg: " + avg / price.size());
    }

    private static List<Integer> getPriceFromJson(String filePath) {
        List<Integer> price = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            JSONArray flightArr = (JSONArray) jsonObject.get("flights");
            for (Object o : flightArr) {
                JSONObject row = (JSONObject) o;
                if (row.get("fromCity").equals("Москва") && row.get("toCity").equals("Хабаровск")) {
                    price.add(Integer.parseInt(String.valueOf(row.get("price"))));
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return price;
    }
}
