public class CreateNewUser {
    //for testing purposes
    //Creating a user for myself in the Users.dat file.


    public static void execute()
    {
        try {
            create("Rad", "password", "Andrew", "Estes");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void create(String username, String password, String firstname, String lastname) throws Exception
    {
        
        User newUser = new User(username, UserOperations.GetPasswordHash(password));
        newUser.setGender(0);
        newUser.setGenderPreference(1);
        newUser.setFirstName(firstname);
        newUser.setLastName(lastname);
        UserOperations.CreateNewUser(newUser);

    }


    
}
