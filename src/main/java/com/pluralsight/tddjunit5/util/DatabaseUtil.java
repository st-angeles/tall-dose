package com.pluralsight.tddjunit5.util;

import com.pluralsight.tddjunit5.airport.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private DatabaseUtil(){

    }

    public static List<Flight> buildFlightsList(List<List<String>> queriedData){
        List<Flight> flightsList = new ArrayList<>();
        for(List<String> row: queriedData){
            String flightId = row.get(0);
            String flightType =  row.get(1);
            Flight flight = null;
            switch(flightType){
                case "e" :
                    flight = (new EconomyFlight(flightId));
                    break;
                case "b" :
                    flight = (new BusinessFlight(flightId));
                    break;
                case "p":
                    flight = (new PremiumFlight(flightId));
                    break;
            }
            flight.addPassenger(new Passenger(row.get(2), Boolean.valueOf(row.get(3))));
            flight.setDistance(Integer.valueOf(row.get(4)));
            flightsList.add(flight);
        }
        return flightsList;
    }
}
