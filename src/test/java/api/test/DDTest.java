package api.test;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	@Test(priority = 1,dataProvider ="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserId,String UserName,String fname,String lname,String email,String password,String phone)

	{

		User userpayload=new User();

		userpayload.setId(Integer.parseInt(UserId));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);

		Response response=UserEndPoints.createUser(userpayload);
		response.then().statusCode(200);
	}

	@Test(priority=2,dataProvider ="UserName",dataProviderClass =DataProviders.class)
	public void testdeleteByUserName(String username)
	{
		Response response=UserEndPoints.deleteUser(username);
		  response.then().statusCode(200);
	}

}
