package com.pluralsight.tddjunit5.airport;

import java.util.Objects;

public class Passenger {

	private String name;
	private boolean vip;

	public Passenger(String name, boolean vip) {
		this.name = name;
		this.vip = vip;
	}

	public String getName() {
		return name;
	}

	public boolean isVip() {
		return vip;
	}

	@Override
	public boolean equals(Object o){
		if( o == null || o.getClass() != getClass()){
			return false;
		}
		if( o == this){
			return true;
		}
		return Objects.equals(name, ((Passenger) o).getName());
	}

	@Override
	public int hashCode(){
		return Objects.hash(name);
	}

}
