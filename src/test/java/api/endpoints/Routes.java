package api.endpoints;

public class Routes {


	public static String base_url="https://petstore.swagger.io/v2";


	//for user model
	//post
	public static String post_url=base_url+"/user";
	//get
	public static String get_url=base_url+"/user/{username}";
	//put
	public static String update_url=base_url+"/user/{username}";
	//delete
	public static String delete_url=base_url+"/user/{username}";

}
