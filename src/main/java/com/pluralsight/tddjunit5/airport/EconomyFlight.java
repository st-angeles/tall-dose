package com.pluralsight.tddjunit5.airport;

public class EconomyFlight extends Flight {
    public EconomyFlight(String id){
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger){
        return passengersSet.add(passenger);
    }

    @Override
    public boolean removePassenger(Passenger passenger){
        if( ! passenger.isVip()){
            return passengersSet.remove(passenger);
        }
        return false;
    }
}
