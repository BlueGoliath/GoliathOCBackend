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
package goliath.ou.controller;

import goliath.ou.interfaces.GPUController;
import goliath.ou.power.PowerLimitParser;
import goliath.io.Terminal;
import java.io.IOException;
import java.util.ArrayList;

/**

 @author ty
 */
public class PowerLimitController implements GPUController<Integer>
{
    private final Terminal shell;
    private final int min;
    private final int max;
    private CharSequence password;
    
    public PowerLimitController()
    {
        PowerLimitParser parser = new PowerLimitParser();
        shell = new Terminal();
        
        parser.beginParse();
        
        min = parser.getMinPowerLimit();
        max = parser.getMaxPowerLimit();
    }
            
    @Override
    public String getName()
    {
        return "PowerLimit";
    }
    
    @Override
    public void reset()
    {
        // no way to get default value yet.
    }

    @Override
    public Integer getCurrentValue()
    {
        return null;
    }

    @Override
    public Integer getMinValue()
    {
        return min;
    }

    @Override
    public Integer getMaxVelue()
    {
        return max;
    }

    @Override
    public ArrayList<String> getOutput()
    {
        ArrayList<String> output = new ArrayList<>();
        
        while(shell.getCommandReader().hasNextLine())
            output.add(shell.getCommandReader().nextLine());
        
        return output;
    }
    public void setPassword(CharSequence psw)
    {
        password = psw;
    }
    @Override
    public void setValue(Integer newVal)
    {
        shell.setCommand("sudo -u root -S nvidia-smi -pl " + newVal);
        
        try
        {
            shell.startCommand();
        }
        catch (IOException ex)
        {
            System.out.println("Failed to set new power limit value " + newVal);
        }
        
        shell.println(password.toString() + "\n");
        password = null;
        System.gc();
    }
    
}
