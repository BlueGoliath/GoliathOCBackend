/*
 * The MIT License
 *
 * Copyright 2017 ty.
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

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class FanProfile extends File
{
    private String name;
    private final ArrayList<Integer> values;
    private boolean useSmoothTrans;
    private long updateInterval;
    
    public FanProfile(String filePath)
    {
        super(filePath);
        name = "undefined";
        useSmoothTrans = true;
        updateInterval = 1000;
        
        values = new ArrayList<>();
    }
    public FanProfile(File file)
    {
        super(file.getAbsolutePath());
        name = "undefined";
        useSmoothTrans = true;
        updateInterval = 1000;
        
        values = new ArrayList<>();
    }
    public String getName()
    {
        return name;
    }
    public ArrayList<Integer> getValues()
    {
        return values;
    }
    public boolean getUseSmoothTrans()
    {
        return useSmoothTrans;
    }
    public long getUpdateDelay()
    {
        return updateInterval;
    }
    public void updateProfileName(String profileName)
    {
        name = profileName;
    }
    public void setName(String nm)
    {
        name = nm;
    }
    public void setUseSmoothTrans(boolean use)
    {
        useSmoothTrans = use;
    }
    public void setUpdateDelay(int speed)
    {
        updateInterval = speed;
    }
    public void addValue(Integer num)
    {
        values.add(num);
    }
    @Override
    public String toString()
    {
        return name;
    }
}
