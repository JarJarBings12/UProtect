package ch.jarjarbings12.uprotect.protect.kernel.events.module.low;

import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public abstract class AbstractEventSubscription implements BasicSubscription
{
    private SubscriptionPriority priority = SubscriptionPriority.NORMAL;

    private boolean ignoreCancelled = false;

    public final SubscriptionPriority getPriority()
    {
        return priority;
    }

    public final boolean ignoreCancelled()
    {
        return ignoreCancelled;
    }

    public final void setPriority(SubscriptionPriority priority)
    {
        this.priority = priority;
    }

    public final void setIgnoreCancelled(boolean bool)
    {
        this.ignoreCancelled = bool;
    }

    public abstract void call(Event event);

    public abstract UUID getSubscriberID();

    public void onSubscribe()
    {
    }

    public void onUnsubscribe()
    {
    }
}
