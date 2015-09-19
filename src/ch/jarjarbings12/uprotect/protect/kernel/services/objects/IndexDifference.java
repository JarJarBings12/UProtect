package ch.jarjarbings12.uprotect.protect.kernel.services.objects;

import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;

/**
 * @author JarJarBings12
 * @creationDate 29.08.2015
 * © 2015 JarJarBings12
 */
public class IndexDifference
{
    private ProtectedChunkRegion region = null;
    private DifferenceType differenceType;

    public IndexDifference(ProtectedChunkRegion region, DifferenceType differenceType)
    {
        this.region = region;
        this.differenceType = differenceType;
    }

    public ProtectedChunkRegion getRegion()
    {
        return this.region;
    }

    public void setRegion(ProtectedChunkRegion region)
    {
        this.region = region;
    }

    public DifferenceType getDifferenceType()
    {
        return this.differenceType;
    }

    public void setDifferenceType(DifferenceType type)
    {
        this.differenceType = type;
    }
}
