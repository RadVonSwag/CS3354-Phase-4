import java.util.Scanner;


public class EditBio {
	public static void main(String [] args) 
	{
	System.out.println("enter in new bio");
	Scanner myObj = new Scanner(System.in);
	String bio = myObj.nextLine();
	User u = new User();
	u.setBio(bio);
	}
}
