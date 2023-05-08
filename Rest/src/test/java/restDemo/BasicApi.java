package restDemo;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;

public class BasicApi {

	public static void main(String[] args) throws IOException {

		//Response
		
//		{
//		    "status": "OK",
//		    "place_id": "0975b6ac6d87dff0c6f0a9cf507c3922",
//		    "scope": "APP",
//		    "reference": "701675b256c9a0240a0522b8f43de503701675b256c9a0240a0522b8f43de503",
//		    "id": "701675b256c9a0240a0522b8f43de503"
//		}
		
		// validate if add place API place API is working as expected
		
				//given-all inpu dertails
				//when-submit the api
				//then-validate the response
				
		//content of the file to string-> content of file can convert into Byte->Byte data to String
		
				RestAssured.baseURI = "http://rahulshettyacademy.com";
				
				String response = given().log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\Documents\\addplace.json"))))
				.when()
				.post("maps/api/place/add/json")
				.then()
				.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();
				
				System.out.println(response);
				
				JsonPath js = new JsonPath(response);//for passing json
				
			 String placeID=js.getString("place_id");
				System.out.println(placeID);
				
				//Add place-->update place with new adresss--> get place to validate if ne adress is present in response
				//Updateplace
				String newAdress = "Summer, winn 198";
				given()
				.log().all()
				.queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeID+"\",\r\n"
						+ "\"address\":\""+newAdress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "")
				.when()
				.put("maps/api/place/update/json")
				.then().log().all()
				.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
				
				//Getplace
				
				String getPlaceResponse =given()
				.log().all()
				.queryParam("key", "qaclick123").queryParam("place_id", placeID).when().get("maps/api/place/get/json").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
				
				JsonPath js1 =ReusableMethods.rawTojson(getPlaceResponse);// from reusable class
				
				String actualAdress = js1.getString("address");
				System.out.println(actualAdress);
				
				//testNg
			
				Assert.assertEquals(actualAdress, newAdress);
				
			
	}

}
