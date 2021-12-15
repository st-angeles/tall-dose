package com.pluralsight.tddjunit5.airconditioning;

import com.pluralsight.tddjunit5.util.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirConditioningSystemTest {

    @InjectMocks
    AirConditioningSystem airConditioningSystem;

    @Mock
    Thermometer thermometer;

    @Test
    @DisplayName("Given the airco system , when the temperature is above threshold then the airco is open")
    void testAirConditioningSystemStarted(){
        when(thermometer.getTemperature()).thenReturn(25.0);
        airConditioningSystem.setTemperatureThreshold(24.0);
        airConditioningSystem.checkAirconditioningSystem();
        assertTrue(airConditioningSystem.isOpen());
        verify(thermometer,times(1)).getTemperature();
    }

    @Test
    @DisplayName("Given the airco system, when the temperature is below threshold then the airco is closed")
    void testAirConditioningSystemStopped(){
        when(thermometer.getTemperature()).thenReturn(23.0);
        airConditioningSystem.setTemperatureThreshold(24.0);
        airConditioningSystem.checkAirconditioningSystem();
        assertFalse(airConditioningSystem.isOpen());
        verify(thermometer,times(1)).getTemperature();
    }
}
