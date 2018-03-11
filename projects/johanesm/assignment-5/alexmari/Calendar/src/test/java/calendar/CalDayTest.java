package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	  public void test01()  throws Throwable  {
	    int thisMonth;
	    int thisYear;	
	    int thisDay;
	    
	    Calendar rightnow = Calendar.getInstance();
	   	//current month/year/date is today
	   	thisMonth = 2;
		thisYear = rightnow.get(Calendar.YEAR);
		thisDay = 14;
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		CalDay day = new CalDay(today);

		StringBuilder sb = new StringBuilder();
	    String todayDate = (thisMonth) + "/" + thisDay + "/" + thisYear;
        sb.append("\t --- " + todayDate + " --- \n");
	    sb.append(" --- -------- Appointments ------------ --- \n");
	   	sb.append("\n");
	   	
	   	String testString = sb.toString();
		assertEquals(thisMonth, day.getMonth());
		assertEquals(thisDay, day.getDay());
		assertEquals(thisYear, day.getYear());
		assertEquals(0, day.getSizeAppts());
		assertTrue(day.isValid());
		assertTrue(testString.equals(day.toString()));
		
		//Iterator test = null;
		// This fails
		//assertEquals(null, day.iterator());


	 }
	 
	 // TESTING THE EMPTY CASE
	 @Test
	  public void test02()  throws Throwable  {
		 CalDay day = new CalDay();
		 assertFalse(day.isValid());
		 String testString = "";
		 assertTrue(testString.equals(day.toString()));
		 
		 // ADDED ITERATOR TEST
		 assertEquals(null, day.iterator());
	 }
	 
	 // Expanded so it can work properly
	 @Test
	  public void test03()  throws Throwable  {
		 int thisMonth = 2;
		 int thisYear = 2012;
		 int thisDay = 14;
		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 CalDay day = new CalDay(today);
		 int startHour=12;	
		 int startMinute=00;
		 String title="Exam";
		 String description="This is a final exam.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          thisDay ,
		          thisMonth ,
		          thisYear ,
		          title,
		         description);
		 
		 startHour=12;	
		 startMinute=30;
		 title="Lunch";
		 description="Get some food!";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt_2 = new Appt(startHour,
		          startMinute ,
		          thisDay ,
		          thisMonth ,
		          thisYear ,
		          title,
		         description);
		 
		 startHour=7;	
		 startMinute=00;
		 title="Breakfast";
		 description="Get some food!";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt_3 = new Appt(startHour,
		          startMinute ,
		          thisDay ,
		          thisMonth ,
		          thisYear ,
		          title,
		         description);
		  
		 startHour=14;	
		 startMinute=00;
		 title="Go home";
		 description="No more school!";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt_4 = new Appt(startHour,
		          startMinute ,
		          thisDay ,
		          thisMonth ,
		          thisYear ,
		          title,
		         description);
		 
		 //test true
		 assertTrue(day.isValid());
		 
		 //check null case
		 StringBuilder sb = new StringBuilder();
		 String todayDate = (thisMonth) + "/" + thisDay + "/" + thisYear;
	     sb.append("\t --- " + todayDate + " --- \n");
		 sb.append(" --- -------- Appointments ------------ --- \n");
		 sb.append("\n");		
		 String testString = sb.toString();
		 assertTrue(testString.equals(day.toString())); 	

		 day.addAppt(appt);
		 day.addAppt(appt_2);
		 day.addAppt(appt_3);
		 day.addAppt(appt_4);

		 assertEquals(4, day.getAppts().size());
		 assertEquals(1, day.getAppts().indexOf(appt));
		 assertEquals(2, day.getAppts().indexOf(appt_2));
		 assertEquals(0, day.getAppts().indexOf(appt_3));
		 assertEquals(3, day.getAppts().indexOf(appt_4));

		 // Check there's more to the string
		 assertFalse(testString.equals(day.toString())); 	
		 
		 // check iterator isn't null
		 assertNotEquals(null, day.iterator());
		 
		 // Can't test this due to private access
		 //day.setAppts(null);
		 }
}
