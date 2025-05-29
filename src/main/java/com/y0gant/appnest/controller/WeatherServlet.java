package com.y0gant.appnest.controller;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@WebServlet("/weatherApp")
public class WeatherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServlet.class);
    private static final Dotenv env = Dotenv.load();
    private static final String API_KEY = env.get("WEATHER_API");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userName") == null || ((String) session.getAttribute("userName")).isEmpty()) {
            logger.warn("Unauthorized access attempt to /weatherApp. Redirecting to login.");
            resp.sendRedirect("login.jsp");
            return;
        }

        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            logger.warn("Empty city name provided by user: {}", session.getAttribute("userName"));
            req.setAttribute("error", "Please enter a city name.");
            req.getRequestDispatcher("weather.jsp").forward(req, resp);
            return;
        }

        city = city.trim();
        logger.debug("Fetching weather data for city: {}", city);

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +
                city + "&appid=" + API_KEY + "&units=metric";

        try {
            URL url = new URI(apiUrl).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            logger.debug("OpenWeatherMap API response code: {}", status);

            if (status != 200) {
                logger.error("Failed to fetch weather for '{}'. API returned status code: {}", city, status);
                req.setAttribute("error", "City not found or API limit reached.");
                req.getRequestDispatcher("weather.jsp").forward(req, resp);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                responseContent.append(inputLine);
            }
            reader.close();
            conn.disconnect();

            JSONObject json = new JSONObject(responseContent.toString());
            String temp = String.valueOf(json.getJSONObject("main").getDouble("temp"));
            String desc = json.getJSONArray("weather").getJSONObject(0).getString("description");

            logger.info("Weather for '{}': {}°C, {}", city, temp, desc);

            req.setAttribute("city", city);
            req.setAttribute("temp", temp);
            req.setAttribute("desc", desc);

        } catch (Exception e) {
            logger.error("Exception while fetching weather data for '{}': {}", city, e.getMessage(), e);
            req.setAttribute("error", "Something went wrong while fetching weather data.");
        }

        req.getRequestDispatcher("weather.jsp").forward(req, resp);
    }
}
