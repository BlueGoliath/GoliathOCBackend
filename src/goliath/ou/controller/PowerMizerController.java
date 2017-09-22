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

import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributePuller;
import goliath.ou.attribute.AttributePusher;
import goliath.ou.interfaces.GPUController;
import java.util.ArrayList;

public class PowerMizerController implements GPUController<Integer>
{
    private final AttributePusher pusher;
    private final AttributePuller puller;
    private final Attribute attr;
    
    public PowerMizerController(Attribute attrPM)
    {
        pusher = new AttributePusher();
        puller = new AttributePuller();
        attr = attrPM;
    }
    
    @Override
    public void reset()
    {
        pusher.pushAttribute(attr, "0");
    }

    @Override
    public String getName()
    {
        return "PowerMizer";
    }

    @Override
    public Integer getCurrentValue()
    {
        return Integer.parseInt(attr.cmdValueProperty().getValue());
    }

    @Override
    public Integer getMinValue()
    {
        return 0;
    }

    @Override
    public Integer getMaxVelue()
    {
        return 2;
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
