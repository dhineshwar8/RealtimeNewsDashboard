
# Realtime News Dashboard

This is a JavaFX-based dashboard that displays real-time data, including weather, news, and stock market information. It provides interactive charts that dynamically update based on the latest fetched data.

## Features

- **Real-Time News**: Displays top headlines from news sources.
- **Weather Information**: Shows the current weather including temperature, humidity, and wind speed.
- **Stock Market Data**: Provides the latest stock prices for popular companies (AAPL, GOOG, AMZN).
- **Live Data Update**: Data is fetched and displayed every 10 seconds.
- **Interactive Charts**: Includes Bar, Line, and Pie charts for visualizing data.

## Technologies Used

- **JavaFX**: For creating the graphical user interface (GUI).
- **JSON**: For parsing the data fetched from APIs.
- **API Integration**:
  - **OpenWeatherMap** for weather data.
  - **NewsAPI** for news data.
  - **Yahoo Finance API** for stock market data.

## Prerequisites

Before running the project, ensure that you have the following installed:

- **Java Development Kit (JDK)**: Java 11 or later
- **Maven**: For dependency management and building the project (optional if using an IDE)
- **API Keys**: You’ll need to register and get API keys for the following services:
  - [OpenWeatherMap](https://openweathermap.org/api)
  - [NewsAPI](https://newsapi.org/)
  - [Yahoo Finance API](https://www.yahoofinanceapi.com/)

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dhineshwar8/RealtimeNewsDashboard.git
   ```

2. **Configure `.env` File**:
   Create a `.env` file in the root of your project and add the following variables:
   
   ```
   WEATHER_API_KEY=""
   NEWS_API_KEY=""
   YAHOO_API_KEY=""
   ```

3. **Install JavaFX**:
   Make sure you have JavaFX installed. You can add JavaFX libraries to your project manually or use a build tool like Maven or Gradle to manage dependencies.

4. **Build the Project**:
   If using Maven, run the following command to build the project:
   
   ```bash
   mvn clean install
   ```

5. **Run the Application**:
   You can run the application from your IDE or from the command line:

   ```bash
   mvn javafx:run
   ```

## File Structure

- `src/`
  - `DashboardApp.java`: Main class for launching the application.
  - `DataFetcher.java`: Handles API calls and data fetching.
  - `ChartUpdater.java`: Updates the charts based on fetched data.
  - `.env`: Contains the API keys.
  
## Screenshots

![Dashboard Screenshot]

## Troubleshooting

- If you encounter any issues related to API limits, ensure your API keys are valid and you haven’t exceeded the rate limits for each API.
- For JavaFX issues, ensure you’ve set up the correct JavaFX version in your build configuration.

