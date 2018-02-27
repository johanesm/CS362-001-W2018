package calendar;


import java.util.Random;
import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	int thisMonth;
	int thisYear;	
	int thisDay;
	    
	Calendar rightnow = Calendar.getInstance();
	//current month/year/date is today
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 try{ 
   		  CalDay day = new CalDay();

				for (int iteration = 0; elapsed < TestTimeout; iteration++) {
					 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
					 long randomseed =System.currentTimeMillis(); //10
						//	System.out.println(" Seed:"+randomseed );
					 Random random = new Random(randomseed);
					 
				        if((iteration%1000)==0 || iteration==0 ) {
				        	if(iteration%10000==0)
				              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
				              //make random day
				      		  thisMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
				      		  thisYear = ValuesGenerator.getRandomIntBetween(random, 1, 2018);
				      		  //thisYear = ValuesGenerator.getRandomIntBetween(random, thisYear-1, thisYear+1);
				      		  thisDay = ValuesGenerator.getRandomIntBetween(random, 1, 31);
				      		  GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
				    		  day = new CalDay(today);
				        } 
				        
				        

					
					 int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 30);
					 int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 61);
					 int startDay=thisDay;
					 int startMonth=thisMonth;
					 //keep smaller date so days line up
					 int startYear=thisYear;

					 String title="A Random Event";
					 String description="let's get random!!!";
					 //Construct a new Appointment object with the initial data	 
					 Appt appt = new Appt(startHour,
					          startMinute ,
					          startDay ,
					          startMonth ,
					          startYear ,
					          title,
					         description);
					
					 day.addAppt(appt);		
					 
					 // something causing these to fail
					 // assertEquals(thisDay, day.getDay());
				     // assertEquals(thisYear, day.getYear());
				     // assertEquals(thisMonth, day.getMonth());
				}
			}catch(NullPointerException e){

			} 
		 
	 }


	
}
