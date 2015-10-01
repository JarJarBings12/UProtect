package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

import org.bukkit.event.Event;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
@FunctionalInterface
public interface CallableFlag <E extends Event>
{
    void callFlag(E event);
}
