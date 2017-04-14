/* 
 * The MIT License
 *
 * Copyright 2017 Ty Young.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
