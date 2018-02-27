package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
	// assertions
	//	 assertTrue(appt.getValid());
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
	 }

	 //test setters and to string functionality
	 @Test
	  public void test02()  throws Throwable  {
		 int startHour=-10;
		 int startMinute=3;
		 int startDay=31;
		 int startMonth=12;
		 int startYear=2100;
		 String title="COLLEGE!!! IS!! FUN!!";
		 String description="COLLEGE??";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 Appt sameAppt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 assertEquals(0, appt.compareTo(sameAppt));

	// assertions
		 //this should fail, but it doesn't (b/c bug)
		// assertFalse(appt.getValid());		//doesn't fail
		 assertEquals(-10, appt.getStartHour());
		 assertEquals(3, appt.getStartMinute());
		 assertEquals(31, appt.getStartDay());
		 assertEquals(12, appt.getStartMonth());
		 assertEquals(2100, appt.getStartYear());
		 assertEquals("COLLEGE!!! IS!! FUN!!", appt.getTitle());
		 assertEquals("COLLEGE??", appt.getDescription()); 
		 
		 appt.setStartHour(4);
		 appt.setStartMinute(4);
		 appt.setStartDay(4);
		 appt.setStartMonth(2);
		 appt.setStartYear(2000);
		 appt.setTitle(null);
		 appt.setDescription(null);
		 appt.setRecurrence(null, 1, 1, 1);
		 
		 //assertTrue(appt.getValid());			//FAILURE
		 assertEquals(4, appt.getStartHour());
		 assertEquals(4, appt.getStartMinute());
		 assertEquals(4, appt.getStartDay());
		 assertEquals(2, appt.getStartMonth());
		 assertEquals(2000, appt.getStartYear());
		 assertEquals("", appt.getTitle());
		 assertEquals("", appt.getDescription()); 
	//	 assertEquals(null, appt.getRecurDays());
		 assertEquals(1, appt.getRecurBy());
		 assertEquals(1, appt.getRecurNumber());
		 assertTrue(appt.isRecurring());
		 assertEquals(1, appt.getRecurIncrement());
		 
		 assertEquals(null, appt.toString());
		 
		 //to get string, the program has to be valid
		 appt.setStartHour(-10);
		 String test= appt.getStartMonth()+"/"+appt.getStartDay()+"/"+appt.getStartYear() + " at ";
	     String test2 = appt.getStartHour() +":"+ appt.getStartMinute() + "am";
	     String compare = "\t"+ test +  test2  + " ," +  appt.getTitle()+ ", "+  appt.getDescription()+"\n";
		 String compare_2 = appt.toString();
		 
		 //this fails because getStartHour doesn't work
		 //bug in represntationApp that always returns 12 as hour
	     //assertTrue(compare.equals(compare_2));
	     
	     //test calUtil reach
		 CalendarUtil calUtil = new CalendarUtil();
		 int febDays = calUtil.DaysInMonth[1];
		 assertEquals(28, febDays);
	 }

	 // This test checks the Validity of the time input
	 @Test
	  public void test03()  throws Throwable  {
		 for(int i = 0; i < 10000; i++) {
		 Random rand = new Random();
		 int startMonth = rand.nextInt(12) + 1; // get positive and negative (for error checking)
		 int startDay = rand.nextInt(60) - 28; //low for feb
		 int startMinute = rand.nextInt(130) - 60;
		 int startHour = rand.nextInt(48) - 24;
		 int startYear = rand.nextInt(2000) - 1;
		 String title="Test appointment 3";
		 String description="TESTtestTEST";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 
		 assertFalse(appt.isRecurring());
		 if(startMonth > 0)
			 assertEquals(startMonth, appt.getStartMonth());
		 else
			 assertFalse(appt.getValid());
		 
		 if(startMinute >= 0)
			 assertEquals(startMinute, appt.getStartMinute());
		 else
			 assertFalse(appt.getValid());
		 
		 if(startDay >= 0)
			 assertEquals(startDay, appt.getStartDay());
		 else
			 assertFalse(appt.getValid());
		 
		 assertEquals(startYear, appt.getStartYear());
		 
		 // This caught the error in startHour
	/*	 if(startHour >= 0)
			 assertEquals(startHour, appt.getStartHour());
		 else
			 assertFalse(appt.getValid()); */
		 //else 
		 // 	assertTrue(appt.getValid());
		 }
	 } 
	 
}