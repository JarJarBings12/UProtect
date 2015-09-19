package ch.jarjarbings12.uprotect.protect.kernel.flag.module.low;

import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public abstract class Flag
{
    public abstract UUID getFlagID();

    public abstract String getName();

    public abstract String getFlagTag();

    public abstract void eventTriggeredCall(Event event);
}
