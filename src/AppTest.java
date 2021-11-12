import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
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
    //Run the RemoveUserTest before attempting to run this test again
    @Test
    public void AddNewUserTest()
    {
        String password = UserOperations.GetPasswordHash("password");
        User expectedUser = new User("testusername", password);
        UserOperations.CreateNewUser(expectedUser);
        User actualUser = UserOperations.AuthenticateUser("testusername", "password");
        assertTrue(expectedUser.getUID() == actualUser.getUID());

        //To demonstrate that the userdata is written and read to a file
        User doesNotExist = new User("invalidusername", "password who cares?"); //User object created, however note it is not actually "created" or added to the database (file)
        User nullUser = UserOperations.AuthenticateUser("invalidusername", "password who cares?");
        assertTrue(nullUser == null);
    }

    @Test
    public void RemoveUserTest()
    {
        UserOperations.RemoveUser("testusername");
    }
}
