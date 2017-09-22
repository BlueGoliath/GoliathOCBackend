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
package goliath.ou.controller;

import goliath.ou.interfaces.GPUController;
import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributePuller;
import goliath.ou.attribute.AttributePusher;
import goliath.ou.utility.MinMaxParser;
import java.util.ArrayList;

public class VoltageOffsetController implements GPUController<Integer>
{
    private final AttributePusher pusher;
    private final Attribute voltAttr;
    private final int min, max;
    
    public VoltageOffsetController(Attribute attrVoltage)
    {
        MinMaxParser parser;
        pusher = new AttributePusher();
        voltAttr = attrVoltage;
        
        this.setValue(100*10000);
        
        parser = new MinMaxParser(this.getOutput().get(1), 0, 0);
        
        min = parser.getMin() / 1000;
        max = parser.getMax() / 1000;
    }

    @Override
    public void reset()
    {
        pusher.pushAttribute(voltAttr, "0");
    }
    
    @Override
    public String getName()
    {
        return "Voltage Offset";
    }
    
    @Override
    public Integer getCurrentValue()
    {
        return Integer.parseInt(voltAttr.cmdValueProperty().getValue());
    }

    @Override
    public Integer getMinValue()
    {
        return min;
    }

    @Override
    public Integer getMaxVelue()
    {
        return max;
    }

    @Override
    public ArrayList<String> getOutput()
    {
        return pusher.getOutput();
    }
    
    @Override
    public void setValue(Integer newVal)
    {
        pusher.pushAttribute(voltAttr, String.valueOf(newVal));
    }

    @Override
    public boolean isWorking()
    {
        return true;
    }
}
