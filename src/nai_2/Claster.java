package nai_2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Claster {

	List<Punkt> zbior = new ArrayList<Punkt>();
	Punkt centroid;
	double sumaodleglosci;
	
	
	double versic;
	double setosac;
	double viginicac;
	
	public void obliczcentroid()
	{
		Punkt asd=new Punkt();
		if(zbior.size()==0)
			centroid=null;
		else
		{
		double[]count = new double [zbior.get(0).atestowy.size()] ;

		for(int i=0;i<zbior.size();i++)
		{
						
			for(int j=0;j<zbior.get(i).atestowy.size();j++)	
		{
				
		count[j]=count[j]+zbior.get(i).atestowy.get(j);
		
		}
	
		}
		
	for(int j=0;j<zbior.get(0).atestowy.size();j++)	
		{
		asd.dodajatrybut(count[j]/zbior.size());
		}
		
		centroid=asd;
		}
	}
	
	
	public void obliczsumeodleglosci()
	{
		double suma=0;
		for(int i=0;i<zbior.size();i++)
		{
			double odleglosc=0;
			
			for(int j=0;j<centroid.atestowy.size();j++)	
			{
				odleglosc=odleglosc+((zbior.get(i).atestowy.get(j)-centroid.atestowy.get(j))*(zbior.get(i).atestowy.get(j)-centroid.atestowy.get(j)));
			}
			suma=suma+odleglosc;
			
		}
		sumaodleglosci=suma;
	}
	
	public void obliczczystosc()
	{
		double versi=0;
		double setosa=0;
		double viginica=0;
		for(int i=0;i<zbior.size();i++)
		{
		 Pattern pattern1 = Pattern.compile("Iris-versicolor");
	        Matcher matcher1 = pattern1.matcher(zbior.get(i).nazwa);
	        boolean a = matcher1.matches();
	        if(a)
			{
			versi++;
			}
	        
	        Pattern pattern2 = Pattern.compile("Iris-virginica");
	        Matcher matcher2 = pattern2.matcher(zbior.get(i).nazwa);
	        boolean b = matcher2.matches();
	        if(b)
			{
			viginica++;
			}
	        
	        Pattern pattern3 = Pattern.compile("Iris-setosa");
	        Matcher matcher3 = pattern3.matcher(zbior.get(i).nazwa);
	        boolean c = matcher3.matches();
	        if(c)
			{
			setosa++;
			}
	    
		}
		versic=versi;
		setosac=setosa;
		viginicac=viginica;
		
	}
	
	public void dodajwektor(Punkt a)
	{
		zbior.add(a);
	}

	
	
	@Override
	public String toString() {
		DecimalFormat numberFormat = new DecimalFormat("00.00");
		return /*""+zbior+*/" sumaodleglosci="+ numberFormat.format(sumaodleglosci)+"        czystosc:  Setosa "+numberFormat.format(setosac/zbior.size()*100)+" Virginica "+numberFormat.format(viginicac/zbior.size()*100)+" Versicolor"+numberFormat.format(versic/zbior.size()*100)  ;
	}

}
