package goliath.ou.api;

import java.util.ArrayList;

/**

 @author ty
 */
public interface AttributeProvider
{
    public String getGetterCmd();
    public String getSetterCmd();
    public ArrayList<String> getAttributes();
    public boolean shouldPull();
}
