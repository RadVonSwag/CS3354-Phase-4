import java.util.Random;

/**
 * User class stores data for user objects
 * 
 * @author Andrew Estes 
 * @NetID ace190002
 * 
 * @param <E>
 */
public class User<E> implements Comparable<E>
{
    /**
     * Stores the username as a string for the User object.
     */
    private String username;

    /**
     * Stores the hashed password as a string for the User object.
     */
    private String hashedPassword;

    /**
     * User ID number to identify user
     */
    private int UID;
    
    /**
     * Stores the first name for the User object as a String.
     */
    private String firstName;

    /**
     * Stores the last name for the User object as String.
     */
    private String lastName;

    /**
     * An Array of strings that stores the user's interests
     */
    private String[] interests;

    /**
     * Stores gender preference of user. Should be 0, for male, 1 for female, or 2 for any
     */
    private int genderPreference = 2;

    /**
     * Gender should be 0 for male, 1 for female, 2 for other
     */
    private int gender = 2;

    /**
     * Constructor assigns values to User class's data fields.
     * @param username
     * @param hashedPassword
     * @param gender
     */
    public User(String username, String hashedPassword)
    {        
        this.username = username;
        this.hashedPassword = hashedPassword;
        
        Random random = new Random();
        UID = random.nextInt(999999);
    }

    /**
     * Changes the first name for the User object.
     * @param name
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /**
     * Returns the first name for the User object.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Changes the last name for the User object.
     * @param name
     */
    public void setLastName(String name) {
        lastName = name;
    }

    /**
     * Returns the last name for the User object.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns username for the User object.
     * @return username
     */
    public String getUserName() {
        return username;
    }

    /**
     * Returns the hashed password for the User object.
     * @return hashed password
     */
    public String getPassword() {
        return hashedPassword;
    }

    /**
     * Returns gender of user
     * @return gender
     */
    public int getGender() {
        return gender;
    }
    /**
     * Sets the gender for the user. Gender is "other" by default
     * @param preference
     * @throws Exception
     */
    public void setGender(int gender) throws Exception
    {
        if (gender < 0 || gender > 2)
        {
            throw new Exception("Invalid gender!");
        }
        this.gender = gender;
    }

    /**
     * Returns genderPreference
     * @return genderPreference
     */
    public int getGenderPreference()
    {
        return genderPreference;
    }
    /**
     * Sets the gender preference for the user. Preference is "other" by default
     * @param preference
     * @throws Exception
     */
    public void setGenderPreference(int preference) throws Exception
    {
        if (preference != 0 || preference != 1 || preference != 2)
        {
            throw new Exception("Invalid gender preference!");
        }
        genderPreference = preference;
    }

    public int getUID()
    {
        return UID;
    }

    public void setUID(int newUID)
    {
        UID = newUID;
    }

    @Override
    public int compareTo(E o) {
        // TODO Auto-generated method stub
        return 0;
    }
}