package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low;

import java.util.UUID;

/**
 * Created by tobias on 30.08.2015.
 */
public interface LastLoginDBSupport
{
    long getLastLogin(UUID uuid);

    void setLastLogin(UUID uuid);

    void setLastLogin(UUID uuid, long timestamp);
}
