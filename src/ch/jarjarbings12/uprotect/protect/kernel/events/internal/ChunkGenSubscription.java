package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
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
    private final UUID subid = UUID.fromString("0b9f54e9-df2f-4fdb-9f8f-74fbef50ef2d");

    @Override
    public void call(Event event)
    {
        ChunkLoadEvent loadEvent = (ChunkLoadEvent) event;
        if (loadEvent.isNewChunk())
        {
            UProtect.getUProtect().getUProtectAPI().getServiceCenter().getWorldServices().getChunkServiceFor(loadEvent.getWorld().getUID(), false).getChunkRecorder().record(loadEvent.getChunk());
        }
    }

    @Override
    public UUID getSubscriberID()
    {
        return subid;
    }

    @Override
    public void onSubscribe()
    {
        if (!UProtect.getUProtect().getUProtectAPI().getConfiguration().getBoolean("world.chunk.restore.use"))
            UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().unsubscribe(200, this.getSubscriberID());
        else
            System.out.println("[UProtect][ESM][->] Enable: Chunk generate listener.");
    }

    @Override
    public void onUnsubscribe()
    {
        System.out.println("[UProtect][ESM][->] Disable: Chunk generate listener.");
    }
}