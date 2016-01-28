package ch.jarjarbings12.uprotect.protect.kernel.services.DirectInput;

import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 18.01.2016
 * © 2016 JarJarBings12
 */
final class DirectInputSubscription extends AbstractEventSubscription
{
    final UUID hid;
    final DirectHandler handler;
    final int event;

    DirectInputSubscription(UUID handlerID, DirectHandler directHandler, int event)
    {
        this.hid = handlerID;
        this.handler = directHandler;
        this.event = event;
    }

    @Override
    public void call(Event event)
    {
        this.handler.directHandel(event);
    }

    @Override
    public UUID getSubscriberID()
    {
        return this.hid;
    }
}
