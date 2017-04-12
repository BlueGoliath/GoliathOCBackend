/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliath.ou.api;

import java.io.File;

/**
 *
 * @author ty
 */
public interface Exporter<T>
{
    public void export();
    public File getExportedObjectFile();
    public void setObjectToExport(T object);
}
