package nai_2;

import java.util.ArrayList;
import java.util.List;

public class Punkt {

	
	List<Double> atestowy = new ArrayList<Double>();
	double odleglosc;
	String nazwa;

	public void nazwij(String xd)
	{
		nazwa=xd;
	}
	
	
	
	public void ustawodleglosc(double a)
	{
		odleglosc=a;
	}

	public void dodajatrybut(double a)
	{
		atestowy.add(a);
	}

	@Override
	public String toString() {
		
		return ""+atestowy+" "+nazwa;
		
	}
	
	
	
	
}
