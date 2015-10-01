package ch.jarjarbings12.uprotect.protect.kernel.world.chunk;

import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.RDB.RecordDatabase;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class WorldChunkService
{
    private final UUID worldUUID;
    private final RecordDatabase recordDatabase;
    private final ChunkRecorder chunkRecorder;
    private final ChunkRestorer chunkRestorer;

    public WorldChunkService(UUID worldUUID)
    {
        this.worldUUID = worldUUID;
        this.recordDatabase = new RecordDatabase(this.worldUUID, this);
        this.chunkRecorder = new ChunkRecorder(this.worldUUID, this);
        this.chunkRestorer = new ChunkRestorer(this.worldUUID, this);
        try
        { recordDatabase.setup(); }
        catch (UnknownWorldException e)
        { e.printStackTrace(); }
    }

    public RecordDatabase getRecordDatabase()
    {
        return recordDatabase;
    }

    public ChunkRecorder getChunkRecorder()
    {
        return chunkRecorder;
    }

    public ChunkRestorer getChunkRestorer()
    {
        return chunkRestorer;
    }

    public UUID getWorldUUID()
    {
        return worldUUID;
    }

    public World getWorld()
    {
        return Bukkit.getWorld(worldUUID);
    }
}