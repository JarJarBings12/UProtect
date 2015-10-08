package ch.jarjarbings12.uprotect.protect.utils.exceptions;

import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 22.09.2015
 * © 2015 JarJarBings12
 */
public class UnknownWorldException extends Exception
{
    public UnknownWorldException(UUID uuid)
    {
       this(Bukkit.getWorld(uuid).getName());
    }

    public UnknownWorldException(String world)
    {
        super(String.format("The World %s don't exist.", world));
    }
}
