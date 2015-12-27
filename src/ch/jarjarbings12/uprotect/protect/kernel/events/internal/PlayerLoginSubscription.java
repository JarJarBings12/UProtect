package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.SubscriptionPriority;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.DriverServices;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 04.10.2015
 * © 2015 JarJarBings12
 */
public class PlayerLoginSubscription extends AbstractEventSubscription
{
    private final UUID subid = UUID.fromString("d7ab0505-6e42-45cc-bef5-5bca989ed9d9");
    private DriverServices driverServices = null;

    public PlayerLoginSubscription()
    {
        setPriority(SubscriptionPriority.MONITOR);
    }

    @Override
    public void call(Event event)
    {
        PlayerJoinEvent playerJoinEvent = (PlayerJoinEvent)event;
        Player player = playerJoinEvent.getPlayer();

        if(driverServices.getUserDBService().getPlayerUUIDService().isUUIDRegistered(player.getUniqueId()))
        {
            driverServices.getUserDBService().getPlayerUUIDService().setUserNameFor(player.getUniqueId(), player.getName());
            driverServices.getUserDBService().getPlayTimeService().setLastLogin(player.getUniqueId(), System.currentTimeMillis());
            return;
        }
        else
        {
            driverServices.getUserDBService().getPlayerUUIDService().setUserNameFor(player.getUniqueId(), player.getName());
            driverServices.getUserDBService().getPlayTimeService().setLastLogin(player.getUniqueId(), System.currentTimeMillis());
            driverServices.getUserDBService().getPlayTimeService().setFirstLogin(player.getUniqueId(), System.currentTimeMillis());
        }
    }

    @Override
    public UUID getSubscriberID()
    {
        return subid;
    }

    public void onSubscribe()
    {
        driverServices = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices();
    }

}