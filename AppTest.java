import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    void InterestingPlaces(Node head)
    {
    	do
    	{
    		System.out.print(head.data);
    		head = head.next;
    	}while(head != tail)

    }
    @Test
    void AccountSettings(int clickedon)
    {
    	string input
    	switch(clickedon)
    	{
    	// clicks cancel
    	case 1: 
    			break;
    	//changes a setting		
    	case 2:
    		string setting = input;
    		break;

    	}
    	

    }
    @Test
    void UpcomingEvents(Node head)
    {
    	do
    	{
    		System.out.print(head.data);
    		head = head.next;
    	}while(head != tail)


    }
    @Test
    void SendMsg(String msg, User sender, User receiver)
    {
    	System.out.print(sender.getUserName + "says");
    	System.out.print(msg + "to");
    	System.out.print(receiver.getUserName);

    }
    @Test
    void SendPicture(Picture picture, User sender, User receiver)
    {
    	System.out.print(sender.getUserName + "sent");
    	System.out.print(picture + "to");
    	System.out.print(receiver.getUserName);

    }
}
