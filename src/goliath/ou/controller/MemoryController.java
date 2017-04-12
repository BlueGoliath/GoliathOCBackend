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
public class MemoryController implements GPUController<Integer>
{
    private final AttributePusher pusher;
    private final AttributePuller puller;
    private final Attribute attr;
    
    public MemoryController(Attribute attrMemory)
    {
        pusher = new AttributePusher();
        puller = new AttributePuller();
        attr = attrMemory;
    }
    

    @Override
    public void reset()
    {
        pusher.pushAttribute(attr, "0");
    }

    @Override
    public Integer getCurrentValue()
    {
       return Integer.parseInt(puller.getAttributeValue(attr).get(0));
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
        pusher.pushAttribute(attr, String.valueOf(newVal));
    }
}
