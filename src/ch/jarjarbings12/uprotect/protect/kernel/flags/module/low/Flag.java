package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public abstract class Flag extends AFlagEventSupport
{
    public abstract UUID getFlagID();

    public abstract String getName();

    public abstract String getFlagTag();

    public void eventTriggeredCall(Event event)
    {
    }

    public UUID getSubscriberID() throws NotInUseException
    {
        throw new NotInUseException("This event doesn't use any event subscriptions.");
    }

    public void onSubscribe() throws NotInUseException
    {
        throw new NotInUseException("This event doesn't use any event subscriptions.");
    }

    public void onUnsubscribe() throws NotInUseException
    {
        throw new NotInUseException("This event doesn't use any event subscriptions.");
    }
}
