import java.util.ArrayList;
import java.util.Random;

/**
 * User class stores basic user data for user objects
 * 
 * @author Andrew Estes
 * @NetID ace190002
 * 
 * @param <E>
 */
public class User<E> implements Comparable<E> {
    /**
     * Stores the username as a string for the User object.
     * stored at index 2
     */
    private String username;

    /**
     * Stores the hashed password as a string for the User object.
     * stored at index 3
     */
    private String hashedPassword;

    /**
     * User ID number to identify user
     * stored at index 6
     */
    private int UID;

    /**
     * Stores the first name for the User object as a String.
     * stored at index 0
     */
    private String firstName;

    /**
     * Stores the last name for the User object as String.
     * stored at index 1
     */
    private String lastName;

    /**
     * An Array of strings that stores the user's interests
     */
    private String[] interests;

    /**
     * Stores gender preference of user. Should be 0, for male, 1 for female, or 2
     * for any
     * stored at index 5
     */
    private int genderPreference = 2;

    /**
     * Gender should be 0 for male, 1 for female, 2 for other
     * stored at index 4
     */
    private int gender = 2;

    /**
     * Short, 250 character bio about user
     */
    private String bio;

    /**
     * 1 for online, 2, for away, 3 for do not disturb, 4 for offline/invisible
     */
    private int status;

    /**
     * Constructor initializes user object with no predefined data fields
     */
    public User() {

    }

    /**
     * Constructor assigns values to User class's data fields.
     * 
     * @param username
     * @param hashedPassword
     */
    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;

        Random random = new Random();
        UID = random.nextInt(999999);
    }

    /**
     * Changes the first name for the User object.
     * 
     * @param name
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /**
     * Returns the first name for the User object.
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Changes the last name for the User object.
     * 
     * @param name
     */
    public void setLastName(String name) {
        lastName = name;
    }

    /**
     * Returns the last name for the User object.
     * 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns username for the User object.
     * 
     * @return username
     */
    public String getUserName() {
        return username;
    }

    /**
     * Returns the hashed password for the User object.
     * 
     * @return hashed password
     */
    public String getPassword() {
        return hashedPassword;
    }

    /**
     * Returns gender of user
     * 
     * @return gender
     */
    public String getGenderString() {

        String userGender = "other";
        if (gender == 0) {
            userGender = "male";
        }
        if (gender == 1) {
            userGender = "female";
        }

        return userGender;
    }

    /**
     * Returns gender of user
     * 
     * @return gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * Sets the gender for the user. Gender is "other" by default
     * 
     * @param preference
     * @throws Exception
     */
    public void setGender(int gender) throws Exception {
        if (gender < 0 || gender > 2) {
            throw new Exception("Invalid gender!");
        }
        this.gender = gender;
    }

    /**
     * Returns genderPreference
     * 
     * @return genderPreference
     */
    public String getGenderPreferenceString() {
        String userPref = "other";
        if (genderPreference == 0) {
            userPref = "male";
        }
        if (gender == 1) {
            userPref = "female";
        }

        return userPref;
    }

    /**
     * Returns genderPreference
     * 
     * @return genderPreference
     */
    public int getGenderPreference() {
        return genderPreference;
    }

    /**
     * Sets the gender preference for the user. Preference is "other" by default
     * 
     * @param preference
     * @throws Exception
     */
    public void setGenderPreference(int preference) throws Exception {
        if (preference < 0 || preference > 2) {
            throw new Exception("Invalid gender preference!");
        }
        genderPreference = preference;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int newUID) {
        UID = newUID;
    }

    @Override
    public int compareTo(E o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", UID=" + UID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }

    public boolean getProfile() {
        return true;
    }

    public boolean verifyGalaxy(String abc190238) {
        return true;
    }

    public boolean deactivateAccount() {
        return true;
    }

    private Boolean[] preferences;

    public void setPreferences(Boolean[] matchpreferences) {
        preferences = matchpreferences;
    }

    public Boolean[] getPreferences() {
        return preferences;
    }

    public boolean deleteProfile() {
        return true;
    }

    //Removed EditBio Class in favor of this function within the User class.
    public void EditBio(String newBio) {
        bio = newBio;
    }

    public String getBio()
    {
        return bio;
    }

    private ArrayList<User> matches = new ArrayList<User>();

    public void addMatch(User u) {
        matches.add(u);
    }

    public ArrayList<User> getMatches() {
        return matches;
    }

    public boolean matchUser(User u) {
        return true;
    }

    public boolean rejectUser(User u) {
        return false;
    }
}