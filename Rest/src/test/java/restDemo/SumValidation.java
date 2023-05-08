package restDemo;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourse() {
		
		JsonPath js = new JsonPath(Payload.CoursePrize());
		int count =	js.getInt("courses.size()");
		int totalAmount =js.getInt("dashboard.purchaseAmount");

		 int sum=0;
			for(int i=0; i<count; i++) {
				int price1 = js.get("courses["+i+"].price");
				int copies1 = js.get("courses["+i+"].copies");
				
				 sum = sum + (price1 * copies1);
				
				}
			if(sum==totalAmount) {
				
				System.out.println(sum);
				
			}
	}

}
