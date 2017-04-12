package goliath.ou.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GoliathRunnable implements Runnable
{
    private final Class objectClass;
    private final Object object;
    private final Method objectMethod;
            
    public GoliathRunnable(Class objClass, Object obj, Method objMethod)
    {
        objectClass = objClass;
        object = obj;
        objectMethod = objMethod;
    }

    public GoliathRunnable(Object obj, Method objMethod) {
        objectClass = obj.getClass();
        object = obj;
        objectMethod = objMethod;
    }
    
    @Override
    public void run()
    {
        try
        {
            objectClass.getDeclaredMethod(objectMethod.getName()).invoke(object);
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            Logger.getLogger(GoliathRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
