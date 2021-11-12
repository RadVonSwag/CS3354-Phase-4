
/* Maxwell Lim Test Cases
*
* Unit test for simple App.
*/
public class AppTestMax {

    @Test
    /**
     * Sample test 
     */
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void showProfile() {
        User testUser = new User("testusername", "password");
        boolean result = getProfile();
        assertTrue(result);
    }

    @Test
    public void connectToGalaxy() {
        User testUser = new User("testusername", "password");

        assertTrue(testUser.verifyGalaxy("abc190238")); //test when galaxy account is valid
        assertTrue(!testUser.verifyGalaxy("aaaaaaaaaa")); //test when galazy account is not valid
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
        Boolean matchpreferences[5] = {true,false,false,true, true};
        testUser.setPreferences(matchpreferences);

        for(int x = 0; x<5, x++){
            assertTrue((testUser.getPreferences())[x]=matchpreferences[x]);
        }
    }

}