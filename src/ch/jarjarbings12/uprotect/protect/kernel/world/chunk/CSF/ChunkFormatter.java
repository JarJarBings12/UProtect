package ch.jarjarbings12.uprotect.protect.kernel.world.chunk.CSF;

import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.BasicChunkService;
import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.WorldChunkService;
import org.apache.commons.lang.Validate;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class ChunkFormatter implements BasicChunkService
{
    private final UUID worldUUID;

    public ChunkFormatter(UUID worldUUID)
    {
        this.worldUUID = worldUUID;
    }

    public RecordedChunk parse(Chunk chunk)
    {
        Validate.notNull(chunk);
        BlockSnapshot[] var1 = new BlockSnapshot[65536];
        EntitySnapshot[] var2 = new EntitySnapshot[chunk.getEntities().length];

        int var3 = 0;
        for (int var4 = 0; var4 < 16; var4++)
            for (int var5 = 0; var5 < 16; var5++)
                for (int var6 = 0; var6 < 256; var6++)
                {
                    Block var7 = chunk.getBlock(var4, var6, var5);
                    var1[var3] = new BlockSnapshot(worldUUID, var7.getX(), var7.getY(), var7.getZ(), var7.getTypeId(), var7.getData());
                    var3++;
                }

        int var8 = 0;
        for (Entity var9 : chunk.getEntities())
        {
            var2[var8] = new EntitySnapshot(worldUUID, var9.getLocation().getBlockX(), var9.getLocation().getBlockY(), var9.getLocation().getBlockZ(), var9.getType().getTypeId());
            var8++;
        }
        return new RecordedChunk(worldUUID, chunk.getX(), chunk.getZ(), var1, var2);
    }

    @Override
    public UUID getWorldUUID()
    {
        return null;
    }

    @Override
    public World getWorld()
    {
        return null;
    }

    @Override
    public WorldChunkService getChunkService()
    {
        return null;
    }
}