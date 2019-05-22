package nai_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		
		List<String> listatest = new ArrayList<String>();
		List<Punkt> listapkt=new ArrayList<Punkt>();
		List<Punkt> listapkt2=new ArrayList<Punkt>();
		List<Claster> listaclastrow=new ArrayList<Claster>();
		List<Double> listazakoncz=new ArrayList<Double>();
		System.out.println("Podaj k)");
        Scanner scan1 = new Scanner(System.in);
	       
        String wybor = scan1.nextLine();
       
        double k=Double.valueOf(wybor);
        
        File file1 = new File("C:\\Users\\Kamil_Strus\\Desktop\\iris/test.txt");
        Scanner testin = new Scanner(file1);

       int i=0;
	while(testin.hasNextLine())
	{
		listatest.add(testin.nextLine());
		i++;
	}
        testin.close();
		i--;
     
		
		
		
		
		//int x=0;  //test
        while(i>-1)
        {
        
        	//System.out.println(listatest.get(i));
        	
        	String testowany=listatest.get(i);
	        Punkt a=new Punkt();
  	      
           	String[] splitedtestowany = testowany.split(",");
           	
           	for(int n=0;n<splitedtestowany.length-1;n++)
           	{
           		
           		a.dodajatrybut(Double.valueOf(splitedtestowany[n]));
           		
           	}
           	a.nazwij(splitedtestowany[splitedtestowany.length-1]);
        //   	a.obliczcentroid();
        	listapkt.add(a);
        	//System.out.println(listapkt.get(x));//test
        	i--;
        //	x++;//test
        }
        
        Collections.shuffle(listapkt);
  
   
        for(int j=0;j<k;j++)
        {
      	
        	Claster c=new Claster();
        	listaclastrow.add(c);
	
        }
       
        //dodawanie do clastrow pierwsze
        int licznik =listapkt.size()-1;
        while(licznik>0)
        {
        	for(int o=0;o<k;o++)
        	{
        		
        		listaclastrow.get(o).dodajwektor(listapkt.get(licznik));
        		//listapkt.remove(listapkt.get(licznik));
        	licznik--;	
        	if(licznik==0)
        		break;
        	}

        }

        for(int jo=0;jo<k;jo++)
        {
      	
        	
        	listaclastrow.get(jo).obliczcentroid();
        	listaclastrow.get(jo).obliczsumeodleglosci();
        	listaclastrow.get(jo).obliczczystosc();
        }
        
        //test--------------------------
        for(int h=0;h<k;h++)
        {
        	System.out.println("Claster nr "+h+" "+listaclastrow.get(h));
        }
        //-------------------
        
        //pierwsze obliczenie odleglosci
       
        	double odleglosc=0;
        	for(int j=0;j<k;j++)
            {
        		for(int j1=0;j1<listaclastrow.get(j).zbior.size();j1++)
        		{
        			
        			
        	for(int j2=0;j2<listaclastrow.get(0).centroid.atestowy.size();j2++)	
			{
				odleglosc=odleglosc+((listaclastrow.get(j).zbior.get(j1).atestowy.get(j2)-listaclastrow.get(j).centroid.atestowy.get(j2))*(listaclastrow.get(j).zbior.get(j1).atestowy.get(j2)-listaclastrow.get(j).centroid.atestowy.get(j2)));
			}
        
        	
        	
        	listaclastrow.get(j).zbior.get(j1).ustawodleglosc(odleglosc);
        	listapkt2.add(listaclastrow.get(j).zbior.get(j1));
        	odleglosc=0;
        		}
        		listaclastrow.get(j).zbior.clear();	
        	
        }
        	
       
        	
        	int koniec=0;
        	while(koniec==0) {
        	
    //podmianka-------------------------------------------------------------    	
        	for(int d1=0;d1<listapkt2.size();d1++)
        		{
        		
        		double newodleglosc=0;
        	for(int d=0;d<k;d++)
            {
        		
        			
        			
        	for(int d2=0;d2<listaclastrow.get(0).centroid.atestowy.size();d2++)	
			{   if(listaclastrow.get(d).zbior.isEmpty())
			    System.out.print("");
				else
				newodleglosc=newodleglosc+((listapkt2.get(d1).atestowy.get(d2)-listaclastrow.get(d).centroid.atestowy.get(d2))*(listapkt2.get(d1).atestowy.get(d2)-listaclastrow.get(d).centroid.atestowy.get(d2)));
			}
        
        	if(newodleglosc<listapkt2.get(d1).odleglosc)
        	{	
        		for(int ops=0;ops<k;ops++)
               {    
        			
               if(listaclastrow.get(ops)!=listaclastrow.get(d)) 
        			{	
        			listaclastrow.get(ops).zbior.remove(listapkt2.get(d1));
        			}
        	   
               }
        		listapkt2.get(d1).odleglosc=newodleglosc;
        		listaclastrow.get(d).zbior.add(listapkt2.get(d1));
        		newodleglosc=0; 	
        	}
        		else
        	{
        		if(newodleglosc==listapkt2.get(d1).odleglosc)
        		{	
        		for(int ops1=0;ops1<k;ops1++)
                {   
        			
        		 if(listaclastrow.get(ops1)!=listaclastrow.get(d))
        			{
        			   listaclastrow.get(ops1).zbior.remove(listapkt2.get(d1));
        			}
        		}
        		listaclastrow.get(d).zbior.add(listapkt2.get(d1));
        		newodleglosc=0;
        		}
        	}
        	newodleglosc=0;
        	
        	
        }
        	
        }	
        	
        //	System.out.println("#################################################################################################################################");
        /*
        	for(int ha1=0;ha1<listapkt2.size();ha1++)
    		{
        	System.out.println(listapkt2.get(ha1));
    		}
        	
        */
       
        	 for(int ch1=0;ch1<k;ch1++)
             {
        		listazakoncz.add(listaclastrow.get(ch1).sumaodleglosci);
             }
        	
        	  for(int lol=0;lol<k;lol++)
              {
            	
              	
              	listaclastrow.get(lol).obliczcentroid();
              	listaclastrow.get(lol).obliczsumeodleglosci();
              	listaclastrow.get(lol).obliczczystosc();
              }
       
        
        
        //test--------------------------
        	  double sumawszystkich=0;
        	System.out.println("********************************************************************************************************************");
        for(int h1=0;h1<k;h1++)
        {
        	System.out.println("Claster nr "+h1+" "+listaclastrow.get(h1));
        	sumawszystkich=sumawszystkich+listaclastrow.get(h1).sumaodleglosci;
        }
        System.out.println("Suma wszystkich= "+sumawszystkich);
   //----------------------------     
        int	zakoncz=0;
  	  for(int ch=0;ch<k;ch++)
        {
  		  if(listaclastrow.get(ch).sumaodleglosci==listazakoncz.get(ch))
   			 zakoncz++;
        }
  	  
  if(zakoncz==k)
  {
  	System.out.println("Dziêkuje za skorzystanie z programu :)");
koniec=1;
  }
  else
  {
	  for(int jod=0;jod<k;jod++)
      {
		  listazakoncz.clear();
		  listaclastrow.get(jod).zbior.clear();	
      }
  }
        
        //-------------------
        
       /* 
        System.out.println("Wykonac kolejna iteracje?");
		  Scanner scan8 = new Scanner(System.in);
	       
	        String powtorzyc = scan8.nextLine();	
      	
	      Pattern pattern2 = Pattern.compile("nie");
	        Matcher matcher2 = pattern2.matcher(powtorzyc);
	        boolean decyzjapowtorzyc = matcher2.matches();
	        
	        if(decyzjapowtorzyc)      
	  	  {
	        	System.out.println("Dziêkuje za skorzystanie z programu :)");
	  	koniec=1;
	  	  }
	        else
	        {
	      	  for(int jod=0;jod<k;jod++)
	            {
	      		  listaclastrow.get(jod).zbior.clear();	
	            }
	        }
        
       */ 
        
        	}
        
        
		
	}
	
}
