package TC;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Player tank test for Tank Command (T.C.)
 *
 *
 * @author Tamky Ngo
 * @version 03/03/2015
 *
 **/

public class PlayerTankTest {

    /** Tests constructor
     * @see playertank#playertank(int x)
     */

    @Test public void testConstructor(){
        playertank myTank = new playertank(0);
        playertank player = new playertank(Framework.width/10);
        assertEquals(player.x, myTank.x);
        assertEquals(player.y, myTank.y);
    }

}