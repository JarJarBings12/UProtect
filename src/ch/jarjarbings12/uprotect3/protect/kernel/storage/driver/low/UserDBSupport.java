package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by tobias on 26.08.2015.
 */
public interface UserDBSupport extends LastLoginDBSupport
{
    void setNameForUUID(UUID uuid, String name);

    String getNameForUUID(UUID uuid);

    HashMap<UUID, String> getNameForUUID(UUID... uuids);

    boolean existLogsFor(UUID uuid);

}
