package ch.jarjarbings12.uprotect.commands.subcommands;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author JarJarBings12
 * @creationDate 29.10.2015
 * © 2015 JarJarBings12
 */
public class RegionCommands
{
    /**
     * Create new {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void create(CommandSender sender, String label, String[] args)
    {
        RegionDatabase regionDatabase = null;
        Location location = ((Player) sender).getLocation();
        try
        {
            regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        } catch (UnknownWorldException e)
        {
            e.printStackTrace();
        }

        if (regionDatabase.isProtected(location))
        {
            sender.sendMessage("[UProtect] This chunk is already protected.");
            return;
        }

        if (args.length == 2)
        {
            if (regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] This region ID already exists!");
                return;
            }
            Set<ProtectedChunk> chunks = new HashSet<>();
            chunks.add(ProtectedChunk.craftProtectedChunk(location));
            ProtectedChunkRegion protectedChunkRegion = new ProtectedChunkRegion(args[1], location.getWorld().getUID(), chunks);
            regionDatabase.setRegion(protectedChunkRegion.getUUID(), protectedChunkRegion);
            sender.sendMessage("[UProtect] Created new region with id: " + args[1]);
        }
    }

    /**
     * Delete a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void delete(CommandSender sender, String label, String[] args)
    {
        RegionDatabase regionDatabase = null;
        Location location = ((Player) sender).getLocation();
        try
        {
            regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        } catch (UnknownWorldException e)
        {
            e.printStackTrace();
        }

        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            UUID uuid = UUID.fromString(args[1]);
            if (regionDatabase.existRegion(uuid))
            {
                regionDatabase.removeRegion(uuid);
                sender.sendMessage("[UProtect] Deleted region: " + uuid.toString());
                return;
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region uuid: " + uuid.toString().toString());
                return;
            }
        }
        else
        {
            if (regionDatabase.existRegion(args[1]))
            {
                regionDatabase.removeRegion(args[1]);
                sender.sendMessage("[UProtect] Deleted region: " + args[1]);
                return;
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region id: " + args[1]);
                return;
            }
        }
    }

    /**
     * Append a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk} to a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void append(CommandSender sender, String label, String[] args)
    {
        RegionDatabase regionDatabase = null;
        Location location = ((Player) sender).getLocation();
        try
        {
            regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        } catch (UnknownWorldException e)
        {
            e.printStackTrace();
        }
        if (regionDatabase.isProtected(location))
        {
            sender.sendMessage("[UProtect] This chunk is already protected.");
            return;
        }

        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            if (!regionDatabase.existRegion(UUID.fromString(args[1])))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
            ProtectedChunkRegion region = regionDatabase.getRegion(UUID.fromString(args[1]));
            region.addProtectedChunk(new ProtectedChunk(location.getChunk().getX(), location.getChunk().getZ()));
            sender.sendMessage(String.format("[UProtect] Appended chunk(%d/%d) to the region %s", location.getChunk().getX(), location.getChunk().getZ(), region.getId()));
            return;
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region ID!");
                return;
            }
            ProtectedChunkRegion region = regionDatabase.getRegion(args[1]);
            region.addProtectedChunk(new ProtectedChunk(location.getChunk().getX(), location.getChunk().getZ()));
            sender.sendMessage(String.format("[UProtect] Appended chunk(%d/%d) to the region %s", location.getChunk().getX(), location.getChunk().getZ(), region.getId()));
            return;
        }
    }

    /**
     * Remove a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk} from a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void remove(CommandSender sender, String label, String[] args)
    {
        RegionDatabase regionDatabase = null;
        Location location = ((Player) sender).getLocation();
        try
        {
            regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        } catch (UnknownWorldException e)
        {
            e.printStackTrace();
        }
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            if (regionDatabase.existRegion(UUID.fromString(args[1])))
            {
                ProtectedChunkRegion region = regionDatabase.getRegion(UUID.fromString(args[1]));
                if (region.getProtectedChunks().stream().filter(protectedChunk -> protectedChunk.getX() == location.getChunk().getX() && protectedChunk.getZ() == location.getChunk().getZ()).count() > 0)
                {
                    region.removeProtectedChunk(location.getChunk().getX(), location.getChunk().getZ());
                }
                else
                {
                    sender.sendMessage("[UProtect] This chunk isn't a part of this region!");
                }
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
            }
        }
        else
        {
            if (regionDatabase.existRegion(args[1]))
            {
                ProtectedChunkRegion region = regionDatabase.getRegion(args[1]);

                if (region.getProtectedChunks().stream().filter(protectedChunk -> protectedChunk.getX() == location.getChunk().getX() && protectedChunk.getZ() == location.getChunk().getZ()).count() > 0)
                {
                    region.removeProtectedChunk(location.getChunk().getX(), location.getChunk().getZ());
                }
                else
                {
                    sender.sendMessage("[UProtect] This chunk isn't a part of this region!");
                }
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region id!");
            }
        }
    }

    public void list(CommandSender sender, String label, String[] args)
    {
        RegionDatabase regionDatabase = null;
        Location location = ((Player) sender).getLocation();

        try
        {
            regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        } catch (UnknownWorldException e)
        {
            e.printStackTrace();
        }

        List<ProtectedChunkRegion> protectedChunkRegions = new ArrayList<>(regionDatabase.getAllRegions());

        int page = args.length > 1 ? Integer.valueOf(args[1]) : 1;
        int maxPage = (int) Math.floor((protectedChunkRegions.size() + 1) / 10);
        maxPage = maxPage > 1 ? maxPage : 1;

        if (args.length == 1)
        {
            sender.sendMessage("[UProtect] ID / Name");
            if (regionDatabase.getAllRegions().size() > 10)
            {
                for (int i = 0; i < 10; i++)
                    sender.sendMessage(String.format("%s (%s)", protectedChunkRegions.get(i).getId(), protectedChunkRegions.get(i).getName()));
                sender.sendMessage(String.format("[UProtect] List page: %d/%d", page, maxPage));
                return;
            }
            else
            {
                regionDatabase.getAllRegions().forEach(region -> sender.sendMessage(String.format("%s (%s)", region.getId(), region.getName())));
                sender.sendMessage(String.format("[UProtect] List page: %d/%d", page, maxPage));
            }
        }
        else if (args.length == 2)
        {
            if (page > protectedChunkRegions.size() / 10)
            {
                sender.sendMessage(String.format("[UProtect] Can't display page %d/%d", page, maxPage));
                return;
            }

            sender.sendMessage("[UProtect] ID / Name");
            for (int i = page; page < page + 10; i++)
            {
                if (i == protectedChunkRegions.size())
                {
                    break;
                }
                sender.sendMessage(String.format("%s (%s)", protectedChunkRegions.get(i).getId(), protectedChunkRegions.get(i).getName()));
            }
            sender.sendMessage(String.format("[UProtect] List page: %d/%d", page, maxPage));
            return;
        }
    }
}
