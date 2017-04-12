/*
 * The MIT License
 *
 * Copyright 2017 ty.
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

package goliath.ou.utility;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author ty
 */
public class CsvWriter
{
    private final File file;
    private final FileWriter writer;
    
    public CsvWriter(File csvFile) throws IOException
    {
        file = csvFile;
        writer = new FileWriter(file);
    }
    public void addKeyValue(String key, String value) throws IOException
    {
        writer.write(key + "," + value + "\n");
        writer.flush();
    }
    public void addKeyValueHashMap(HashMap<String,String> hash) throws IOException
    {
        for(int i = 0; i < hash.size(); i++)
        {
            writer.write(hash.keySet().toArray(new String[hash.size()])[i] + ",");
        }
    }
    public void overwriteValue(String key, String value)
    {
        
    }
    
}
