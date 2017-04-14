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
package goliath.ou.performance;

import goliath.ou.attribute.AttributePuller;
import java.util.ArrayList;

/**
 *
 * @author ty
 */
public class PerfLevelParser
{
    private final ArrayList<PerformanceLevel> levels;
    private final AttributePuller puller;
    
    public PerfLevelParser()
    {
        levels = new ArrayList<>();
        puller = new AttributePuller();
    }
    public ArrayList<PerformanceLevel> beginParse()
    {
        ArrayList<String> output = puller.getAttributeValue("GPUPerfModes");
        StringBuilder tmp = new StringBuilder();
        String[] perfSections = null;
        PerformanceLevel tmpLevel;
        
        for(int i = 0; i < output.size(); i++)
            tmp.append(output.get(i));
        
        perfSections = tmp.toString().split("; ");
        
        /*
            Parse and set everything. Since the values can be either 3 or 4 characters long, use the biggest(4) first.
            If that fails, catch the exception by getting 3 characters long.
        */
        for(int i = 0; i < perfSections.length; i++)
        {
            tmpLevel = new PerformanceLevel();

            tmpLevel.setName(perfSections[i].substring(perfSections[i].indexOf("perf=")+5, perfSections[i].indexOf("perf=") + 6));
            tmpLevel.setMinCore(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("nvclockmin=")+11, perfSections[i].indexOf("nvclockmin=") + 14)));

            try
            {
               tmpLevel.setMaxCore(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("nvclockmax=")+11, perfSections[i].indexOf("nvclockmax=") + 15))); 
            }
            catch(NumberFormatException e)
            {
                tmpLevel.setMaxCore(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("nvclockmax=")+11, perfSections[i].indexOf("nvclockmax=") + 14)));
            }
            
            try
            {
                tmpLevel.setMinMemory(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("memTransferRatemin=")+19, perfSections[i].indexOf("memTransferRatemin=") + 23)));
            }
            catch(NumberFormatException e)
            {
                tmpLevel.setMinMemory(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("memTransferRatemin=")+19, perfSections[i].indexOf("memTransferRatemin=") + 22)));
            }
            
            try
            {
                tmpLevel.setMaxMemory(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("memTransferRatemax=")+19, perfSections[i].indexOf("memTransferRatemax=") + 23)));
            }
            catch(NumberFormatException e)
            {
                tmpLevel.setMaxMemory(Integer.parseInt(perfSections[i].substring(perfSections[i].indexOf("memTransferRatemax=")+19, perfSections[i].indexOf("memTransferRatemax=") + 22)));
            }
            
            levels.add(tmpLevel);
        }

        // hack since there are more than 3 performance levels being included
        for(int i = 1; i < levels.size(); i++)
        {
            for(int k = i+1; k < levels.size(); k++)
            {
                if(levels.get(i).nameProperty().getValue().equals(levels.get(k).nameProperty().getValue()))
                    levels.remove(k);
            }        
        }
        return levels;
    }
}
