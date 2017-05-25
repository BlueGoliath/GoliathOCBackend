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

import goliath.io.Terminal;
import java.io.IOException;
import java.util.ArrayList;

public class AttributePuller
{
    private Terminal shell;
    
    public AttributePuller()
    {
        shell = null;
    }
    public ArrayList<String> getAttributeValue(Attribute attr)
    {
        ArrayList<String> values = new ArrayList<>();
        StringBuilder cmd = new StringBuilder();
        shell = new Terminal();
        
        cmd.append("nvidia-settings -t -q ");
        cmd.append(attr.cmdNameProperty().getValue());
        
        shell.setCommand(cmd.toString());
        
        try
        {
            shell.startCommand();
        }
        catch (IOException ex)
        {
            System.out.println("Failed to get Nvidia attribute " + attr.cmdNameProperty().getValue());
        }

        while(shell.getCommandReader().hasNextLine())
            values.add(shell.getCommandReader().nextLine());
       
        shell.terminate();
        return values;
    }
    public ArrayList<String> getAttributeValue(String attr)
    {
        ArrayList<String> values = new ArrayList<>();
        StringBuilder cmd = new StringBuilder();
        shell = new Terminal();
        
        cmd.append("nvidia-settings -t -q ");
        cmd.append(attr);
        
        shell.setCommand(cmd.toString());
        
        try
        {
            shell.startCommand();
        }
        catch (IOException ex)
        {
            System.out.println("Failed to get Nvidia attribute " + attr);
        }

        shell.waitForExit();
        
        while(shell.getCommandReader().hasNextLine())
            values.add(shell.getCommandReader().nextLine());
       
        shell.terminate();
        
        return values;
    }
}
