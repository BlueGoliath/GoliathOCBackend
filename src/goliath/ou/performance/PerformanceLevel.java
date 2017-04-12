/*
 * The MIT License
 *
 * Copyright 2017 ty.
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
package goliath.ou.performance;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ty
 */
public class PerformanceLevel
{
    private StringProperty name;
    private IntegerProperty minGraphicsClock;
    private IntegerProperty maxGraphicsClock;
    private IntegerProperty minMemoryClock;
    private IntegerProperty maxMemoryClock;
    private IntegerProperty minVoltage;
    private IntegerProperty maxVolage;
    
    public PerformanceLevel()
    {
        this.maxVolage = new SimpleIntegerProperty();
        this.minVoltage = new SimpleIntegerProperty();
        this.maxMemoryClock = new SimpleIntegerProperty();
        this.minMemoryClock = new SimpleIntegerProperty();
        this.maxGraphicsClock = new SimpleIntegerProperty();
        this.minGraphicsClock = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        
        name.set("Undefined!");
        minGraphicsClock.set(0);
        maxGraphicsClock.set(0);
        minMemoryClock.set(0);
        maxMemoryClock.set(0);
    }
    public PerformanceLevel(String perfName)
    {
        this.maxVolage = new SimpleIntegerProperty();
        this.minVoltage = new SimpleIntegerProperty();
        this.maxMemoryClock = new SimpleIntegerProperty();
        this.minMemoryClock = new SimpleIntegerProperty();
        this.maxGraphicsClock = new SimpleIntegerProperty();
        this.minGraphicsClock = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        name.set(perfName);
        minGraphicsClock.set(0);
        maxGraphicsClock.set(0);
        minMemoryClock.set(0);
        maxMemoryClock.set(0);
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public IntegerProperty minClockProperty()
    {
        return minGraphicsClock;
    }
    public IntegerProperty maxClockProperty()
    {
        return maxGraphicsClock;
    }
    public IntegerProperty minMemoryProperty()
    {
        return minMemoryClock;
    }
    public IntegerProperty maxMemoryProperty()
    {
        return maxMemoryClock;
    }
    public IntegerProperty minVoltageProperty()
    {
        return minVoltage;
    }
    public IntegerProperty maxVoltageProperty()
    {
        return maxVolage;
    }

    
    public void setMinVoltage(int min)
    {
        minVoltage.set(min);
    }
    public void setName(String nm)
    {
        name.set(nm);
    }
    public void setMaxVolage(int max)
    {
        maxVolage.set(max);
    }
    public void setMinGraphicsClock(int min) {
        minGraphicsClock.set(min);
    }
    public void setMaxGraphicsClock(int max)
    {
        maxGraphicsClock.set(max);
    }
    public void setMinMemoryClock(int min)
    {
        minMemoryClock.set(min);
    }
    public void setMaxMemoryClock(int max)
    {
        maxMemoryClock.set(max);
    }
}
