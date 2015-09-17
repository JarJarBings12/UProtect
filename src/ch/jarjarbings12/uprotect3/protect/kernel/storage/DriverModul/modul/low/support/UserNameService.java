package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.support;

import java.util.UUID;

/**
 * Created by tobias on 13.09.2015.
 */
public interface UserNameService
{
    void setUserNameFor(UUID uuid, String name);

    String getUserNameFor(UUID uuid);

    UUID getUUIDFor(String name);
}
