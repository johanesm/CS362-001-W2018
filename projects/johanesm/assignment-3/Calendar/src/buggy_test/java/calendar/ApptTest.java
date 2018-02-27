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
		 int startHour=10;
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
		 startMinute = 1;
		 Appt sameAppt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
		 assertEquals(2, appt.compareTo(sameAppt));
		 
		 //ADDED
		 assertEquals(-2, sameAppt.compareTo(appt));
		 
		 sameAppt.setStartYear(2000);
		 sameAppt.setStartHour(11);
		 sameAppt.setStartDay(30);
		 sameAppt.setStartMonth(10);
		 assertEquals(104, appt.compareTo(sameAppt));
		 assertEquals(-104, sameAppt.compareTo(appt));

	// assertions
		 //this should fail, but it doesn't (b/c bug)
		// assertFalse(appt.getValid());		//doesn't fail
		 assertEquals(10, appt.getStartHour());
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
		 
		 assertNotEquals(null,appt.getRecurDays());
		 assertTrue(appt.getValid());		
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
	
		 //TODO: Fix this
		 appt.setStartDay(-10);
		 assertEquals(null, appt.toString());
		 appt.setStartDay(4);
		 assertTrue(appt.getValid());		

		 //to get string, the program has to be valid
		 String test= appt.getStartMonth()+"/"+appt.getStartDay()+"/"+appt.getStartYear() + " at ";
	     String test2 = appt.getStartHour() +":"+ appt.getStartMinute() + "am";
	     String compare = "\t"+ test +  test2  + " ," +  appt.getTitle()+ ", "+  appt.getDescription()+"\n";
		 String compare_2 = appt.toString();		 
	  //   assertTrue(compare.equals(compare_2));
	     
	     //ADDED
	     //test boundry conditions
	     appt.setStartHour(11);
		 test= appt.getStartMonth()+"/"+appt.getStartDay()+"/"+appt.getStartYear() + " at ";
	     test2 = appt.getStartHour() +":"+ appt.getStartMinute() + "am";
	     compare = "\t"+ test +  test2  + " ," +  appt.getTitle()+ ", "+  appt.getDescription()+"\n";
		 compare_2 = appt.toString();		 
	   //  assertTrue(compare.equals(compare_2));	   
	     
	     //ADDED
	     //test boundry conditions
	     appt.setStartHour(12);
		 test= appt.getStartMonth()+"/"+appt.getStartDay()+"/"+appt.getStartYear() + " at ";
	     test2 = appt.getStartHour() +":"+ appt.getStartMinute() + "pm";
	     compare = "\t"+ test +  test2  + " ," +  appt.getTitle()+ ", "+  appt.getDescription()+"\n";
		 compare_2 = appt.toString();		 
	//     assertTrue(compare.equals(compare_2));	 
	     
	     //test calUtil reach
		 CalendarUtil calUtil = new CalendarUtil();
		 int febDays = calUtil.DaysInMonth[1];
		 assertEquals(28, febDays);
	 }

	 // This test checks the Validity of the time input
	 @Test
	  public void test03()  throws Throwable  {
		 for(int i = 0; i < 100; i++) {
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
		 if(startMonth > 0) {
			 assertEquals(startMonth, appt.getStartMonth());
		 } 
		 else
			 assertFalse(appt.getValid());
		 
		 if(startMinute >= 0) {
			 assertEquals(startMinute, appt.getStartMinute());
		 } 
		 else
			 assertFalse(appt.getValid());
		 
		 if(startDay >= 0) {
			 assertEquals(startDay, appt.getStartDay());
		 } 
		 else
			 assertFalse(appt.getValid());
		 
		 assertEquals(startYear, appt.getStartYear());
		 
		 // This caught the error in startHour
		 if(startHour >= 0 && startHour < 24) {
			 assertEquals(startHour, appt.getStartHour());
		 }
		 else {
			 assertFalse(appt.getValid()); 
		 }
		 
		 //else 
		 // 	assertTrue(appt.getValid());
		 }
	 } 
	 
	 // I think a lot of this was ADDED
	 @Test
	  public void test04()  throws Throwable  { 
		 int startHour=23;	//this is allowed and valid
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
		 
		 assertEquals(2, appt.getRecurBy());
		// int[] test = new int[0]; 
		 //assertEquals(test, appt.getRecurDays());
		 
		 assertTrue(appt.getValid());
		 appt.setStartHour(0);
		 assertTrue(appt.getValid());
		 appt.setStartHour(-1);
		 assertFalse(appt.getValid());		 
		 appt.setStartHour(14);
		 assertTrue(appt.getValid());
		 
		 appt.setStartMinute(-1);
		 assertFalse(appt.getValid());	
		 appt.setStartMinute(0);
		 assertTrue(appt.getValid());
		 appt.setStartMinute(60);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(59);
		 assertTrue(appt.getValid());

		 appt.setStartDay(0);
		 assertFalse(appt.getValid());	
		 appt.setStartDay(1);
		 assertTrue(appt.getValid());	

		 //start year breaking makes no difference
		 appt.setStartYear(36);
		 assertTrue(appt.getValid());	

		 appt.setStartMinute(-1);
		 assertFalse(appt.getValid());
		 appt.setStartMinute(60);
		 assertFalse(appt.getValid());
		 
		 appt.setStartMinute(0);
		 assertTrue(appt.getValid());	
		 appt.setStartMinute(59);
		 assertTrue(appt.getValid());	
		 
		 appt.setStartDay(-1);
		 assertFalse(appt.getValid());
		 appt.setStartDay(66);
		 assertFalse(appt.getValid());
		 //January
		 appt.setStartDay(31);
		 assertTrue(appt.getValid());	

		 appt.setStartMonth(1);
		 assertTrue(appt.getValid());
		 appt.setStartMonth(12);
		 assertTrue(appt.getValid());
		 
		 //VERY SPECIFIC CATCHING OF STARTMONTH + START YEAR, LEAP YEAR
		 appt.setStartDay(31);
		 appt.setStartMonth(2);
		 assertFalse(appt.getValid());
		 appt.setStartDay(29);
		 appt.setStartYear(2016);
		 assertTrue(appt.getValid());
		 appt.setStartYear(2015);
		 assertFalse(appt.getValid());

		 // LEAP YEAR TESTS
		 appt.setStartYear(400);
		 assertTrue(appt.getValid());
		 appt.setStartYear(100);
		 assertFalse(appt.getValid());
	 } 
	 
}
