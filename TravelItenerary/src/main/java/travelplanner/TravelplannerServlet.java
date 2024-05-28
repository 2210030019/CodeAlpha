package travelplanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TravelplannerServlet")
public class TravelplannerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        String preference = request.getParameter("preference");
        double totalBudget = calculateBudget(destination, 7);
        String itinerary = generateItinerary(destination, date, preference);
        request.setAttribute("itinerary", itinerary);
        request.setAttribute("totalBudget", totalBudget);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
    private String generateItinerary(String destination, String date, String preference) {
        StringBuilder itinerary = new StringBuilder();
        itinerary.append("Your Travel Itinerary:<br>");
        itinerary.append("Destination: ").append(destination).append("<br>");
        itinerary.append("Date: ").append(date).append("<br>");
        itinerary.append("Preference: ").append(preference).append("<br><br>");
        if (preference != null) {
            itinerary.append("Preference: ").append(preference).append("\n");

            if (preference.equals("Sightseeing")) {
                itinerary.append("Day 1: Visit popular tourist attractions.<br>");
                itinerary.append("Day 2: Explore local landmarks.<br>");
            } else if (preference.equals("Adventure Sports")) {
                itinerary.append("Day 1: Go hiking in the mountains.<br>");
                itinerary.append("Day 2: Try water sports at the beach.<br>");
            } else if (preference.equals("Cultural Experiences")) {
                itinerary.append("Day 1: Visit museums and art galleries.<br>");
                itinerary.append("Day 2: Attend cultural festivals and events.<br>");
            }
        } else {
            itinerary.append("Preference: Not specified<br>");
        }

        return itinerary.toString();
    }
    private double calculateBudget(String destination, int duration) {
        
        double accommodationCostPerNight;
        double foodCostPerDay;
        double transportationCost;

       
        if (destination.equals("india")) {
            accommodationCostPerNight = 200;
            foodCostPerDay = 100;
            transportationCost = 500;
        } else if (destination.equals("newyork")) {
            accommodationCostPerNight = 150;
            foodCostPerDay = 80;
            transportationCost = 400;
        } else {
            accommodationCostPerNight = 100;
            foodCostPerDay = 50;
            transportationCost = 200;
        }
        double totalBudget = (accommodationCostPerNight * duration) + (foodCostPerDay * duration) + transportationCost;
        return totalBudget;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
