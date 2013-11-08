package com.ece4894.test;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Test;
import com.ece4894.model.VisitorRequest;

public class DBConnectionTest {

	@Test//correct access
	public void DBTestUserExistence1(){
		String Username = "sgupta23";
		String Password = "abc8";
		
		boolean result = com.ece4894.services.StudentUserDao.isUserExistent(Username, Password);
		
		assertEquals(true, result);
	}
	
	@Test//correct access
	public void DBTestUserExistence2(){
		String Username = "akumar96";
		String Password = "abc10";
		
		boolean result = com.ece4894.services.StudentUserDao.isUserExistent(Username, Password);
		
		assertEquals(true, result);
	}
	
	@Test//wrong password
	public void DBTestUserExistence3(){
		String Username = "akumar96";
		String Password = "abc4";
		
		boolean result = com.ece4894.services.StudentUserDao.isUserExistent(Username, Password);
		
		assertEquals(false, result);
	}
	
	@Test//wrong username
	public void DBTestUserExistence4(){
		String Username = "akumar96";
		String Password = "abc1";
		
		boolean result = com.ece4894.services.StudentUserDao.isUserExistent(Username, Password);
		
		assertEquals(false, result);
	}
	
	@Test//put request for a new visitor
	public void DBTestInsertRequest1(){
		VisitorRequest request = new VisitorRequest();
		
		request.setFirstName("Kane");
		request.setLastName("Terry");
		request.setGTID(902673214L);
		request.setGuestOfGTID(902764642L);
		request.setDateTimeIn(new BigDecimal(new Long(1382430210000L).toString()));
		request.setDateTimeOut(new BigDecimal(new Long(1382430810000L).toString()));
		request.setRequestNumber(6);
		
		boolean result = com.ece4894.services.RequestDao.putRequest(request);
		
		assertEquals(true, result);
	}
	
	@Test//check that the count of tuples matches as expected
	public void DBTestCount1(){
		int count = com.ece4894.services.RequestDao.countRequest();
		assertEquals(5, count);
	}

}
