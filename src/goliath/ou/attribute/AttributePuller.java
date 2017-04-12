package goliath.ou.attribute;

import goliath.io.Terminal;
import java.util.ArrayList;

/**
 *
 * @author ty
 */
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
        cmd.append(attr.cmdNameProperty().toString());
        
        shell.setCommand(cmd.toString());
        shell.startCommand();

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
        shell.startCommand();

        while(shell.getCommandReader().hasNextLine())
            values.add(shell.getCommandReader().nextLine());
       
        shell.terminate();
        
        return values;
    }
}
