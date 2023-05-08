package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dynamicjson {
	
	@Test(dataProvider="booksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI ="http://216.10.245.166";
		
		String response= given().log().all().header("Content-Type","text/plain").body(Payload.Addbook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReusableMethods.rawTojson(response);
		
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="booksData")
	public Object[][] getData() {
		
	return	new Object[][] {{"fdgj","1212"},{"fghj","4545"},{"7858"}};
	
	}

}
