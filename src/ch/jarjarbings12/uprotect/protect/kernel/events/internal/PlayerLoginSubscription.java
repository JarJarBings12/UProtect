package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
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

    @Override
    public void call(Event event)
    {
        PlayerJoinEvent localEvent = (PlayerJoinEvent)event;

        Player player = localEvent.getPlayer();
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayTimeService().setLastLogin(localEvent.getPlayer().getUniqueId(), System.currentTimeMillis());

        if (!UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayerUUIDService().isUUIDRegistered(player.getUniqueId()))
        {
            UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayTimeService().setPlayTime(player.getUniqueId(), 0);
            UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayerUUIDService().setUserNameFor(player.getUniqueId(), player.getName());
        }
        else
        {
            if (!UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayerUUIDService().isNameCurrently(player.getUniqueId(), player.getName()))
                UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayerUUIDService().setUserNameFor(player.getUniqueId(), player.getName());
        }
    }

    @Override
    public UUID getSubscriberID() throws NotInUseException
    {
        return subid;
    }
}