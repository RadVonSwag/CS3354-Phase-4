import java.util.Scanner;
import User.java;

public class EditBio {
	public static void main(String [] args) 
	{
	System.out.println("enter in new bio");
	Scanner myObj = new Scanner(System.in);
	String bio = myObj.nextLine();
	User.bio = bio;
	
	}
}
