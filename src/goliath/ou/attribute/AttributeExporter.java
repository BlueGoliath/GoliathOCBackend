/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliath.ou.attribute;

import goliath.ou.api.Exporter;
import goliath.ou.utility.CsvWriter;
import java.io.File;

/**
 *
 * @author ty
 */
public class AttributeExporter implements Exporter<Attribute>
{
    private Attribute attrToExport;
    private CsvWriter writer;
    private File file;
    
    
    public AttributeExporter(Attribute attr)
    {
        
    }
    @Override
    public void export()
    {
        
    }


    @Override
    public void setObjectToExport(Attribute object)
    {
        attrToExport = object;
    }

    @Override
    public File getExportedObjectFile() {
        return null;
    }
}
