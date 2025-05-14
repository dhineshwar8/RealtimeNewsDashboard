package dashboard;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChartUpdater {

    private XYChart.Series<String, Number> barSeries;
    private XYChart.Series<Number, Number> lineSeries;
    private PieChart pieChart;

    public ChartUpdater() {
        barSeries = new XYChart.Series<>();
        barSeries.getData().add(new XYChart.Data<>("AAPL", 0));
        barSeries.getData().add(new XYChart.Data<>("GOOG", 0));
        barSeries.getData().add(new XYChart.Data<>("AMZN", 0));

        lineSeries = new XYChart.Series<>();
        lineSeries.getData().add(new XYChart.Data<>(1, 0));
        lineSeries.getData().add(new XYChart.Data<>(2, 0));
        lineSeries.getData().add(new XYChart.Data<>(3, 0));

        pieChart = new PieChart();
        pieChart.getData().add(new PieChart.Data("Temperature", 0));
        pieChart.getData().add(new PieChart.Data("Humidity", 0));
        pieChart.getData().add(new PieChart.Data("Wind Speed", 0));
    }

    public BarChart<String, Number> getBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.getData().add(barSeries);
        return barChart;
    }

    public LineChart<Number, Number> getLineChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().add(lineSeries);
        return lineChart;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public void updateCharts(String weatherData, String newsData, String stockData) {
        try {
            JSONObject weatherObj = new JSONObject(weatherData);
            double temp = weatherObj.getJSONObject("main").getDouble("temp") - 273.15;
            double humidity = weatherObj.getJSONObject("main").getDouble("humidity");
            double windSpeed = weatherObj.getJSONObject("wind").getDouble("speed");

            pieChart.getData().get(0).setPieValue(temp);
            pieChart.getData().get(1).setPieValue(humidity);
            pieChart.getData().get(2).setPieValue(windSpeed);

            JSONObject stockObj = new JSONObject(stockData).getJSONObject("quoteResponse");
            JSONArray results = stockObj.getJSONArray("result");
            barSeries.getData().get(0).setYValue(results.getJSONObject(0).getDouble("regularMarketPrice"));
            barSeries.getData().get(1).setYValue(results.getJSONObject(1).getDouble("regularMarketPrice"));
            barSeries.getData().get(2).setYValue(results.getJSONObject(2).getDouble("regularMarketPrice"));

            JSONArray newsArticles = new JSONObject(newsData).getJSONArray("articles");
            for (int i = 0; i < 3; i++) {
                lineSeries.getData().get(i).setYValue(newsArticles.getJSONObject(i).getString("title").length());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
