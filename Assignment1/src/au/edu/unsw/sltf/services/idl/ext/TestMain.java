package au.edu.unsw.sltf.services.idl.ext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class TestMain {

	public TestMain() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub 09-JUL-2001,07:24:26.463
	     Calendar rightNow = Calendar.getInstance();
	     Calendar test = Calendar.getInstance();
	     SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.mmm");
	     try {
			test.setTime(sdf.parse("09-JUL-2001 07:24:26.463"));
			System.out.println(rightNow.after(test));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     //System.out.println(rightNow.after(mydate));
	     
	     System.out.println(rightNow.getTime());
	     String eventSetID= RandomStringUtils.randomAscii(10)+".csv";
	      eventSetID= RandomStringUtils.randomAlphanumeric(15);
	     System.out.println(eventSetID);
	     

	}

}
