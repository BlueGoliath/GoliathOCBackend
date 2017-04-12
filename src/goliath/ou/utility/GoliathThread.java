
package goliath.ou.utility;

import java.lang.reflect.Method;

public class GoliathThread extends Thread
{   
    public GoliathThread(Class objClass, Object obj, Method objMethod)
    {
        super(new GoliathRunnable(objClass, obj, objMethod));
    }
    public GoliathThread(Object obj, Method objMethod)
    {
        super(new GoliathRunnable(obj.getClass(), obj, objMethod));
    }
}
