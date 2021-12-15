package com.pluralsight.tddjunit5.database;

import com.pluralsight.tddjunit5.airport.Flight;
import com.pluralsight.tddjunit5.util.DatabaseUtil;
import com.pluralsight.tddjunit5.util.MockitoExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import javax.xml.crypto.Data;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightStatisticsTest {

    @Spy
    private Database database;

    private List<List<String>> queriedData;
    private List<Flight> flightsList;

    @BeforeEach
    void before(){
        queriedData = new ArrayList();

        List<String> row1 = Arrays.asList("1","e","Doris","false","349");
        List<String> row2 = Arrays.asList("2","b","Angelina","true","278");
        List<String> row3 = Arrays.asList("3","e","Doris","false","319");
        List<String> row4 = Arrays.asList("4","p","Angelina","true","817");
        List<String> row5 = Arrays.asList("5","e","Doris","false","623");
        List<String> row6 = Arrays.asList("6","e","Angelina","true","978");

        queriedData.add(row1);
        queriedData.add(row2);
        queriedData.add(row3);
        queriedData.add(row4);
        queriedData.add(row5);
        queriedData.add(row6);

        flightsList = DatabaseUtil.buildFlightsList(queriedData);

    }

    @Test
    void testQueriedData(){
        when(database.queryFlightsDatabase()).thenReturn(queriedData);
        assertEquals(6, database.queryFlightsDatabase().size());
        assertEquals(6, flightsList.size());
        assertEquals(560.66, database.averageDistance(flightsList), 0.01);
        assertEquals(278, database.minDistance(flightsList));
        assertEquals(978, database.maxDistance(flightsList));
    }
}
