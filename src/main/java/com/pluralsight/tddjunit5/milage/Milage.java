package com.pluralsight.tddjunit5.milage;

import com.pluralsight.tddjunit5.airport.Passenger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Milage {
    public static final int VIP_BONUS_FACTOR = 10;
    public static final int NON_VIP_BONUS_FACTOR = 20;

    Map<Passenger,Integer> passengerMilageMap = new HashMap<Passenger,Integer>();
    Map<Passenger,Integer> passengerPointsMap = new HashMap<Passenger,Integer>();

    public void addMilage(Passenger passenger, int miles){
        if(passengerMilageMap.containsKey(passenger)){
            passengerMilageMap.put(passenger, passengerMilageMap.get(passenger) +
             miles);
        } else {
            passengerMilageMap.put(passenger, miles);
        }

    }

    public  Map<Passenger, Integer> getPassengerPointsMap(){
        return Collections.unmodifiableMap(passengerPointsMap);
    }

    public void calculateGivenPoints(){

        for(Passenger passenger : passengerMilageMap.keySet()){
            if(passenger.isVip()){
                passengerPointsMap.put(passenger, passengerMilageMap.get(passenger) / VIP_BONUS_FACTOR);
            } else {
                passengerPointsMap.put(passenger, passengerMilageMap.get(passenger) / NON_VIP_BONUS_FACTOR);
            }
        }

    }
}

