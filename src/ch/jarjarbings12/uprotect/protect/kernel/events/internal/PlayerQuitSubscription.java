package ch.jarjarbings12.uprotect.protect.kernel.events.internal;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 04.10.2015
 * © 2015 JarJarBings12
 */
public class PlayerQuitSubscription extends AbstractEventSubscription
{
    private final UUID subid = UUID.fromString("61b6a427-1b73-44b9-abfc-a844ecf78013");

    @Override
    public void call(Event event)
    {
        PlayerQuitEvent localEvent = (PlayerQuitEvent)event;
        UUID playerUUID = localEvent.getPlayer().getUniqueId();
        long totalPlayTime = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayTimeService().getPlayTime(playerUUID);
        totalPlayTime = totalPlayTime + (System.currentTimeMillis() - UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayTimeService().getLastLogin(playerUUID));
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService().getPlayTimeService().setPlayTime(playerUUID, totalPlayTime);
    }

    @Override
    public UUID getSubscriberID() throws NotInUseException
    {
        return subid;
    }
}