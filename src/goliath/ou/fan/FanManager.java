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

import goliath.ou.controller.FanController;

/**
 *
 * @author ty
 */
public class FanManager
{
    private final FanController fan;
    private final int[] tempRange;
    private FanProfile currentProfile;
    
    public FanManager(FanController fanCont)
    {
        fan = fanCont;
        currentProfile = null;
        
        tempRange = new int[10];
        
        tempRange[0] = 35;
        tempRange[1] = 40;
        tempRange[2] = 45;
        tempRange[3] = 50;
        tempRange[4] = 55;
        tempRange[5] = 60;
        tempRange[6] = 65;
        tempRange[7] = 70;
        tempRange[8] = 75;
        tempRange[9] = 80; 
    }
    public void updateFanSpeed()
    {
        int currentTemp = fan.getCurrentValue();

        if(currentTemp <= tempRange[0])
            fan.setValue(currentProfile.getValues().get(0));
        else if(currentTemp >= tempRange[9])
            fan.setValue(currentProfile.getValues().get(9));
        else
        {
            for(int i = 1; i < tempRange.length-1; i++)
            {
                if(isWithinRange(currentTemp, tempRange[i-1], tempRange[i+1]))
                {
                   fan.setValue(currentProfile.getValues().get(i));
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
    }
    private boolean isWithinRange(int a, int b, int c)
    {
        if(a > b && a < c)
            return true;
        return false;
    }

    public void setFanState(int state)
    {
        if(state == 0)
            fan.setFanMode(FanController.DRIVER);
        else
            fan.setFanMode(FanController.MANUAL);
    }

}
