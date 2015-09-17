package ch.jarjarbings12.uprotect3.protect.kernel.objects;

import org.bukkit.Chunk;

/**
 * @author JarJarBings12
 * @creationDate 3.08.2015
 * @since 1.0.0.0
 */
public class ProtectedChunk
{
    private final int X;
    private final int Z;
    private final Chunk chunk;

    public ProtectedChunk(final int X, final int Z, final Chunk chunk)
    {
        this.X = X;
        this.Z = Z;
        this.chunk = chunk;
    }

    public int getX()
    {
        return this.X;
    }

    public int getZ()
    {
        return this.Z;
    }

    public Chunk getChunk()
    {
        return this.chunk;
    }
}
