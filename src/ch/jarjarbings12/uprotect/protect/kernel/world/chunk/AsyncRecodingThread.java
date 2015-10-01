package ch.jarjarbings12.uprotect.protect.kernel.world.chunk;

import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.CSF.ChunkFormatter;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class AsyncRecodingThread extends Thread implements BasicChunkService
{
    private final UUID worldUUID;
    private final WorldChunkService chunkService;
    private final ChunkFormatter chunkFormatter;
    private final Queue<Chunk> pool = new ConcurrentLinkedDeque<>();
    private boolean run = true;
    private boolean pause = false;
    private boolean fastMode = false;

    protected AsyncRecodingThread(UUID worldUUID, WorldChunkService chunkService)
    {
        this.worldUUID = worldUUID;
        this.chunkService = chunkService;
        this.chunkFormatter = new ChunkFormatter(this.worldUUID);
    }

    public synchronized void queue(Chunk chunk)
    {
        this.pool.add(chunk);
    }

    public synchronized Chunk poll()
    {
        return pool.poll();
    }

    public synchronized Queue<Chunk> getPool()
    {
        return pool;
    }

    public boolean isFastMode()
    {
        return fastMode;
    }

    public boolean isRun()
    {
        return run;
    }

    public void setFastMode(boolean fastMode)
    {
        this.fastMode = fastMode;
    }

    public void shutdown()
    {
        this.run = false;
    }

    public void pauseProcess()
    {
        this.pause = true;
    }

    public void startProcess()
    {
        this.pause = false;
    }

    @Override
    public UUID getWorldUUID()
    {
        return this.worldUUID;
    }

    @Override
    public World getWorld()
    {
        return Bukkit.getWorld(worldUUID);
    }

    @Override
    public WorldChunkService getChunkService()
    {
        return null;
    }

    @Override
    public void run()
    {
        while (run)
        {
            while (!pause)
            {
                if (!fastMode)
                {
                    if (getPool().size() > 0)
                        for (int var1 = 0; var1 < 5; var1++)
                            if (getPool().size() != 0)
                                chunkService.getChunkRecorder().saveRecord(chunkFormatter.parse(poll()));
                            else
                                continue;
                    pause(2000);
                }
                else
                {
                    if (getPool().size() > 0)
                        for (int var2 = 0; var2 < 10; var2++)
                            if (getPool().size() != 0)
                                chunkService.getChunkRecorder().saveRecord(chunkFormatter.parse(poll()));
                            else
                                continue;
                }
            }
        }
    }

    protected void pause(long mills)
    {
        try
        { sleep(mills); }
        catch (InterruptedException e)
        { e.printStackTrace(); }
    }
}