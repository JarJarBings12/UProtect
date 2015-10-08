package ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit;

import ch.jarjarbings12.uprotect.core.UProtect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author JarJarBings12
 * @creationDate 07.10.2015
 * © 2015 JarJarBings12
 */
public class BlockEvents implements Listener
{

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(0, event);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(1, event);
    }

}