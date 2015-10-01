package ch.jarjarbings12.uprotect.protect.kernel.world.chunk.CSF;

import net.minecraft.server.v1_8_R3.ChunkCoordIntPair;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftChunk;
import org.bukkit.entity.EntityType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class RecordedChunk implements Serializable
{
    private final UUID worldUUID;
    private final int x;
    private final int z;
    private final BlockSnapshot[] blockSnapshots;
    private final EntitySnapshot[] entitySnapshots;

    public RecordedChunk(UUID worldUUID, int x, int z, BlockSnapshot[] blockSnapshots, EntitySnapshot[] entitySnapshots)
    {
        this.worldUUID = worldUUID;
        this.x = x;
        this.z = z;
        this.blockSnapshots = blockSnapshots;
        this.entitySnapshots = entitySnapshots;
    }

    public int getX()
    {
        return x;
    }

    public int getZ()
    {
        return z;
    }

    public BlockSnapshot[] getBlockSnapshots()
    {
        return this.blockSnapshots;
    }

    public EntitySnapshot[] getEntitySnapshots()
    {
        return this.entitySnapshots;
    }

    /**
     * Restore this Chunk.
     **/
    public void restore()
    {
        Chunk var1 = Bukkit.getWorld(worldUUID).getChunkAt(x, z);

        int var2 = 0;
        for (int var3 = 0; var3 < 16; var3++)
            for (int var4 = 0; var4 < 16; var4++)
                for (int var5 = 0; var5 < 256; var5++)
                {
                    BlockSnapshot var6 = blockSnapshots[var2];
                    var1.getBlock(var3, var5, var4).setTypeIdAndData(var6.id, var6.data, false);
                    var2++;
                }

        for (EntitySnapshot var9 : entitySnapshots)
            getWorld().spawnEntity(new Location(getWorld(), (double) var9.x, (double) var9.y, (double) var9.z), EntityType.fromId(var9.entityID));
        sendChunkChanges(var1);
    }

    public boolean sendChunkChanges(Chunk chunk)
    {
        if (chunk == null)
            return false;

        int var1, var2, var3;
        var3 = Bukkit.getViewDistance();

        for (EntityPlayer var4 : castHumanEntity(((CraftChunk)chunk).getHandle().getWorld().players))
        {
            var1 = Math.abs((int)var4.locX - chunk.getX());
            var2 = Math.abs((int)var4.locZ - chunk.getZ());
            if (var3 >= var1 && var3 >= var2)
                var4.chunkCoordIntPairQueue.add(new ChunkCoordIntPair(chunk.getX(), chunk.getZ()));
        }
        return true;
    }


    public UUID getWorldUUID()
    {
        return worldUUID;
    }

    public World getWorld()
    {
        return Bukkit.getWorld(worldUUID);
    }

    public List<EntityPlayer> castHumanEntity(List<EntityHuman> humanEntities)
    {
        List<EntityPlayer> var1 = new ArrayList<>();
        humanEntities.forEach(e -> {
            var1.add((EntityPlayer)e);
        });
        return var1;
    }
}