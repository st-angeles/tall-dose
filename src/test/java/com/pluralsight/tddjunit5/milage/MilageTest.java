package com.pluralsight.tddjunit5.milage;

import com.pluralsight.tddjunit5.airport.Flight;
import com.pluralsight.tddjunit5.airport.Passenger;
import com.pluralsight.tddjunit5.util.FlightArgumentConverter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MilageTest {

    private Milage milage;

    @BeforeAll
    void beforeAll(){
        milage = new Milage();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1; e; Doris; false; 349",
            "2; b; Angelina; true; 278",
            "3; e; Doris; false; 319",
            "4; p; Angelina; true; 817",
            "5; e; Doris; false; 623",
            "6; e; Angelina; true; 978"
    })
    @Disabled
    void checkGivenPoints( @ConvertWith(FlightArgumentConverter.class) Flight flight){
        for(Passenger passenger : flight.getPassengersSet()){
            milage.addMilage(passenger,flight.getDistance());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/flights_info_test_input.csv")
    void checkGivenPointsWithInputFromCSV(@ConvertWith(FlightArgumentConverter.class) Flight flight){
        for(Passenger passenger : flight.getPassengersSet()){
            milage.addMilage(passenger, flight.getDistance());
        }
    }

    @AfterAll
    void afterAll(){
        milage.calculateGivenPoints();
        int dorisPoints = milage.getPassengerPointsMap().get(new Passenger("Doris", false)).intValue();
        assertEquals(64, dorisPoints);

        int angelinaPoints = milage.getPassengerPointsMap().get(new Passenger("Angelina", true)).intValue();
        assertEquals(207, angelinaPoints);

        System.out.println(milage.getPassengerPointsMap());
    }

}
