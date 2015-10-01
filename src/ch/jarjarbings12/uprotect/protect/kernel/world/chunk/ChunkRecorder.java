package ch.jarjarbings12.uprotect.protect.kernel.world.chunk;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.CSF.RecordedChunk;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.io.ByteArrayInputStream;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class ChunkRecorder implements BasicChunkService
{
    private final UUID worldUUID;
    private final WorldChunkService worldChunkService;
    private final AsyncRecodingThread recodingThread;

    public ChunkRecorder(UUID worldUUID, WorldChunkService worldChunkService)
    {
        this.worldUUID = worldUUID;
        this.worldChunkService = worldChunkService;
        this.recodingThread = new AsyncRecodingThread(worldUUID, worldChunkService);
        this.recodingThread.setName("REC");
        this.recodingThread.start();
    }

    public void record(int x, int z)
    {
         record(Bukkit.getWorld(worldUUID).getChunkAt(x, z));
    }

    public void record(Chunk chunk)
    {
        recodingThread.queue(chunk);
    }

    public boolean saveRecord(RecordedChunk recordedChunk)
    {
        return worldChunkService.getRecordDatabase().executeInsertStatement(recordedChunk.getX(), recordedChunk.getZ(), UProtect.getUProtect().getUProtectAPI().getServiceCenter().getObjectService().getCompressorService().compress(recordedChunk), System.currentTimeMillis());
    }

    public RecordedChunk getRecord(Chunk chunk)
    {
        return getRecord(chunk.getX(), chunk.getZ());
    }

    public RecordedChunk getRecord(int x, int z)
    {
        return (RecordedChunk)UProtect.getUProtect().getUProtectAPI().getServiceCenter().getObjectService().getCompressorService().decompress(new ByteArrayInputStream(worldChunkService.getRecordDatabase().executeSelectStatement(x, z)));
    }

    public boolean removeRecord(Chunk chunk)
    {
        return removeRecord(chunk.getX(), chunk.getZ());
    }

    public boolean removeRecord(int x, int z)
    {
        return worldChunkService.getRecordDatabase().executeRemoveStatement(x, z);
    }

    public boolean existRecord(Chunk chunk)
    {
        return existRecord(chunk.getX(), chunk.getZ());
    }

    public boolean existRecord(int x, int z)
    {
        return worldChunkService.getRecordDatabase().executeExistStatement(x, z);
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