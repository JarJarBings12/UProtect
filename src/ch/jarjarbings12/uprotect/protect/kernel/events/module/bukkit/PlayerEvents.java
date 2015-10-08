package ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit;

import ch.jarjarbings12.uprotect.core.UProtect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author JarJarBings12
 * @creationDate 03.10.2015
 * © 2015 JarJarBings12
 */
public class PlayerEvents implements Listener
{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        switch (event.getAction())
        {
            case LEFT_CLICK_BLOCK:
                UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(2, event);
            case RIGHT_CLICK_BLOCK:
                UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(3, event);
            case LEFT_CLICK_AIR:
                UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(4, event);
            case RIGHT_CLICK_AIR:
                UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(5, event);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(20, event);
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(21, event);
    }

}