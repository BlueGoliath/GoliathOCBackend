package goliath.ou.attribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ty
 */
public class Attribute
{
    private StringProperty cmdName;
    private StringProperty cmdValue;
    private StringProperty displayName;
    private StringProperty displayValue;
    private final String measurement;
    private final boolean readOnly;
    private final boolean alwaysUpdate;
    
    
    public Attribute(String cmd, String display, String rawUnitType, boolean readOnlyBool, boolean shouldBeUpdated)
    {
        cmdName = new SimpleStringProperty();
        cmdValue = new SimpleStringProperty();
        displayName = new SimpleStringProperty();
        displayValue = new SimpleStringProperty();
        
        cmdName.set(cmd);
        cmdValue.set(null);
        displayName.set(display);
        displayValue.set(null);
        
        if(rawUnitType != null)
            measurement = rawUnitType;
        else
            measurement = "";
        
        readOnly = readOnlyBool;
        alwaysUpdate = shouldBeUpdated;
    }
    public StringProperty cmdNameProperty()
    {
        return cmdName;
    }
    public StringProperty displayNameProperty()
    {
        return displayName;
    }
    public StringProperty displayValueProperty()
    {
        return displayValue;
    }
    public StringProperty cmdValueProperty()
    {
        return cmdValue;
    }
    public String getMeasurement()
    {
        return measurement;
    }
    public boolean getReadOnly()
    {
        return readOnly;
    }
    public boolean getShouldUpdate()
    {
        return alwaysUpdate;
    }
    public void appendCmdName(String name)
    {
        cmdName.set(cmdName.get() + name);
    }
    public void setDisplayValue(String str)
    {
        displayValue.set(str);
    }
    public void setValue(String val)
    {
        cmdValue.set(val);
    }
    public void setDisplayName(String str)
    {
        displayName.set(str);
    }
    @Override
    public String toString()
    {
        return displayName.get();
    }
}
