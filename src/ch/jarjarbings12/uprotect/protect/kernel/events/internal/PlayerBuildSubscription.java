package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 24.09.2015
 * © 2015 JarJarBings12
 */
public class PlayerBuildSubscription extends AbstractEventSubscription
{
    @Override
    public void call(Event event)
    {

    }

    @Override
    public UUID getSubscriberID() throws NotInUseException
    {
        return UUID.fromString("9be14ada-5c83-426e-a242-d96bda1ff4eb");
    }
}