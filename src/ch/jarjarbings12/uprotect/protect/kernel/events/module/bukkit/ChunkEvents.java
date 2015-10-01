package ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit;

import ch.jarjarbings12.uprotect.core.UProtect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * @author JarJarBings12
 * @creationDate 24.09.2015
 * © 2015 JarJarBings12
 */
public class ChunkEvents implements Listener
{
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().callSubscribers(200, event);
    }
}
