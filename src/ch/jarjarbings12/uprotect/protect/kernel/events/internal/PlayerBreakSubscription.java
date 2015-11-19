package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 12.11.2015
 * � 2015 JarJarBings12
 */
public class PlayerBreakSubscription extends AbstractEventSubscription
{

    private final UUID subid = UUID.fromString("d120a705-c64c-489d-934f-eeb906299502");

    @Override
    public void call(Event event)
    {
        BlockBreakEvent blockBreakEvent = (BlockBreakEvent) event;
        RegionDatabase regionDatabase = null;
        try
        { regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(blockBreakEvent.getBlock().getLocation()); }
        catch (UnknownWorldException e)
        { e.printStackTrace(); }

        if (!regionDatabase.isProtected(blockBreakEvent.getBlock().getLocation()))
        {
            blockBreakEvent.setCancelled(false);
            return;
        }

        if (!regionDatabase.getRegion(blockBreakEvent.getBlock().getLocation()).hasAccess(blockBreakEvent.getPlayer().getUniqueId()))
        {
            blockBreakEvent.getPlayer().sendMessage("[UProtect] Region access denied!");
            blockBreakEvent.setCancelled(true);
            return;
        }

        blockBreakEvent.setCancelled(false);
        return;
    }

    @Override
    public UUID getSubscriberID() throws NotInUseException
    {
        return subid;
    }
}
