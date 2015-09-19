package ch.jarjarbings12.uprotect.protect.kernel.services.interfaces;

import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.DifferenceType;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.IndexDifference;

import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 19.08.2015
 * © 2015 JarJarBings12
 */
public interface IDifferenceService
{
    void addDifference(ProtectedChunkRegion region, DifferenceType differenceType);

    IndexDifference getDifferences(UUID key);

    Set<IndexDifference> getDifferences(UUID... key);

    Set<IndexDifference> getAllDifferences();

    void clearDifferences();
}
