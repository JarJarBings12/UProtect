package ch.jarjarbings12.uprotect3.protect.kernel.services.interfaces;

import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect3.protect.kernel.services.objects.DifferenceType;
import ch.jarjarbings12.uprotect3.protect.kernel.services.objects.IndexDifference;

import java.util.Set;
import java.util.UUID;

/**
 * Created by tobias on 29.08.2015.
 */
public interface IDifferenceService
{
    void addDifference(ProtectedChunkRegion region, DifferenceType differenceType);

    IndexDifference getDifferences(UUID key);

    Set<IndexDifference> getDifferences(UUID... key);

    Set<IndexDifference> getAllDifferences();

    void clearDifferences();
}
