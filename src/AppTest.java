import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

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


    @Test
    public void searchForUserTest(){

        //generate some users
        String p = "usernames";
        ArrayList<User> users = new ArrayList<User>();
        for(int i=0;i<p.length();i++) {
            String password = UserOperations.GetPasswordHash("password");
            User user = new User(p.charAt(i)+""+i, password);
            users.add(user);
            UserOperations.CreateNewUser(user);
        }
        //our expected users
        ArrayList<User> expectedUsers = new ArrayList<>();
        for(User x: users){
            if(x.getUserName().contains("s")){
                expectedUsers.add(x);
            }
        }

        ArrayList<User> actualUsers=new ArrayList<User>();
        for(int i=0;i<p.length();i++){
            User actualUser = UserOperations.AuthenticateUser(p.charAt(i)+""+i, "password");
            actualUsers.add(actualUser);
        }
        //search for users
        ArrayList<User> expectedActualUsers = new ArrayList<>();
        for(User x: actualUsers){
            if(x!=null&&x.getUserName().contains("s")){
                expectedActualUsers.add(x);
            }
        }

        boolean passed=true;
        for(User x: expectedUsers){
            passed=false;
            for(User compare:expectedActualUsers){
                if(compare.getUID() == x.getUID()){
                    passed=true;
                }
            }

        }

        for(int i=0;i<p.length();i++){
            UserOperations.RemoveUser(p.charAt(i)+""+i);
        }

        assertTrue(passed);
    }

    @Test


}
