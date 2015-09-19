package ch.jarjarbings12.uprotect.utils;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.ValueFlag;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author JarJarBings12
 * @creationDate 19.09.2015
 * © 2015 JarJarBings12
 */
public class eventtest implements Listener
{
    private CopyOnWriteArraySet<Flag> d = new CopyOnWriteArraySet<>();

    @EventHandler
    public void e(BlockBreakEvent e)
    {
        Flag dd = ((ValueFlag)UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlag(UUID.fromString("70e997ce-bf4c-40f0-be6e-1b733c76a89c"))).setValue(true);
        d.add(dd);
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlagIDsForEventID(1).forEach(f -> {
            if (fakeRegion.hasFlag(f))
                fakeRegion.getFlag(f).eventTriggeredCall(e);
        });
    }

    private ProtectedChunkRegion fakeRegion = new ProtectedChunkRegion("test", "test", "test", null, null, null, d);
}
