import java.io.File;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
        User expectedUser = new User("test_user", password);
        UserOperations.CreateNewUser(expectedUser);
        User actualUser = UserOperations.AuthenticateUser("test_user", "password");
        assertEquals(expectedUser.getUID(), actualUser.getUID());
        System.out.println(actualUser);
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
        assertFalse(SearchFunction.ListUserFilters().isEmpty());
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
        assertTrue(true);
        try {
            file.createNewFile();
        }catch(Exception e){e.printStackTrace();}

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
    public void uploadPictureTest(){
        Picture ourPic = new Picture(1);
        Picture testPic = new Picture(1);
        assertTrue(ourPic.equalsPic(testPic));
    }

    @Test
    public void acceptMatchesTest(){
        String password ="password";
        User user = new User("matchusername", password);
        User match = new User("testusername2", password);

        user.addMatch(match);

        for(Object x: user.getMatches()){
            assertTrue(user.matchUser((User)x));
        }

    }
    @Test
    public void rejectMatchesTest(){
        String password ="password";
        User user = new User("matchusername", password);
        User match = new User("testusername2", password);

        user.addMatch(match);

        for(Object x: user.getMatches()){
            assertFalse(user.rejectUser((User)x));
        }
    }
    @Test
    public void deleteProfilesTest(){
        String password = UserOperations.GetPasswordHash("password");
        User expectedUser = new User("testusername", password);
        UserOperations.CreateNewUser(expectedUser);

        assertTrue(expectedUser.deleteProfile());



    }


    @Test
    public void showProfile() {
        User testUser = new User("testusername", "password");
        boolean result = testUser.getProfile();
        assertTrue(result);
    }

    @Test
    public void connectToGalaxy() {
        User testUser = new User("testusername", "password");

        assertTrue(testUser.verifyGalaxy("abc190238")); //test when galaxy account is valid
        assertFalse(!testUser.verifyGalaxy("aaaaaaaaaa")); //test when galazy account is not valid
    }

    @Test
    public void deactivateAccount() {
        User testUser = new User("test", "password"); //create user object
        boolean result = testUser.deactivateAccount();
        assertTrue(result);

    }

    @Test
    public void defineMatchPreferences() {
        String password = UserOperations.GetPasswordHash("password");
        User testUser = new User("testusername", password);
        Boolean[] matchpreferences = new Boolean[]{true,false,false,true, true};
        testUser.setPreferences(matchpreferences);

        for(int x = 0; x<5; x++){
            assertTrue((testUser.getPreferences()[x])==matchpreferences[x]);
        }
    }
}
