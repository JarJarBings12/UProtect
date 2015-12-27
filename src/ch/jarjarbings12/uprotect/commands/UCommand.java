package ch.jarjarbings12.uprotect.commands;

import org.bukkit.command.CommandSender;

/**
 * @author JarJarBings12
 * @creationDate 13.12.2015
 * © 2015 JarJarBings12
 */
public final class UCommand
{
    private final CommandSender sender;
    private final String label;
    private final String[] arguemnts;

    public UCommand(CommandSender sender, String label, String[] arguemnts)
    {
        this.sender = sender;
        this.label = label;
        this.arguemnts = arguemnts;
    }

    public CommandSender getSender()
    {
        return this.sender;
    }

    public String getLabel()
    {
        return this.label;
    }

    public String[] getArguemnts()
    {
        return this.arguemnts;
    }
}
