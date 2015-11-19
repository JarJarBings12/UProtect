package ch.jarjarbings12.uprotect.commands;

import ch.jarjarbings12.uprotect.commands.subcommands.RegionCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author JarJarBings12
 * @creationDate 09.11.2015
 * © 2015 JarJarBings12
 */
public class ChunkProtectCommands extends Command
{
    private RegionCommands regionCommands = new RegionCommands();

    public ChunkProtectCommands(String name)
    {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args)
    {
        if (args.length == 0)
        {
            //TODO INFO
            return true;
        }
        else if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("list"))
                regionCommands.list(sender, label, args);
            return true;
        }
        else if (args.length == 2)
        {
            if (args[0].equalsIgnoreCase("create"))
                regionCommands.create(sender, label, args);
            else if (args[0].equalsIgnoreCase("remove"))
                regionCommands.remove(sender, label, args);
            else if (args[0].equalsIgnoreCase("delete"))
                regionCommands.delete(sender, label, args);
            else if (args[0].equalsIgnoreCase("append"))
                regionCommands.append(sender, label, args);
            else if (args[0].equalsIgnoreCase("list"))
                regionCommands.list(sender, label, args);
            else
                return true;
            return true;
        }
        return true;
    }
}
