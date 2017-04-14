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
package goliath.ou.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads .csv files which store settings for the application. It is assumed that the file passed to the constructor is a .csv file.
 * Settings are broken up into keys and values.
 * @author ty
 */
public class CsvReader
{
    private final File file;
    private final Scanner fileReader;
    private final ArrayList<String> keys;
    private final ArrayList<String> values;
    
    public CsvReader(File csvFile) throws FileNotFoundException
    {   
        file = csvFile;
        fileReader = new Scanner(file);
        keys = new ArrayList<>();
        values = new ArrayList<>();
        
        String[] tmp;
        
        while(fileReader.hasNextLine())
        {
            tmp = fileReader.nextLine().split(",");
            
            if(tmp.length != 0)
            {
                keys.add(tmp[0]);
                values.add(tmp[1]);
            }
        }
    }
    public ArrayList<String> getKeys()
    {
        return keys;
    }
    public ArrayList<String> getValues()
    {
        return values;
    }
    public ArrayList<String> getKeyValues(String key)
    {
        ArrayList<String> tmp = new ArrayList<>();
        
        for(int i = 0; i < keys.size(); i++)
        {
            if(keys.get(i).equals(key))
                tmp.add(values.get(i));
        }
        
        return tmp;
    } 
}
