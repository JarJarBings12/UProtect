package ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.support;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.Flag;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015
 * © 2015 JarJarBings12
 */
public abstract class FlagExtension<E>
{
    public abstract void load();

    public final void register(Flag flag)
    {
        register(flag, null);
    }

    public final void register(Flag flag, int... requiredEvents)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().register(flag.getFlagID(), flag, requiredEvents);
    }

    public final void unregister(UUID flagID)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().unregister(flagID);
    }
}