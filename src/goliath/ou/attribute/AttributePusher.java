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

/**
 *
 * @author ty
 */
public class AttributePusher
{
    private final Terminal shell;
    private ArrayList<String> output;
    
    public AttributePusher()
    {
        shell = new Terminal();
    }
    public void pushAttribute(Attribute attr, String val)
    {
        StringBuilder cmd = new StringBuilder();
        output = new ArrayList<>();
        
        cmd.append("nvidia-settings -a ");
        cmd.append(attr.cmdNameProperty().getValue());
        cmd.append("=");
        cmd.append(val);
        
        shell.setCommand(cmd.toString());

        try
        {
            shell.startCommand();
        }
        catch (IOException ex)
        {
            System.out.println("Failed to push Nvidia attribute " + attr.cmdNameProperty().getValue());
        }
        
        while(shell.getCommandReader().hasNextLine())
            output.add(shell.getCommandReader().nextLine());
    }
    public void pushAttribute(String attr, String val)
    {
        StringBuilder cmd = new StringBuilder();
        output = new ArrayList<>();
        
        cmd.append("nvidia-settings -a ");
        cmd.append(attr);
        cmd.append("=");
        cmd.append(val);
        
        shell.setCommand(cmd.toString());
        
        try
        {
            shell.startCommand();
        }
        catch (IOException ex)
        {
            System.out.println("Failed to push Nvidia attribute " + attr);
        }
        
        while(shell.getCommandReader().hasNextLine())
            output.add(shell.getCommandReader().nextLine());
    }
    public ArrayList<String> getOutput()
    {
        return output;
    }
    public void terminate()
    {
        shell.terminate();
    }
}

