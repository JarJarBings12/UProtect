package ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit;

import ch.jarjarbings12.uprotect.core.UProtect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author JarJarBings12
 * @creationDate 13.11.2015
 * © 2015 JarJarBings12
 */
public class EntityEvents implements Listener
{
    @EventHandler
    public void onEntityDamageEntityEvent(EntityDamageByEntityEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(50, event);
    }

    @EventHandler
    public void onEntityDamageBlockEvent(EntityDamageByBlockEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(51, event);
    }
}
