package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 24.09.2015
 * © 2015 JarJarBings12
 */
public class PlayerBuildSubscription extends AbstractEventSubscription
{
    private final UUID subid = UUID.fromString("9be14ada-5c83-426e-a242-d96bda1ff4eb");

    @Override
    public void call(Event event)
    {
        BlockPlaceEvent blockPlaceEvent = (BlockPlaceEvent)event;
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(blockPlaceEvent.getBlock().getLocation());

        if (!regionDatabase.isProtected(blockPlaceEvent.getBlock().getLocation()))
        {
            blockPlaceEvent.setCancelled(false);
            return;
        }

        if (!regionDatabase.getRegion(blockPlaceEvent.getBlock().getLocation()).hasAccess(blockPlaceEvent.getPlayer().getUniqueId()))
        {
            blockPlaceEvent.getPlayer().sendMessage("[UProtect] Region access denied!");
            blockPlaceEvent.setCancelled(true);
            return;
        }
    }

    @Override
    public UUID getSubscriberID()
    {
        return subid;
    }
}