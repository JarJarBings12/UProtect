package ch.jarjarbings12.uprotect.protect.kernel.world.chunk;

import org.bukkit.World;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public interface BasicChunkService
{
    UUID getWorldUUID();

    World getWorld();

    WorldChunkService getChunkService();
}