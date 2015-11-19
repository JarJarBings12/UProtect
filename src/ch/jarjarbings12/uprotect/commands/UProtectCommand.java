package ch.jarjarbings12.uprotect.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by tobias on 28.10.2015.
 */
public class UProtectCommand extends Command
{

    public UProtectCommand(String name)
    {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args)
    {

        if (args.length == 0)
        {
            sender.sendMessage("[UProtect] version, playtime");
            return true;
        }

        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("version"))
            {
                sender.sendMessage("Not implemented yet!");
                return true;
            }
            else if (args[0].equalsIgnoreCase("playtime"))
            {

            }
            else
            {
                sender.sendMessage("[UProtect] version, playtime");
                return true;
            }
        }
        return true;
    }
}
