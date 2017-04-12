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
public interface Importer<T>
{
    public void readFile();
    public void createObject();
    public File getFile();
    public T getImportedData();
}
