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

import goliath.ou.interfaces.CsvData;
import java.io.File;
import java.util.Comparator;
import java.util.Collections;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.HashMap;

public class FanProfile extends File
{
    private final ArrayList<FanNode> nodes;
    
    private StringProperty name;
    private boolean useSmoothTrans;
    private long updateInterval;
    private final NodeComparator nodeComp;
    private final HashMap<String, String> data;
    
    public FanProfile(String filePath)
    {
        super(filePath);
        name = new SimpleStringProperty();
        
        nodes = new ArrayList<>();
        nodeComp = new NodeComparator();
        
        data = new HashMap<>();
        
        data.put("display_name", name.getValue());
        data.put("update_speed", String.valueOf(updateInterval));
        data.put("smooth_speed", String.valueOf(useSmoothTrans));
    }
    public FanProfile(File file)
    {
       super(file.getAbsolutePath());
       name = new SimpleStringProperty();
        
       nodes = new ArrayList<>();
       nodeComp = new NodeComparator();
       
       data = new HashMap<>();
        
       data.put("display_name", name.getValue());
       data.put("update_speed", String.valueOf(updateInterval));
       data.put("smooth_speed", String.valueOf(useSmoothTrans));
    }
    public void addNode(int c, int p)
    {
        nodes.add(new FanNode(c, p));
        Collections.sort(nodes, nodeComp);
    }
    public void addNode(FanNode node)
    {
        nodes.add(node);
        Collections.sort(nodes, nodeComp);
    }
    public void reSortNodes()
    {
        Collections.sort(nodes, nodeComp);
    }
    public StringProperty nameProperty()
    {
        return name;
    }
    public ArrayList<FanNode> getNodes()
    {
        return nodes;
    }
    public boolean getUseSmoothTrans()
    {
        return useSmoothTrans;
    }
    public long getUpdateDelay()
    {
        return updateInterval;
    }
    public void setName(String nm)
    {
        name.set(nm);
    }
    public void setUseSmoothTrans(boolean use)
    {
        useSmoothTrans = use;
    }
    public void setUpdateDelay(int speed)
    {
        updateInterval = speed;
    }
    @Override
    public String toString()
    {
        return name.getValue();
    }
    private class NodeComparator implements Comparator<FanNode>
    {
        @Override
        public int compare(FanNode t, FanNode t1)
        {
            if(t.tempProperty().intValue() < t1.tempProperty().intValue())
                return -1;
            else if(t.tempProperty().intValue() == t1.tempProperty().intValue())
                return 0;
            else
                return 1;
        }  
    }
    private class ProfileCsvData implements CsvData
    {
        @Override
        public HashMap<String, String> getCsvData()
        {
            return null;
        }
    }
}
