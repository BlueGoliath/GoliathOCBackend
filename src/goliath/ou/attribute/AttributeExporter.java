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

import goliath.ou.interfaces.Exporter;
import goliath.ou.utility.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttributeExporter implements Exporter<Attribute>
{
    private CsvWriter writer;
    private File file, directory;
    
    
    public AttributeExporter(File attrExportFolder)
    {   
        directory = attrExportFolder;
    }
    
    @Override
    public void exportObject(Attribute object)
    {
        if(!directory.exists())
           directory.mkdir();
        
        file = new File(directory.getAbsolutePath() + "/" + object.cmdNameProperty().getValue() + ".csv"); 
        
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException ex)
            {
                Logger.getLogger(AttributeExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try
        {
            writer = new CsvWriter(file);

            writer.addKeyValue("CmdName", object.cmdNameProperty().getValue());
            writer.addKeyValue("DisplayName", object.displayNameProperty().getValue());
            writer.addKeyValue("Measurement", object.getMeasurement());
            writer.addKeyValue("ReadOnly", String.valueOf(object.getReadOnly()));
            writer.addKeyValue("ShouldUpdate", String.valueOf(object.getShouldUpdate()));
        }
        catch (IOException ex)
        {
            Logger.getLogger(AttributeExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public File getExportedObjectFile()
    {
        return file;
    }
    
    public File getExportDirectory()
    {
        return directory;
    }
    
    public void setExportDirectory(File dir)
    {
        directory = dir;
    }
}
