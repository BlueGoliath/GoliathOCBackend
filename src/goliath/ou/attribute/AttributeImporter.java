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
package goliath.ou.attribute;

import goliath.ou.interfaces.Importer;
import goliath.ou.utility.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttributeImporter implements Importer<Attribute>
{
    private Attribute attr;

    @Override
    public void importObject(File file)
    {
        CsvReader reader = null;
        String cmdName, displayName, unit;
        boolean readOnly, shouldUpdate;
        
        try
        {
            reader = new CsvReader(file);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to read attribute file " + file.getName());
            Logger.getLogger(AttributeImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cmdName = reader.getKeyValues("CmdName").get(0);
        displayName = reader.getKeyValues("DisplayName").get(0);
        unit = reader.getKeyValues("Measurement").get(0);
        
        readOnly = Boolean.parseBoolean(reader.getKeyValues("ReadOnly").get(0));
        shouldUpdate = Boolean.parseBoolean(reader.getKeyValues("ShouldUpdate").get(0));
        
        attr = new Attribute(cmdName, displayName, unit, readOnly, shouldUpdate);
    }
    
    @Override
    public Attribute getImportedObject()
    {
        return attr;
    }
}
