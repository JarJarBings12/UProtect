package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.SubscriptionPriority;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 20.09.2015
 * © 2015 JarJarBings12
 */
public abstract class AFlagEventSupport extends AbstractEventSubscription
{
    AFlagEventSupport()
    { setPriority(SubscriptionPriority.MEDIUM); }

    @Override
    public final void call(Event event)
    {
        eventTriggeredCall(event);
    }

    @Override
    public UUID getSubscriberID()
    {
        return UUID.fromString("00000000-0000-0000-0000-000000000000");
    }

    public abstract void eventTriggeredCall(Event e);
}
