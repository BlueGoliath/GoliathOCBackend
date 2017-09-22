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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttributeUpdater
{
    private final AttributePuller puller;
    private long updateDelay;

    public AttributeUpdater()
    {
        puller = new AttributePuller();
        updateDelay = 0;
    }
    public long getUpdateDelay()
    {
        return updateDelay;
    }
    public void setUpdateDelay(long delay)
    {
        updateDelay = delay;
    }
    public void update(Attribute attr)
    {
        String tmpVal = attr.cmdValueProperty().getValue();
        attr.setCmdValue(puller.getAttributeValue(attr.cmdNameProperty().getValue()).get(0));

        if (!attr.cmdValueProperty().getValue().equals(tmpVal))
        {
            switch (attr.cmdNameProperty().getValue())
            {
                case "GPUOverVoltageOffset":
                case "GPUCurrentCoreVoltage":
                    attr.setDisplayValue(Integer.parseInt(attr.cmdValueProperty().getValue()) / 1000 + " " + attr.getMeasurement());
                    break;

                default:
                    attr.setDisplayValue(attr.cmdValueProperty().getValue() + " " + attr.getMeasurement());
                    break;
            }
        }
    }

    public void updateAll(ArrayList<Attribute> attrs, boolean useDelay)
    {
        String tmpVal;
        
        for (int i = 0; i < attrs.size(); i++)
        {
            tmpVal = attrs.get(i).cmdValueProperty().getValue();
            
            try
            {
                attrs.get(i).setCmdValue(puller.getAttributeValue(attrs.get(i).cmdNameProperty().getValue()).get(0));
            }
            catch(IndexOutOfBoundsException e)
            {
                System.out.println("Failed to update attribute " + attrs.get(i).cmdNameProperty().getValue() + ".");
                break;
            }
            
            if (!attrs.get(i).cmdValueProperty().getValue().equals(tmpVal))
            {
                switch (attrs.get(i).cmdNameProperty().getValue())
                {
                    case "GPUOverVoltageOffset":
                    case "GPUCurrentCoreVoltage":
                        attrs.get(i).setDisplayValue(Integer.parseInt(attrs.get(i).cmdValueProperty().getValue()) / 1000 + " " + attrs.get(i).getMeasurement());
                        break;

                    default:
                        attrs.get(i).setDisplayValue(attrs.get(i).cmdValueProperty().getValue() + " " + attrs.get(i).getMeasurement());
                        break;
                }
            }
            try
            {
                if(useDelay)
                    Thread.sleep(updateDelay);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(AttributeUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
