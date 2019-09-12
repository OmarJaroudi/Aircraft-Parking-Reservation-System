package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class o1 {
	
	public IntegerProperty GateNumber;
	
	// constructor
	public o1(){
		this.GateNumber = new SimpleIntegerProperty();
	}
	
	public int geto1() {
		
		return GateNumber.get();
	}
	
	public void seto1(int num) {
		this.GateNumber.set(num);
	}
	public IntegerProperty geto1num()
	{
		return GateNumber;
	}
	

}
