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
package goliath.ou.fan;

import goliath.ou.attribute.Attribute;
import goliath.ou.attribute.AttributePuller;
import java.util.ArrayList;
import goliath.ou.controller.FanModeController;
import goliath.ou.controller.FanSpeedController;

public class FanManager
{
    private final FanSpeedController fan;
    private final FanModeController mode;
    private final ArrayList<Integer> tempRange;
    private final Attribute temp;
    private final AttributePuller puller;
    private FanProfile currentProfile;
    
    public FanManager(FanSpeedController fanCont, FanModeController modeCont, Attribute fTemp)
    {
        fan = fanCont;
        mode = modeCont;
        currentProfile = null;
        temp = fTemp;
        puller = new AttributePuller();
        tempRange = new ArrayList<>();
    }
    public void updateFanSpeed()
    {
        int currentTemp = Integer.parseInt(puller.getAttributeValue(temp).get(0));

        if(currentTemp <= tempRange.get(0))
            fan.setValue(currentProfile.getNodes().get(0).speedProperty().intValue());
        else if(currentTemp >= tempRange.get(currentProfile.getNodes().size()-1))
            fan.setValue(currentProfile.getNodes().get(currentProfile.getNodes().size()-1).speedProperty().intValue());
        else
        {
            for(int i = 1; i < tempRange.size()-1; i++)
            {
                if(isWithinRange(currentTemp, tempRange.get(i-1), tempRange.get(i+1)))
                {
                   fan.setValue(currentProfile.getNodes().get(i).speedProperty().intValue());
                   break;
                }
            }
        }
    }
    public FanProfile getCurrentProfile()
    {
        return currentProfile;
    }
    public void setActiveProfile(FanProfile profile)
    {
        currentProfile = profile;
        
        tempRange.clear();
        
        for(int i = 0; i < profile.getNodes().size(); i++)
            tempRange.add(profile.getNodes().get(i).tempProperty().getValue());
    }
    public void setFanState(int state)
    {
        if(state == 0)
            mode.setValue(FanModeController.DRIVER_CONTROLLED);
        else
            mode.setValue(FanModeController.MANUALLY_CONTROLLED);
    }
    private boolean isWithinRange(int a, int b, int c)
    {
        if(a > b && a < c)
            return true;
        return false;
    }
}
