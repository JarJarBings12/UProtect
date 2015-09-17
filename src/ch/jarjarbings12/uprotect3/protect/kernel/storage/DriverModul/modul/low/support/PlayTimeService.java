package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.support;

import java.util.UUID;

/**
 * Created by tobias on 13.09.2015.
 */
public interface PlayTimeService
{
    void setPlayTime(UUID uuid, long time);

    long getPlayTime(UUID uuid);

    void setLastLogin(UUID uuid, long lastLogin);

    long getLastLogin(UUID uuid);
}
