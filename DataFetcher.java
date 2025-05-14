package dashboard;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetcher {

    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY";
    private static final String NEWS_API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=YOUR_API_KEY";
    private static final String YAHOO_API_URL = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=AAPL,GOOG,AMZN";

    private ChartUpdater chartUpdater;

    public DataFetcher(ChartUpdater chartUpdater) {
        this.chartUpdater = chartUpdater;
    }

    public void startDataUpdate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> fetchData()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void fetchData() {
        new Thread(() -> {
            try {
                String weatherData = fetchFromAPI(WEATHER_API_URL);
                String newsData = fetchFromAPI(NEWS_API_URL);
                String stockData = fetchFromAPI(YAHOO_API_URL);

                chartUpdater.updateCharts(weatherData, newsData, stockData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String fetchFromAPI(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
