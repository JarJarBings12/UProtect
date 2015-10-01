package ch.jarjarbings12.uprotect.protect.kernel.world.chunk.CSF;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 28.09.2015
 * © 2015 JarJarBings12
 */
class BlockSnapshot implements Serializable
{
    final UUID world;
    final int x;
    final int y;
    final int z;
    final int id;
    final byte data;

    protected BlockSnapshot(UUID world, int x, int y, int z, int id, byte data)
    {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.data = data;
    }
}