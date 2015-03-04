package TC;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Enemy tank test for Tank Command (T.C.)
 *
 *
 * @author Tamky Ngo
 * @version 03/03/2015
 *
 **/

public class EnemyTankTest {

    /** Test EnemyTank Constructor
     *@see enemytank#initialize(int x, int y)
     */

    @Test public void testEnemyConstructor(){
        int randomPosX = (int) (Math.random() * 600);
        int randomPosY = (int) (Math.random() * 400);
        int randomPosx = (int) (Math.random() * 600);
        int randomPosy = (int) (Math.random() * 400);

        enemytank e1 = new enemytank();
        e1.initialize(randomPosX, randomPosY);

        enemytank e2 = new enemytank();
        e2.initialize(randomPosx, randomPosy);

        assertNotEquals(e1.x, e2.x);
        assertNotEquals(e1.y, e2.y);

    }

}