package ch.jarjarbings12.uprotect.utils;

import ch.jarjarbings12.uprotect.protect.kernel.flags.module.high.BooleanFlag;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.flagCall;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.support.FlagExtension;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 19.09.2015
 * © 2015 JarJarBings12
 */
public class test extends FlagExtension
{
    public Flag build = new BooleanFlag(UUID.fromString("70e997ce-bf4c-40f0-be6e-1b733c76a89c"), "build", "BUILD", true, "uprotect.flags.build.set", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Event event, Boolean value)
        {
            System.out.println("cancle");
            if (event instanceof BlockPlaceEvent)
            {
                BlockPlaceEvent e1 = (BlockPlaceEvent) event;
                if (value)
                {
                    e1.getPlayer().sendMessage("[UProtect] No Build");
                    e1.setCancelled(true);
                    return;
                }

                e1.setCancelled(false);
            }
            else if (event instanceof BlockBreakEvent)
            {
                BlockBreakEvent e1 = (BlockBreakEvent) event;

                if (value)
                {
                    e1.getPlayer().sendMessage("[UProtect] No Build");
                    e1.setCancelled(true);
                    return;
                }
            }
        }
    });

    @Override
    public void load()
    {
        register(build, 1);
    }

}
