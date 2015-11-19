package ch.jarjarbings12.uprotect.protect.kernel.objects;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author JarJarBings12
 * @creationDate 03.08.2015
 * © 2015 JarJarBings12
 */
public class ProtectedChunk
{
    private final int X;
    private final int Z;

    public ProtectedChunk(int X, int Z)
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

    /**
     * Create a protected chunk.
     * @param player
     * @return
     */
    public static ProtectedChunk craftProtectedChunk(Player player)
    {
        return craftProtectedChunk(player.getLocation());
    }

    /**
     * Create a protected chunk.
     * @param location
     * @return
     */
    public static ProtectedChunk craftProtectedChunk(Location location)
    {
        return craftProtectedChunk(location.getChunk());
    }

    /**
     * Create a protected chunk.
     * @param chunk
     * @return
     */
    public static ProtectedChunk craftProtectedChunk(Chunk chunk)
    {
        return new ProtectedChunk(chunk.getX(), chunk.getZ());
    }

    public static ProtectedChunk craftProtectedChunk(String string)
    {
        String[] split = string.split(":");
        return new ProtectedChunk(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }

    @Override
    public String toString()
    {
        return this.X + ":" + this.Z;
    }
}
