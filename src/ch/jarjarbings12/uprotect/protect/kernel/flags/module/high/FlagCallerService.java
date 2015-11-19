package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 20.09.2015
 * © 2015 JarJarBings12
 */
public class FlagCallerService extends AbstractEventSubscription
{

    private UUID subscriberID;
    private int eventID;

    public FlagCallerService(int eventID)
    {
        this.subscriberID = UUID.randomUUID();
        this.eventID = eventID;
    }

    /**
     * @return Return the event ID where the Service listen to.
     **/
    public int getEventID()
    {
        return this.eventID;
    }

    @Override
    public void call(Event event)
    {
        Location location = getLocation(event);
        RegionDatabase db = null;
        try
        { db = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(getLocation(event).getWorld().getName()); }
        catch (UnknownWorldException e)
        { e.printStackTrace(); }

        if (db.isProtected(location))
        {
            ProtectedChunkRegion pcr = db.getRegion(location);
            UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlagIDsForEventID(eventID)
                    .forEach(flag -> {
                        if (pcr.hasFlag(flag))
                            pcr.getFlag(flag).eventTriggeredCall(event);
                    });
        }
    }

    @Override
    public UUID getSubscriberID()
    {
        return this.subscriberID;
    }

    private Location getLocation(Event event)
    {
        if (event instanceof PlayerEvent)
            return ((PlayerEvent)event).getPlayer().getLocation();
        else if (event instanceof EntityEvent)
            return ((EntityEvent)event).getEntity().getLocation();
        else if (event instanceof BlockEvent)
            return ((BlockEvent)event).getBlock().getLocation();
        else
            throw new UnsupportedOperationException("Not supported yet.");
    }
}
