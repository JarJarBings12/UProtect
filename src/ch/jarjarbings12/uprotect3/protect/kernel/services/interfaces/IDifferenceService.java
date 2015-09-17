package ch.jarjarbings12.uprotect3.protect.kernel.services.interfaces;

import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect3.protect.kernel.services.objects.DifferenceType;
import ch.jarjarbings12.uprotect3.protect.kernel.services.objects.IndexDifference;

import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.08.2015
 * @since 1.0.0.0
 */
public interface IDifferenceService
{
    void addDifference(ProtectedChunkRegion region, DifferenceType differenceType);

    IndexDifference getDifferences(UUID key);

    Set<IndexDifference> getDifferences(UUID... key);

    Set<IndexDifference> getAllDifferences();

    void clearDifferences();
}
