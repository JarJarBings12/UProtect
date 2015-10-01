package ch.jarjarbings12.uprotect.protect.kernel.world.chunk;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class ChunkRestorer implements BasicChunkService
{
    private final UUID worldUUID;
    private final WorldChunkService worldChunkService;

    public ChunkRestorer(UUID worldUUID, WorldChunkService worldChunkService)
    {
        this.worldUUID = worldUUID;
        this.worldChunkService = worldChunkService;
    }

    public boolean restore(Chunk chunk)
    {
        return restore(chunk.getX(), chunk.getZ());
    }

    public boolean restore(int x, int z)
    {
        if (!worldChunkService.getRecordDatabase().executeExistStatement(x, z))
            return false;
        worldChunkService.getChunkRecorder().getRecord(x, z).restore();
        return true;
    }

    public boolean existRecord(Chunk chunk)
    {
        return existRecord(chunk.getX(), chunk.getZ());
    }

    private boolean existRecord(int x, int z)
    {
        return worldChunkService.getChunkRecorder().existRecord(x, z);
    }

    public UUID getWorldUUID()
    {
        return this.worldUUID;
    }

    public World getWorld()
    {
        return Bukkit.getWorld(worldUUID);
    }

    public WorldChunkService getChunkService()
    {
        return this.worldChunkService;
    }
}