package com.pluralsight.tddjunit5.airport;

import java.util.*;

public abstract class Flight {

	private int distance;
    private String id;
	protected Set<Passenger> passengersSet = new HashSet<Passenger>();

	public Flight(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Set<Passenger> getPassengersSet(){
		return Collections.unmodifiableSet(passengersSet);
	}

	public abstract boolean addPassenger(Passenger passenger);

	public abstract boolean removePassenger(Passenger passenger);

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
