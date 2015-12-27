package ch.jarjarbings12.uprotect.protect.kernel.objects;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.high.FlagService;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.utils.HashMapFactory;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 03.08.2015
 * © 2015 JarJarBings12
 */
public class ProtectedChunkRegion
{
    private final UUID uuid;
    private final String id;
    private final UUID world;
    private String name;
    private Set<ProtectedChunk> protectedChunks = new HashSet<>();
    private Set<UUID> owners;
    private Set<UUID> members;
    private Set<Flag> flags;

    public ProtectedChunkRegion(String id, UUID worldUUID)
    {
        this.uuid = UUID.randomUUID();
        this.id = id;
        this.world = worldUUID;
        this.name = id;
        this.protectedChunks = new HashSet<>();
        this.owners = new HashSet<>();
        this.members = new HashSet<>();
        this.flags = new HashSet<>();
    }

    public ProtectedChunkRegion(String id, UUID worldUUID,Set<ProtectedChunk> protectedChunks)
    {
        this.uuid = UUID.randomUUID();
        this.id = id;
        this.world = worldUUID;
        this.name = id;
        this.protectedChunks = protectedChunks;
        this.owners = new HashSet<>();
        this.members = new HashSet<>();
        this.flags = new HashSet<>();
    }

    public ProtectedChunkRegion(String id, String name, UUID worldUUID, Set<ProtectedChunk> protectedChunks, Set<UUID> owners, Set<UUID> members, Set<Flag> flags)
    {
        this.uuid = UUID.randomUUID();
        this.id = id;
        this.name = name;
        this.world = worldUUID;
        this.protectedChunks = protectedChunks;
        this.owners = owners;
        this.members = members;
        this.flags = flags;
    }

    public ProtectedChunkRegion(UUID regionUUID, String id, String name, UUID worldUUID, Set<ProtectedChunk> protectedChunks, Set<UUID> owners, Set<UUID> members, Set<Flag> flags)
    {
        this.uuid = regionUUID;
        this.id = id;
        this.name = name;
        this.world = worldUUID;
        this.protectedChunks = protectedChunks;
        this.owners = owners;
        this.members = members;
        this.flags = flags;
    }

    public final UUID getUUID()
    {
        return this.uuid;
    }

    public final String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public final World getWorld()
    {
        return Bukkit.getWorld(world);
    }

    public void setName(String name)
    {
        this.name = name;
        update();
        return;
    }

    public Set<ProtectedChunk> getProtectedChunks()
    {
        return this.protectedChunks;
    }

    public ProtectedChunk getProtectedChunk(int x, int z)
    {
        return this.protectedChunks.stream().filter(c -> c.getX() == x && c.getZ() == z).findFirst().get();
    }

    public void addProtectedChunk(ProtectedChunk protectedChunk)
    {
        this.protectedChunks.add(protectedChunk);
        update();
        return;
    }

    public boolean removeProtectedChunk(int x, int z)
    {
        boolean var1 = this.protectedChunks.removeIf(pc -> pc.getX() == x && pc.getZ() == z);
        if (var1)
            update();
        return var1;
    }

    public void setProtectedChunks(Set<ProtectedChunk> protectedChunks)
    {
        this.protectedChunks = protectedChunks;
        update();
        return;
    }

    public boolean hasAccess(UUID uuid)
    {
        return (isMember(uuid) || isOwner(uuid)) || Bukkit.getPlayer(uuid).hasPermission("uprotect.region.bypass");
    }

    public boolean isOwner(UUID uuid)
    {
        return this.owners.contains(uuid);
    }

    public boolean isMember(UUID uuid)
    {
        return this.members.contains(uuid);
    }

    public Set<UUID> getOwners()
    {
        return this.owners;
    }

    public void addOwner(UUID uuid)
    {
        if (isOwner(uuid))
            return;

        this.owners.add(uuid);
        return;
    }

    public boolean removeOwner(UUID uuid)
    {
        if (!isOwner(uuid))
            return false;
        this.owners.remove(uuid);
        update();
        return true;
    }

    public void setOwners(Set<UUID> owners)
    {
        this.owners = owners;
        update();
        return;
    }

    public Set<UUID> getMembers()
    {
        return this.members;
    }

    public void addMember(UUID uuid)
    {
        if (isMember(uuid))
            return;

        this.members.add(uuid);
        update();
        return;
    }

    public boolean removeMember(UUID uuid)
    {
        if (!isMember(uuid))
            return false;
        this.members.remove(uuid);
        update();
        return true;
    }

    public void setMembers(Set<UUID> members)
    {
        this.members = members;
        update();
        return;
    }

    public Set<Flag> getFlags()
    {
        return this.flags;
    }

    public boolean hasFlag(UUID uuid)
    {

        for (Flag flag : this.flags)
            if (flag.getFlagID().equals(uuid))
            {
                return true;
            }

        return false;
    }

    public void addFlag(Flag flag)
    {
        if (hasFlag(flag.getFlagID()))
            removeFlag(flag.getFlagID());
        this.flags.add(flag);
        update();
        return;
    }

    public Flag getFlag(UUID flagID)
    {
        if (!flags.isEmpty())
            return flags.stream().filter(f -> f.getFlagID().equals(flagID)).findFirst().get();
        return null;
    }

    public boolean removeFlag(UUID flagID)
    {
        boolean var1 = this.flags.removeIf(f -> f.getFlagID().equals(flagID));
        if (var1)
            update();
        return var1;
    }

    public void setFlags(Set<Flag> flags)
    {
        this.flags = flags;
        update();
        return;
    }

    public MProtectedChunkRegion modify()
    {
        return new MProtectedChunkRegion(this);
    }

    public void update()
    {
        getRegionDatabase().setRegion(this.uuid, this);
    }

    public void delete()
    {
        if (getRegionDatabase().existRegion(getUUID()))
            getRegionDatabase().removeRegion(getUUID());
    }

    public RegionDatabase getRegionDatabase()
    {
        return UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(world);
    }

    public String serialize()
    {
        JSONObject object = new JSONObject();

        object.put("rinfo", new JSONObject(new HashMapFactory<>()
                .put("uuid", uuid.toString())
                .put("id", id)
                .put("name", name)
                .put("world", world.toString())
                .getHashMap()));

        JSONArray chunks = new JSONArray();

        protectedChunks.forEach(protectedChunk -> chunks.add(protectedChunk.toString()));
        object.put("chunks", chunks);

        JSONObject user = new JSONObject();
        JSONArray member = new JSONArray();
        JSONArray owner = new JSONArray();

        this.members.forEach(var1 -> member.add(var1.toString()));
        this.owners.forEach(var1 -> member.add(var1.toString()));

        user.put("member", member);
        user.put("owner", owner);
        object.put("user", user);

        JSONArray flags = new JSONArray();
        this.flags.forEach(flag -> flags.add(flag.serialize()));

        object.put("flags", flags);

        return object.toString();
    }

    public static ProtectedChunkRegion deserialize(final String value)
    {
        JSONParser parser = new JSONParser();
        JSONObject object;
        ProtectedChunkRegion chunkRegion = null;
        FlagService flagService = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService();

        try
        {
            object = (JSONObject)parser.parse(value);
            JSONObject temp = (JSONObject) object.get("user");

            //---------------------------------
            Set<ProtectedChunk> chunks = new HashSet<>();
            ((JSONArray)object.get("chunks")).forEach(chunk -> chunks.add(ProtectedChunk.craftProtectedChunk((String)chunk)));
            Set<UUID> members = new HashSet<>();
            ((JSONArray)temp.get("member")).forEach(member -> members.add(UUID.fromString((String)member)));
            Set<UUID> owners = new HashSet<>();
            ((JSONArray)temp.get("owner")).forEach(owner -> owners.add(UUID.fromString((String)owner)));
            final Set<Flag> flags = new HashSet<>();
            ((JSONArray)object.get("flags")).forEach(flag -> flags.add(flagService.getFlag(UUID.fromString((String) ((JSONObject) flag).get("fid"))).deserialize(((JSONObject) flag))));
            //---------------------------------

            temp = (JSONObject) object.get("rinfo");
            chunkRegion = new ProtectedChunkRegion(UUID.fromString((String)temp.get("uuid")), (String)temp.get("id"), (String)temp.get("name"), UUID.fromString((String)temp.get("world")), chunks, owners, members, flags);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return chunkRegion;
    }
}