package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low;

import ch.jarjarbings12.uprotect2.protect.kernel.managers.storage.RegionDatabase;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;

import java.util.Set;

/**
 * Created by tobias on 23.08.2015.
 */
public interface RegionDBSupport
{
    void saveRegion(ProtectedChunkRegion protectedChunkRegion);

    void saveRegion(ProtectedChunkRegion... protectedChunkRegions);

    void saveRegionDatabase(RegionDatabase regionDatabase);

    void saveRegionDatabase(RegionDatabase... regionDatabases);

    Set<RegionDatabase> getRegionDatabase(String world);

    Set<RegionDatabase> getRegionDatabase(String... world);

    Set<RegionDatabase> getAll();
}
