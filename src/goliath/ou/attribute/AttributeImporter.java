/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
