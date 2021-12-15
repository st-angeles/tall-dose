package com.pluralsight.tddjunit5.airconditioning;


import com.pluralsight.tddjunit5.util.MockitoExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ThermometerTest {

    @InjectMocks
    Thermometer thermometer;

    @Mock
    Sensor sensor;

    @Test
    @DisplayName("Given the sensor is not blocked when the thermometer measures the temperature then the measurement succeeds")
    void testWorkingSensor(){
        thermometer.setTemperature(25.0);
        when(sensor.isBlocked()).thenReturn(false);
        assertEquals(sensor, thermometer.getSensor());
        assertEquals(25.0, thermometer.getTemperature(), 0.001);
        verify(sensor, times(1)).isBlocked();
    }

    @Test
    @DisplayName("Given the sensor is blocked when the thermometer tries to read the temperature then an exception is thrown")
    void testBlockedSensor(){
        thermometer.setTemperature(25.0);
        when(sensor.isBlocked()).thenReturn(true);
        assertEquals(sensor, thermometer.getSensor());
        assertThrows(RuntimeException.class, ()->thermometer.getTemperature());
        verify(sensor,times(1)).isBlocked();
    }
}
