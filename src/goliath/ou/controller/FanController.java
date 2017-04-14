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

import goliath.ou.api.GPUController;
import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributePuller;
import goliath.ou.attribute.AttributePusher;
import java.util.ArrayList;

/**

 @author ty
 */
public class FanController implements GPUController<Integer>
{
    public final static int DRIVER = 0;
    public final static int MANUAL = 1;
    
    private final AttributePusher pusher;
    private final AttributePuller puller;
    private final Attribute fanAttr, modeAttr;
    
    public FanController(Attribute attrFan, Attribute attrFanMode)
    {
        pusher = new AttributePusher();
        puller = new AttributePuller();
        fanAttr = attrFan;
        modeAttr = attrFanMode;
    }
 
    @Override
    public void reset()
    {
        pusher.pushAttribute(fanAttr, "0");
    }

    public int getFanMode()
    {
        return Integer.parseInt(puller.getAttributeValue(modeAttr).get(0));
    }
    
    @Override
    public Integer getCurrentValue()
    {
       return Integer.parseInt(puller.getAttributeValue(fanAttr).get(0));
    }

    @Override
    public Integer getMinValue()
    {
        return -1000;
    }

    @Override
    public Integer getMaxVelue()
    {
        return 7010;
    }

    @Override
    public ArrayList<String> getOutput()
    {
        return pusher.getOutput();
    }
    
    @Override
    public void setValue(Integer newVal)
    {
        pusher.pushAttribute(fanAttr, String.valueOf(newVal));
    }
    
    public void setFanMode(int mode)
    {
        pusher.pushAttribute(modeAttr, String.valueOf(mode));
    }
}
