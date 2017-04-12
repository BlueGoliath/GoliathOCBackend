package goliath.ou.controller;

import goliath.ou.api.GPUController;
import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributePuller;
import goliath.ou.attribute.AttributePusher;
import java.util.ArrayList;

public class CoreController implements GPUController<Integer>
{
    private final AttributePusher pusher;
    private final AttributePuller puller;
    private final Attribute attr;
    
    public CoreController(Attribute attrCore)
    {
        pusher = new AttributePusher();
        puller = new AttributePuller();
        attr = attrCore;
    }
    
    @Override
    public void reset()
    {
        pusher.pushAttribute(attr.cmdNameProperty().getValue(), "0");
    }
        
    @Override
    public Integer getCurrentValue()
    {
        return Integer.parseInt(puller.getAttributeValue(attr).get(0));
    }

    @Override
    public Integer getMinValue()
    {
        return -202;
    }

    @Override
    public Integer getMaxVelue()
    {
        return 1000;
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
