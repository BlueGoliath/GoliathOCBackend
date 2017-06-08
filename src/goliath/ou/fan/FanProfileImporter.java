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

import goliath.ou.interfaces.Importer;
import goliath.ou.utility.CsvReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FanProfileImporter implements Importer<FanProfile>
{
    private FanProfile profile;
    
    @Override
    public void importObject(File file)
    {
        CsvReader reader = null;
        ArrayList<String> tempValues;

        try
        {
            reader = new CsvReader(file);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(FanProfileImporter.class.getName()).log(Level.SEVERE, null, ex);
        }

        profile = new FanProfile(file);
        profile.setName(reader.getKeyValues("display_name").get(0));
        profile.setUpdateDelay(Integer.parseInt(reader.getKeyValues("update_speed").get(0)));
        profile.setUseSmoothTrans(Boolean.valueOf(reader.getKeyValues("smooth").get(0)));

        for (int l = 5; l < 100; l++)
        {
            tempValues = reader.getKeyValues("node_" + l);

            if (!tempValues.isEmpty())
            {
                profile.addNode(l, Integer.parseInt(tempValues.get(0)));
            }
        }
    }

    @Override
    public FanProfile getImportedObject()
    {
        return profile;
    }
}
