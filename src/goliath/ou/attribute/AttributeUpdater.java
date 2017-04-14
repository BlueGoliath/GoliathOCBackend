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

/**
 *
 * @author ty
 */
public class AttributeUpdater
{
    private final ArrayList<Attribute> attributes;
    private final AttributePuller puller;
    
    public AttributeUpdater(ArrayList<Attribute> atts)
    {
        attributes = atts;
        puller = new AttributePuller();
    }
    public void updateAll(boolean ignoreShouldUpdate)
    {  
        String tmpVal;
        
        for(int i = 0; i < attributes.size(); i++)
        {
            if(ignoreShouldUpdate || attributes.get(i).getShouldUpdate())
            {
                tmpVal = attributes.get(i).cmdValueProperty().getValue();
                attributes.get(i).setValue(puller.getAttributeValue(attributes.get(i).cmdNameProperty().getValue()).get(0));
                
                if(!attributes.get(i).cmdValueProperty().getValue().equals(tmpVal))
                {
                    switch(attributes.get(i).cmdNameProperty().getValue())
                    {
                        case "GPUOverVoltageOffset":
                        case "GPUCurrentCoreVoltage":
                            attributes.get(i).setDisplayValue(Integer.parseInt(attributes.get(i).cmdValueProperty().getValue())/1000 + " " + attributes.get(i).getMeasurement());
                            break;
                            
                        default:
                            attributes.get(i).setDisplayValue(attributes.get(i).cmdValueProperty().getValue() + " " + attributes.get(i).getMeasurement());
                            break;
                    }
                }
                try
                {
                    Thread.sleep(175);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(AttributeUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
