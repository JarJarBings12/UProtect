package ch.jarjarbings12.uprotect3.protect.kernel.events.module.low;

import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by tobias on 15.09.2015.
 */
public abstract class AbstractEventSubscription implements BasicSubscription
{
    public abstract void call(Event event);

    public abstract UUID getSubscriberID();

    public void onSubscribe()
    {}

    public void onUnsubscribe()
    {}
}
