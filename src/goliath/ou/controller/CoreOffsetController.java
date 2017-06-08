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

public class CoreOffsetController implements GPUController<Integer>
{
    private final AttributePusher pusher;
    private final AttributePuller puller;
    private final Attribute attr;
    private final int min, max;
    
    public CoreOffsetController(Attribute attrCore)
    {
        MinMaxParser parser;
        String str;
        
        pusher = new AttributePusher();
        puller = new AttributePuller();
        attr = attrCore;
        
        this.setValue(9999999);
        
        str = this.getOutput().get(1) + this.getOutput().get(2);
        
        parser = new MinMaxParser(str, 0, 0);
        
        min = parser.getMin();
        max = parser.getMax();
    }
    
    @Override
    public void reset()
    {
        pusher.pushAttribute(attr.cmdNameProperty().getValue(), "0");
    }
    @Override
    public String getName()
    {
        return attr.cmdNameProperty().getValue();
    }
    @Override
    public Integer getCurrentValue()
    {
        return Integer.parseInt(puller.getAttributeValue(attr).get(0));
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
       pusher.pushAttribute(attr, String.valueOf(newVal));
    } 

    @Override
    public boolean isWorking()
    {
        return true;
    }
}
