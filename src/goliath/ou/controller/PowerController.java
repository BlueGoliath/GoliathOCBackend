/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goliath.ou.controller;

import goliath.ou.api.GPUController;
import goliath.ou.power.PowerLimitParser;
import goliath.io.Terminal;
import java.util.ArrayList;

/**

 @author ty
 */
public class PowerController implements GPUController<Integer>
{
    private final Terminal shell;
    private final int min;
    private final int max;
    private CharSequence password;
    
    public PowerController()
    {
        PowerLimitParser parser = new PowerLimitParser();
        shell = new Terminal();
        
        parser.beginParse();
        
        min = parser.getMinPowerLimit();
        max = parser.getMaxPowerLimit();
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
        shell.startCommand();
        shell.println(password.toString() + "\n");
        password = null;
        System.gc();
    }
    
}
