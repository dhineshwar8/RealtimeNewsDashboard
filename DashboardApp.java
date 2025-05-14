package dashboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DashboardApp extends Application {

    private ChartUpdater chartUpdater;
    private DataFetcher dataFetcher;
    private static Properties config;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Personalized News Dashboard");

        loadConfig();

        chartUpdater = new ChartUpdater();
        dataFetcher = new DataFetcher(chartUpdater, config);

        VBox sidebar = new VBox();
        sidebar.setSpacing(10);
        Button dashboardBtn = new Button("Dashboard");
        Button settingsBtn = new Button("Settings");
        sidebar.getChildren().addAll(dashboardBtn, settingsBtn);

        ToolBar toolbar = new ToolBar();
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> dataFetcher.fetchData());
        toolbar.getItems().add(refreshBtn);

        HBox chartsSection = new HBox(20);
        chartsSection.getChildren().addAll(chartUpdater.getBarChart(), chartUpdater.getPieChart(), chartUpdater.getLineChart());

        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebar);
        mainLayout.setTop(toolbar);
        mainLayout.setCenter(chartsSection);

        Scene scene = new Scene(mainLayout, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        dataFetcher.startDataUpdate();
    }

    private void loadConfig() {
        config = new Properties();
        try (FileInputStream input = new FileInputStream(".env")) {
            config.load(input);
        } catch (IOException e) {
            System.err.println("Failed to load .env file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
