package goliath.ou.power;

import goliath.ou.attribute.AttributePuller;
import goliath.io.Terminal;

/**

 @author ty
 */
public class PowerLimitParser
{
    private final Terminal shell;
    private int min;
    private int max;
    
    public PowerLimitParser()
    {
        shell = new Terminal();
        
    }
    public void beginParse()
    {
        String line;
        
        shell.setCommand("nvidia-smi --power-limit=99999999");
        shell.startCommand();
        
        line = shell.getCommandReader().nextLine();
        
        try
        {
            min = (int)Double.parseDouble(line.substring(86, 91));
        }
        catch(NumberFormatException e)
        {
            min = (int)Double.parseDouble(line.substring(86, 92));
        }
        
        try
        {
            max = (int)Double.parseDouble(line.substring(98, 103));
        }
        catch(NumberFormatException e)
        {
            max = (int)Double.parseDouble(line.substring(98, 104));
        } 
    }
    public int getMinPowerLimit()
    {
        return min;
    }
    public int getMaxPowerLimit()
    {
        return max;
    }
}
