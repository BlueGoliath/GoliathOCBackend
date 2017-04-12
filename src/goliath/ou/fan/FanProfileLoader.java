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

import goliath.ou.utility.CsvReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads and parses fan profile configs into FanProfile objects
 * @author ty
 */
public class FanProfileLoader
{
    private final File[] configs;
    private final ArrayList<FanProfile> profiles;
    private CsvReader reader;
    
    public FanProfileLoader(File[] files)
    {
        configs = files;
        profiles = new ArrayList<>();
    }
    public void loadProfiles()
    {
        FanProfile profile;
        ArrayList<String> tempValues;
        
        for(int i = 0; i < configs.length; i++)
        {
            try
            {
                reader = new CsvReader(configs[i]);
            }
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(FanProfileLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            profile = new FanProfile(configs[i]);
            profile.setName(reader.getKeyValues("display_name").get(0));
            profile.setUpdateDelay(Integer.parseInt(reader.getKeyValues("update_speed").get(0)));
            profile.setUseSmoothTrans(Boolean.valueOf(reader.getKeyValues("smooth").get(0)));
            
            tempValues = reader.getKeyValues("node");
            
            for(int k = 0; k < tempValues.size(); k++)
                profile.addValue(Integer.parseInt(tempValues.get(k)));  
            
            profiles.add(profile);
        }
    }
    public ArrayList<FanProfile> getLoadedProfiles()
    {
        return profiles;
    }
}
