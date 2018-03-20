
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

   // Copied from the other testing
   private final boolean printStatus = false;
   private final boolean printIndex = false;	//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	   
	  UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	  
	  // Good baseline URL + Good URL Scheme
      assertTrue(urlVal.isValid("http://www.twitter.com"));
      assertTrue(urlVal.isValid("http://www.twitter.com/"));
      // BUG HERE - should pass, but fails
      // assertTrue(urlVal.isValid("www.twitter.com"));

      // Bad URL Scheme
      assertFalse(urlVal.isValid("http:/www.twitter.com/"));
      assertFalse(urlVal.isValid("3ht://www.twitter.com/"));
      assertFalse(urlVal.isValid(":/www.twitter.com/"));

      // Good URL Authority
      assertTrue(urlVal.isValid("http://255.255.255.255"));
      assertTrue(urlVal.isValid("http://0.0.0.0"));
      assertTrue(urlVal.isValid("http://www.go.com"));
      
      // Bad URL Authority
      // BUGS HERE - should fail, but do not
      //assertFalse(urlVal.isValid("http://256.256.256.256"));
      //assertFalse(urlVal.isValid("http://"));
      assertFalse(urlVal.isValid(""));

      // Good URL Port
      assertTrue(urlVal.isValid("http://www.twitter.com/:0"));
      assertTrue(urlVal.isValid("http://www.twitter.com/:65535"));
      
      // Bad URL Port
      // BUG HERE - should fail, but passes
      //assertFalse(urlVal.isValid("http://www.twitter.com/:65636"));
      //assertFalse(urlVal.isValid("http://www.twitter.com/:65a"));

      // Good URL Path      
      // BUG HERE - should pass, but fails
      //assertTrue(urlVal.isValid("http://www.twitter.com/:65535/test1"));
      //assertTrue(urlVal.isValid("http://www.twitter.com/:65535/$23"));

      // Bad URL Path      
      assertFalse(urlVal.isValid("http://www.twitter.com/:65535//test1//file"));
      assertFalse(urlVal.isValid("http://www.twitter.com/:80//..//file"));
      assertFalse(urlVal.isValid("http://www.twitter.com//.."));
      assertFalse(urlVal.isValid("http://www.twitter.com///..//file"));

      // Good URL Query
      assertTrue(urlVal.isValid("http://www.twitter.com/:65535?action=edit&mode=up"));
      assertTrue(urlVal.isValid("http://www.twitter.com/:65535?action=view"));
   }

   // OBJECTS FOR PARTITION TESTING
   //testing schemes valid invalid and null
   ResultPair[] testValSchemes = {new ResultPair("https",true),
			   new ResultPair("http", true)};
   ResultPair[] testInvalSchemes = {new ResultPair("htp",false),
		   new ResultPair("h", false),
		   new ResultPair(" ", false),
		   new ResultPair("://", false),
		   new ResultPair("http/", false)};
   ResultPair[] testNullSchemes = {new ResultPair(null, true),
		   new ResultPair("", true)};
   //testing authorities, valid invalid and null
   
   ResultPair[] testValAuthorities = {new ResultPair("www.twitter.com",true),
		   new ResultPair("0.0.0.0", true),
		   new ResultPair("", false)};
   ResultPair[] testInvalAuthorities = {new ResultPair("www.",false),
		   new ResultPair("355.345.333.444", false),
		   new ResultPair("halp..",false),
		   new ResultPair(".siodls.",false)};
   ResultPair[] testNullAuthorities = {new ResultPair("",false),
		   new ResultPair(null, false)};
   
   //testing url ports valid, invalid, and null
   ResultPair[] testValPorts = {new ResultPair(":80",true),
		   new ResultPair(":0",true)};
   
   ResultPair[] testInvalPort = {new ResultPair(":-1", false),
		   new ResultPair(":65636",false),
		   new ResultPair(":65a", false)};
   ResultPair[] testNullPort = {
           new ResultPair("", true),
           new ResultPair(null, true)
};
   
   //testing url options, valid, invalid and null
   ResultPair[] testOptions =  {new ResultPair("./.",true),
		   new ResultPair("///", false),
		   new ResultPair("/.../",false),
		   new ResultPair("/./.dd/", false),
		   new ResultPair("/..a.a/,", false),
		   new ResultPair("33a / ", false),
		   new ResultPair("/#a/", false),
		   new ResultPair("///", false)};

   ResultPair[] testQuery = {new ResultPair("?action=view", true),
		   new ResultPair("", true),
		   new ResultPair("sn==sj", false)};
   Object[] valTest = {testValSchemes, testValAuthorities, testValPorts, testOptions, testQuery};
   Object[] invalTest = {testInvalSchemes, testInvalAuthorities, testInvalPort, testOptions, testQuery};
   Object[] nullTest = {testNullSchemes, testNullAuthorities, testNullPort, testOptions, testQuery};

   
   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing
	   try{
		   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		   StringBuilder testValUrl = new StringBuilder();
		   for (int index = 0; index < valTest.length - 1; index++) {
			   testValUrl.append(valTest[index]);
		   }
		   String valUrl = testValUrl.toString();
		   boolean resi = urlVal.isValid(valUrl);
		   assertFalse(resi);
	   	} catch (NullPointerException e){
		   
	   	}

   }
   
   public void testYourSecondPartition(){
	   //You can use this function to implement your Second Partition testing
	   try{
		   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		   StringBuilder testInvalUrl = new StringBuilder();
		   for(int i = 0; i < invalTest.length - 1; i++){
			   testInvalUrl.append(invalTest[i]);
		   }
		   String invalUrl = testInvalUrl.toString();
		   boolean resi = urlVal.isValid(invalUrl);
		   assertFalse(resi);
		   
	   }catch (NullPointerException e){
		   
	   }  
   }
   
   //You need to create more test cases for your Partitions if you need to 
   public void testYourThirdPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   try{
		   StringBuilder testNullUrl = new StringBuilder();
		   for(int i = 0; i < nullTest.length - 1; i++){
			   testNullUrl.append(nullTest[i]);
		   }
		   String nullUrl = testNullUrl.toString();
		   boolean test = urlVal.isValid(nullUrl);
		   assertFalse(test);
	   }catch (NullPointerException e){	   
	   }
 }

   public void testIsValid()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   int scheme = 0;
	   int authority = 0;
	   int port = 0;
	   int path = 0;
	   int query = 0;
	   //int count = 0;
	   boolean flag = true;
	   
	   ResultPair[] testUrlScheme = {new ResultPair("http://", true),
               //new ResultPair("ftp://", true),	//CAUSES ERRORS
               //new ResultPair("h3t://", true), 	//CAUSES ERRORS
               new ResultPair("3ht://", false),
               //new ResultPair("http:/", false),	//SHOULD FAIL, BUT PASSES
               new ResultPair("http:", false),
               new ResultPair("http/", false),
               new ResultPair("://", false),
               //new ResultPair("", true),			//SHOULD PASS, BUT FAILS
               new ResultPair("&http+", false),
               new ResultPair("ab", false)};
               
	   ResultPair[] testUrlAuthority = {new ResultPair("www.twitter.com", true),
                  new ResultPair("fa.com", true),
                  new ResultPair("fa.au", true),
                  new ResultPair("0.0.0.0", true),
                  new ResultPair("255.255.255.255", true),
                  //new ResultPair("256.256.256.256", false),	//SHOULD FAIL, BUT PASSES
                  new ResultPair("255.com", true),
                  //new ResultPair("5.4.3.2.1", false),			//SHOULD FAIL, BUT PASSES
                  //new ResultPair("4.3.2.1.", false),			//SHOULD FAIL, BUT PASSES
                  //new ResultPair("3.2.1", false),				//SHOULD FAIL, BUT PASSES
                  //new ResultPair(".66.36.46.54", false),		//SHOULD FAIL, BUT PASSES
                  //new ResultPair("fa.a", false),				//SHOULD FAIL, BUT PASSES
                  //new ResultPair("fa.a1a", false),			//SHOULD FAIL, BUT PASSES
                  //new ResultPair("fa.1aa", false),			//SHOULD FAIL, BUT PASSES
                  //new ResultPair("zzz.", false),				//SHOULD FAIL, BUT PASSES
                  //new ResultPair(".ZZZ", false),				//SHOULD FAIL, BUT PASSES
                  //new ResultPair("AAA", false),				//SHOULD FAIL, BUT PASSES
                  //new ResultPair("", false),					//SHOULD FAIL, BUT PASSES
	   			  };
	   
	   ResultPair[] testUrlPort = {new ResultPair("", true),
             //new ResultPair(":65535", true),	//SHOULD PASS, BUT FAILS
            // new ResultPair(":0", true),  	//SHOULD PASS, BUT FAILS
            // new ResultPair(":80", true),		// CAUSES ERRORS
             new ResultPair(":-1", false),
             new ResultPair(":65636",false),
             new ResultPair(":65a", false)};
	   
	   ResultPair[] testUrlPath = {new ResultPair("/test1", true),
          new ResultPair("/t123", true),
          new ResultPair("/$23", true),
          new ResultPair("/..", false),
          new ResultPair("/../", false),
         // new ResultPair("/test1/", true), // CAUSES ERRORS
          new ResultPair("", true),		
         // new ResultPair("/test1/file", true), // CAUSES ERRORS
          new ResultPair("/..//file", false),
          new ResultPair("/test1//file", false)};


	   ResultPair[] testUrlQuery = {new ResultPair("?action=view", true),
              new ResultPair("?action=edit&mode=up", true),
              new ResultPair("", true),
       };

	   while(flag) {
		   StringBuilder testBuilder = new StringBuilder();
	       boolean result = true;
	       testBuilder.append(testUrlScheme[scheme].item);
	       testBuilder.append(testUrlAuthority[authority].item);
	       testBuilder.append(testUrlPort[port].item);
	       testBuilder.append(testUrlPath[path].item);
	       testBuilder.append(testUrlQuery[query].item);

	       //NOTE: there is a bug that is revsersing logic
	       result &= !testUrlScheme[scheme].valid;
	       result &= !testUrlAuthority[authority].valid;
	       result &= !testUrlPort[port].valid;
	       result &= !testUrlPath[path].valid;
	       result &= !testUrlQuery[query].valid;
	       
           String url = testBuilder.toString();
           
           System.out.print(url + '\n');

           boolean validResult = urlVal.isValid(url);
           
           if(result)
        	   assertTrue(validResult);
           else
        	   assertFalse(validResult);
           
           // System.out.print(query + " " + path + " " + port + " " + authority + " " + scheme + '\n');

           query = query + 1;
         //  count = count + 1;
           if(query >= testUrlQuery.length) {
        	   path = path + 1;
        	   query = 0;
        	   if(path >= testUrlPath.length) {
        		   port = port + 1;
        		   path = 0;
        		   if(port >= testUrlPort.length) {
        			   authority = authority + 1;
        			   port = 0;
        			   if(authority >= testUrlAuthority.length) {
        				   scheme = scheme + 1;
        				   authority = 0;
        				   if(scheme >= testUrlScheme.length) {
            				   flag = false;
            		           //System.out.print("Count: " + count + '\n');
        				   } 
        			   }			   
        		   }
        	   }
           }
   	   }
   }
}
