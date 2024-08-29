package api.test;

import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;

    User userpayloads;

    public Logger logger;

	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userpayloads=new User();

		userpayloads.setId(faker.idNumber().hashCode());
		userpayloads.setUsername(faker.name().username());
		userpayloads.setFirstName(faker.name().firstName());
		userpayloads.setLastName(faker.name().lastName());
		userpayloads.setEmail(faker.internet().safeEmailAddress());
		userpayloads.setPassword(null);
		userpayloads.setPassword(faker.internet().password(5,10));
		userpayloads.setPhone(faker.phoneNumber().cellPhone());


		//logs
		//logger=LogManager.getLogger(this.getClass());

	}

	@Test(priority=1)
	public void testPostUser()

	{

		logger.info("create method start........");
		Response response=UserEndPoints.createUser(userpayloads);
		response.then().statusCode(200);

		//Assert.assertNotEquals(response.getStatusCode(), 200);

		logger.info("create method start........");
	}


	  @Test(priority =2) public void testGetUser() {
		  Response response=UserEndPoints.readUser(this.userpayloads.getUsername());
	  response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(),200);
	  }

	  @Test(priority=3)
	  public void testUserUpdateByName()
	  {
		  //change fn,ln,eml
		    userpayloads.setFirstName(faker.name().firstName());
			userpayloads.setLastName(faker.name().lastName());
			userpayloads.setEmail(faker.internet().safeEmailAddress());

		  Response response=UserEndPoints.updateUser(this.userpayloads.getUsername(),userpayloads);
			response.then().log().body();//statusCode(200);

			//after updateing data
			 Response responseafterupdate=UserEndPoints.readUser(this.userpayloads.getUsername());
			 responseafterupdate.then().log().body().statusCode(200);


	  }
	  @Test(priority=4)
	  public void testDeleteByUserName()
	  {
		  Response response=UserEndPoints.deleteUser(this.userpayloads.getUsername());
		  response.then().statusCode(200);
	  }

}
