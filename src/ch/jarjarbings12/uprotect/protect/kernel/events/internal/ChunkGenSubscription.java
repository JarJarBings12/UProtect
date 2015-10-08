package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.event.Event;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 24.09.2015
 * © 2015 JarJarBings12
 */
public class ChunkGenSubscription extends AbstractEventSubscription
{
    @Override
    public void call(Event event)
    {
        ChunkLoadEvent loadEvent = (ChunkLoadEvent) event;
        if (loadEvent.isNewChunk())
        {
            try
            { UProtect.getUProtect().getUProtectAPI().getServiceCenter().getWorldServices().getChunkServiceFor(loadEvent.getWorld().getUID(), false).getChunkRecorder().record(loadEvent.getChunk()); }
            catch (UnknownWorldException e)
            { e.printStackTrace(); }
        }
    }

    @Override
    public UUID getSubscriberID() throws NotInUseException
    {
        return UUID.fromString("0b9f54e9-df2f-4fdb-9f8f-74fbef50ef2d");
    }

    @Override
    public void onSubscribe()
    {
        if (!UProtect.getUProtect().getUProtectAPI().getConfiguration().getBoolean("world.chunk.restore.use"))
            try
            { UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().unsubscribe(200, this.getSubscriberID()); }
            catch (NotInUseException e)
            { e.printStackTrace(); }
        else
            System.out.println("[UProtect][->] Enable: Chunk generate listener.");
    }

    @Override
    public void onUnsubscribe()
    {
        System.out.println("[UProtect][->] Disable: Chunk generate listener.");
    }
}