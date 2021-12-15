package com.pluralsight.tddjunit5.util;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import com.pluralsight.tddjunit5.airport.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightArgumentConverter extends SimpleArgumentConverter {
    @Override
    public Object convert(Object sourceCSV, Class<?> targetType){

        assertEquals(String.class, sourceCSV.getClass(),"Can only convert from String");
        assertEquals(Flight.class, targetType,"Can only convert to Flight");

        String[] parsedCSV = sourceCSV.toString().split(";");

        String flightId   = parsedCSV[0].toLowerCase().trim();
        String flightType = parsedCSV[1].toLowerCase().trim();
        Flight flight = null;
        switch(flightType){
            case "b" :
                flight = new BusinessFlight(flightId);
                break;
            case "e" :
                flight = new EconomyFlight(flightId);
                break;
            case "p" :
                flight = new PremiumFlight(flightId);
                break;
        }

        String passengerName = parsedCSV[2].trim();
        String vipFlag =  parsedCSV[3].toLowerCase().trim();
        Passenger passenger = new Passenger(passengerName,Boolean.valueOf(vipFlag));
        flight.addPassenger(passenger);

        String distanceInfo = parsedCSV[4].toLowerCase().trim();
        flight.setDistance(Integer.valueOf(distanceInfo));

        return flight;
    }
}
