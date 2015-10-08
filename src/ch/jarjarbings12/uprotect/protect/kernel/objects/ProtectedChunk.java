package ch.jarjarbings12.uprotect.protect.kernel.objects;

import java.io.Serializable;

/**
 * @author JarJarBings12
 * @creationDate 03.08.2015
 * © 2015 JarJarBings12
 */
public class ProtectedChunk implements Serializable
{
    private final int X;
    private final int Z;

    public ProtectedChunk(final int X, final int Z)
    {
        this.X = X;
        this.Z = Z;
    }

    public int getX()
    {
        return this.X;
    }

    public int getZ()
    {
        return this.Z;
    }
}
