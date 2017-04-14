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
package goliath.ou.performance;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class for representing Nvidia PowerMizer performance levels.
 * @author Ty Young
 */
public class PerformanceLevel
{
    private final StringProperty name;
    private final IntegerProperty minCore;
    private final IntegerProperty maxCore;
    private final IntegerProperty minMemory;
    private final IntegerProperty maxMemory;
    
    public PerformanceLevel()
    {
        maxMemory = new SimpleIntegerProperty();
        minMemory = new SimpleIntegerProperty();
        maxCore = new SimpleIntegerProperty();
        minCore = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        
        name.set(null);
        minCore.set(0);
        maxCore.set(0);
        minMemory.set(0);
        maxMemory.set(0);
    }

    public PerformanceLevel(String perfName)
    {
        maxCore = new SimpleIntegerProperty();
        minCore = new SimpleIntegerProperty();
        maxMemory = new SimpleIntegerProperty();
        minMemory = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        
        name.set(perfName);
        minCore.set(0);
        maxCore.set(0);
        minMemory.set(0);
        maxMemory.set(0);
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public IntegerProperty minCoreProperty()
    {
        return minCore;
    }

    public IntegerProperty maxCoreProperty()
    {
        return maxCore;
    }

    public IntegerProperty minMemoryProperty()
    {
        return minMemory;
    }

    public IntegerProperty maxMemoryProperty()
    {
        return maxMemory;
    }

    public void setName(String nm)
    {
        name.set(nm);
    }

    public void setMinCore(int min) {
        minCore.set(min);
    }


    public void setMaxCore(int max)
    {
        maxCore.set(max);
    }

    public void setMinMemory(int min)
    {
        minMemory.set(min);
    }

    public void setMaxMemory(int max)
    {
        maxMemory.set(max);
    }
}
