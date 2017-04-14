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

import goliath.ou.api.Importer;
import goliath.ou.utility.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ty
 */
public class AttributeImporter implements Importer<Attribute>
{
    private CsvReader reader;
    private File file;
    private Attribute attr;
    private String cmdName, displayName, unit;
    private boolean readOnly, shouldUpdate;

    public AttributeImporter(File attrFile)
    {
        file = attrFile;
        reader = null;
        
        try
        {
            reader = new CsvReader(file);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(AttributeImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void readFile()
    {
        cmdName = reader.getKeyValues("cmdName").get(0);
        displayName = reader.getKeyValues("displayName").get(0);
        unit = reader.getKeyValues("unit").get(0);
        
        readOnly = Boolean.parseBoolean(reader.getKeyValues("readOnly").get(0));
        shouldUpdate = Boolean.parseBoolean(reader.getKeyValues("shouldUpdate").get(0));
    }
    @Override
    public void createObject()
    {
        attr = new Attribute(cmdName, displayName, unit, readOnly, shouldUpdate);
    }
    @Override
    public File getFile()
    {
        return file;
    }
    @Override
    public Attribute getImportedData()
    {
        return attr;
    }
}
