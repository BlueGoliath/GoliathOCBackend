/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class VoltageController implements GPUController<Integer>
{
    private final AttributePusher pusher;
    private final AttributePuller puller;
    private final Attribute voltAttr;
    
    public VoltageController(Attribute attrVoltage)
    {
        pusher = new AttributePusher();
        puller = new AttributePuller();
        voltAttr = attrVoltage;
    }

    @Override
    public void reset()
    {
        pusher.pushAttribute(voltAttr, "0");
    }
    
    @Override
    public Integer getCurrentValue()
    {
       return Integer.parseInt(puller.getAttributeValue(voltAttr).get(0));
    }

    @Override
    public Integer getMinValue()
    {
        return 0;
    }

    @Override
    public Integer getMaxVelue()
    {
        return 100;
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
}
