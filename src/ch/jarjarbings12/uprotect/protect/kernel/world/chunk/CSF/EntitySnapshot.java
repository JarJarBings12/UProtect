package ch.jarjarbings12.uprotect.protect.kernel.world.chunk.CSF;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 28.09.2015
 * © 2015 JarJarBings12
 */
class EntitySnapshot implements Serializable
{
    final UUID world;
    final int x;
    final int y;
    final int z;
    final short entityID;

    protected EntitySnapshot(UUID world, int x, int y, int z, short entityID)
    {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityID = entityID;
    }
}