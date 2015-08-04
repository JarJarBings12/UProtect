package ch.jarjarbings12.uprotect.listeners;

import ch.jarjarbings12.uprotect.regions.RegionManager;
import com.sun.jna.platform.win32.NTSecApi;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by tobias on 03.08.2015.
 */
public class PlayerListener implements Listener
{

    private RegionManager regionManager = new RegionManager();

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent e)
    {

        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Player))
            return;

        if (!regionManager.isProtected(e.getEntity().getLocation()) && !regionManager.isProtected(e.getDamager().getLocation()))
            return;

        if (e.getDamager() instanceof Player)
        {
            if (!regionManager.isProtected(e.getEntity().getLocation()))
                return;


            e.setCancelled(true);
        }

    }

    private void throwProtectedEntity(Entity damager, Entity entity)
    {
        if (entity instanceof Player)
            damager.sendMessage("§c[UProtect] ");
    }
}
