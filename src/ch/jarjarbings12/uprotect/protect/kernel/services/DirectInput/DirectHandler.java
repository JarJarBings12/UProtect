package ch.jarjarbings12.uprotect.protect.kernel.services.DirectInput;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.SubscriptionManager;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.Events;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 18.01.2016
 * © 2016 JarJarBings12
 */
public abstract class DirectHandler
{
    private final UUID hid;
    private final int[] requiredEvents;
    private final SubscriptionManager subscriptionManager = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager();

    public DirectHandler(UUID handlerID, int... requiredEvents)
    {
        this.hid = handlerID;
        this.requiredEvents = requiredEvents;
    }

    public DirectHandler(UUID handlerID, Events... requiredEvents)
    {
        this.hid = handlerID;
        this.requiredEvents = new int[requiredEvents.length];
        for (int i = 0; i < requiredEvents.length; i++)
            this.requiredEvents[i] = requiredEvents[i].getID();
    }

    public abstract void directHandel(Event event);

    public void destroy()
    {
        for (int event : requiredEvents)
            subscriptionManager.unsubscribe(event, hid);
    }

    public final void enable()
    {
        for (int event : requiredEvents)
            subscriptionManager.subscribe(event, new DirectInputSubscription(hid, this, event));
    }

    public final int[] getRequiredEvents()
    {
        return this.requiredEvents;
    }

    public final UUID getHandlerID()
    {
        return this.hid;
    }
}
