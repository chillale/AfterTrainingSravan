package genericUtility;

import com.github.javafaker.Faker;

public class JavaFaker {
   static  Faker faker = new Faker();
	public static String createdBy() {
		return faker.name().firstName();
	}
	public static String projectName() {
		return faker.food().dish();
	}
	public static String status() {
		return "completed";
	}
	

}
