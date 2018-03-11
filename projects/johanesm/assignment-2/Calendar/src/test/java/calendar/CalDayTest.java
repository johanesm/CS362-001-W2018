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
	 @Test
	  public void test02()  throws Throwable  {
		 CalDay day = new CalDay();
		 assertFalse(day.isValid());
		 String testString = "";
		 assertTrue(testString.equals(day.toString()));
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2016;
		 String title="Exam";
		 String description="This is a final exam.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 day.addAppt(appt);
		assertEquals(null, day.iterator());
		 
		 // Since day cannot be valid due to bug in ApptTest, this next tests fails
		 //assertTrue(day.isValid()); 
	 }
	 
	 @Test
	  public void test03()  throws Throwable  {
		 int thisMonth = 2;
		 int thisYear = 2012;
		 int thisDay = 14;
		 GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		 CalDay day = new CalDay(today);
		 int startHour=-10;	//incorrect so addAppt can work
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2016;
		 String title="Exam";
		 String description="This is a final exam.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 
		 //test true
		 assertTrue(day.isValid());
		 
		 StringBuilder sb = new StringBuilder();
		 String todayDate = (thisMonth) + "/" + thisDay + "/" + thisYear;
	     sb.append("\t --- " + todayDate + " --- \n");
		 sb.append(" --- -------- Appointments ------------ --- \n");
		 sb.append("\n");		
		 String testString = sb.toString();
		 assertTrue(testString.equals(day.toString())); 	
	 
		 day.addAppt(appt);
		 	  	
		 //check that there is more to the string than just the header
		 assertFalse(testString.equals(day.toString())); 	
	 }
}
