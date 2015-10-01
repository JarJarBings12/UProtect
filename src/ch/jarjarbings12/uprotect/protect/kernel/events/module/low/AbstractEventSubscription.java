package ch.jarjarbings12.uprotect.protect.kernel.events.module.low;

import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public abstract class AbstractEventSubscription implements BasicSubscription
{
    public abstract void call(Event event);

    public abstract UUID getSubscriberID() throws NotInUseException;

    public void onSubscribe() throws NotInUseException
    {
    }

    public void onUnsubscribe() throws NotInUseException
    {
    }
}
