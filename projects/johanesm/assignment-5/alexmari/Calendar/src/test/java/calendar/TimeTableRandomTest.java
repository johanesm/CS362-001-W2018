package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import java.util.GregorianCalendar;
import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */


public class TimeTableRandomTest {
	
	public static String RandomSelectMethod(Random random){
	    String[] methodArray = new String[] {"nullTest","deleteAppt","getApptRange"};// The list of the of methods to be tested in the Appt class

		int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
		            
	    return methodArray[n] ; // return the method name 
	    }
	//CHANGED: 500 to 5
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=1;
	int thisMonth = 6;
	int thisYear = 6;	
	int thisDay = 6;
	int thisMonth2 = 7;
	int thisYear2 = 7;	
	int thisDay2 = 7;
	Calendar rightnow = Calendar.getInstance();
	//current month/year/date is today	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {

		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 System.out.println("Start testing...");
		 try{ 
  		  GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
  		  GregorianCalendar anotherDay = new GregorianCalendar(thisYear2,thisMonth2,thisDay2);
			 int startHour=12;
			 int startMinute=30;
			 int startDay=5;
			 int startMonth=5;
			 int startYear=2015;
			 String title="A Random Event";
			 String description="let's get random!!!";
		     TimeTable tbl = new TimeTable();
		     int randAppt;
			 LinkedList<Appt> listAppts = new LinkedList<Appt>();
				for (int iteration = 0; elapsed < TestTimeout; iteration++) {

					 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
					 long randomseed =System.currentTimeMillis(); //10
					 Random random = new Random(randomseed);

				        if((iteration%100)==0) {
				              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
				              //make random day
				      		  thisMonth = ValuesGenerator.getRandomIntBetween(random, 1, 12);
				      		  thisYear = ValuesGenerator.getRandomIntBetween(random, thisYear-1, thisYear+1);
				      		  thisDay = ValuesGenerator.getRandomIntBetween(random, 1, 31);
				      		  thisMonth2 = ValuesGenerator.getRandomIntBetween(random, 1, 12);
				      		  thisYear2 = thisYear + ValuesGenerator.getRandomIntBetween(random, 1, 10);
				      		  thisDay2 = ValuesGenerator.getRandomIntBetween(random, 1, 31);
				      		  today = new GregorianCalendar(thisYear,thisMonth,thisDay);
				      		  anotherDay = new GregorianCalendar(thisYear2,thisMonth2,thisDay2);
				      		  //ALL OF THE TIME GOES HERE
				        } 
				        
						 
						 startHour=ValuesGenerator.getRandomIntBetween(random, -1, 30);
					//	 System.out.println(" Start Hour:"+startHour );

						 startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 61);
						 startDay=thisDay;
						 
						 // Start Month cannot be outside of these values -> crash
						 startMonth=thisMonth;
						 //keep smaller date so days line up
						 startYear=thisYear;

						 title=ValuesGenerator.getString(random);
						 description=ValuesGenerator.getString(random);
						   

					 //Construct a new Appointment object with the initial data	 
					 Appt appt = new Appt(startHour,
					          startMinute ,
					          startDay ,
					          startMonth ,
					          startYear ,
					          title,
					         description);
						
					 //add recurrence
					 int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
					 int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					 int recur=ApptRandomTest.RandomSelectRecur(random);
					 int recurIncrement = ValuesGenerator.RandInt(random);
					 int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					 appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
					   
				     listAppts.add(appt);
				     
						for (int i = 0; i < NUM_TESTS; i++) {
							String methodName = TimeTableRandomTest.RandomSelectMethod(random);
						//	System.out.println(" Method:"+methodName );
							   if (methodName.equals("nullTest")){
								     randAppt = ValuesGenerator.getRandomIntBetween(random, 0, listAppts.size() - 1);
								     assertEquals(null, tbl.deleteAppt(null, null));
									 assertEquals(null, tbl.deleteAppt(listAppts, null));
									 assertEquals(null, tbl.deleteAppt(null, listAppts.get(randAppt)));				   
								}
							   else if (methodName.equals("getApptRange")){
								   tbl.getApptRange(listAppts, today, anotherDay);
								}		
							   
							   //getting coverage for null recurDays
							   else if (methodName.equals("deleteAppt")) {
								   randAppt = ValuesGenerator.getRandomIntBetween(random, 0, listAppts.size() - 1);
								   tbl.deleteAppt(listAppts, listAppts.get(randAppt));
							   }
						} 
				     
				 
				}
				//intentionally fails
				 //  tbl.getApptRange(listAppts, anotherDay, today);

			}catch(NullPointerException e){ 
				
			} 
	 }


	
}
