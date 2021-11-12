import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

/**
 * Unit test Dating App Functions.
 */
public class AppTest {

    @Test
    /**
     * Sample test :)
     */
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    //Test can only be run once. (because the user will be added and you cannot add the same user twice)
    //Run the ResetCreateProfile auxilary function before attempting to run this test more than once
    @Test
    public void CreateProfileTest()
    {
        String password = UserOperations.GetPasswordHash("password");
        User expectedUser = new User("testusername", password);
        UserOperations.CreateNewUser(expectedUser);
        User actualUser = UserOperations.AuthenticateUser("testusername", "password");
        assertTrue(expectedUser.getUID() == actualUser.getUID());

        //To demonstrate that the userdata is written and read to a file by trying to authenticate a user that does not exist.
        User doesNotExist = new User("invalidusername", "password who cares?"); //User object created, however note it is not actually "created" or added to the database (file)
        User nullUser = UserOperations.AuthenticateUser("invalidusername", "password who cares?");
        assertTrue(nullUser == null);
    }

    @Test
    public void DeleteProfileTest()
    {
       //Create new user and manually set the UID
       User expectedUser = new User("usertodelete", UserOperations.GetPasswordHash("password123"));
       expectedUser.setUID(12345);
       UserOperations.CreateNewUser(expectedUser);

       //Remove the user just created
       User deletedUser = UserOperations.RemoveUser(12345);
       assertTrue(expectedUser.getUID() == deletedUser.getUID());

    }

    @Test
    public void EditBioTest()
    {
        String newBio = "This is my new bio!";
        boolean bioChanged = true;
        assertTrue(bioChanged == UserOperations.ChangeBio(newBio));
    }
    @Test
    public void ChangeStatusTest()
    {
        assertTrue(UserOperations.ChangeStatus(1) == 1); //test online
        assertTrue(UserOperations.ChangeStatus(2) == 2); //test away
        assertTrue(UserOperations.ChangeStatus(3) == 3); //test do not disturb
        assertTrue(UserOperations.ChangeStatus(4) == 4); //test offline/invisible

    }

    @Test
    public void filterUsersTest()
    {
        SearchFunction.ApplyUserFilter("male");
        SearchFunction.ApplyUserFilter("computer science major");
        assertTrue(SearchFunction.ListUserFilters() == "male, computer science major");
    }

    //Auxilary function to find file location of Users.dat
    @Test
    public void findFileLocation()
    {
        File file = new File("Users.dat");
        System.out.println(file.getAbsolutePath());
    }
    
    //Auxilary function to reset the user data file.
    @Test
    public void ResetCreateUser()
    {
        File file = new File("Users.dat");
        file.delete();
    }
}
