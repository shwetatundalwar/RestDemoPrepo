package restDemo;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrize());

		int count = js.getInt("courses.size()");

		System.out.println(count);

		int totalAmount = js.getInt("dashboard.purchaseAmount");

		System.out.println(totalAmount);

		String titlefirstcourse = js.get("courses[0].title");

		System.out.println(titlefirstcourse);

		for (int i = 0; i < count; i++) {

			String coursename = js.get("courses[" + i + "].title");
			int price = js.get("courses[" + i + "].price");
			System.out.println(coursename + " " + price);

			if (coursename.equalsIgnoreCase("RPA")) {

				int copies = js.get("courses[" + i + "].copies");
				System.out.println("No. of copies sold by RPA-->" + " " + copies);

			}
		}

		int sum = 0;
		for (int j = 0; j < count; j++) {
			int price1 = js.get("courses[" + j + "].price");
			int copies1 = js.get("courses[" + j + "].copies");

			sum = sum + (price1 * copies1);

		}
		
		 Assert.assertEquals(sum, totalAmount);

	}

}
