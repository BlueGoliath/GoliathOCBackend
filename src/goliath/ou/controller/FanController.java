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
