package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.core.UProtectAPI;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public class DefaultFlags
{
    private UProtectAPI api = UProtect.getUProtect().getUProtectAPI();
/*
    private Flag pvp = new BooleanFlag("pvp", false, "uprotect.flags.pvp.set", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Event event, Boolean value)
        {
            EntityDamageByEntityEvent e1 = (EntityDamageByEntityEvent) event;

            if (value)
            {
                e1.setCancelled(false);
            }
            else
            {
                e1.getDamager().sendMessage("[UProtectCommand] No PVP");
                e1.setCancelled(true);
            }
        }
    });

    private Flag build = new BooleanFlag("build", false, "uprotect.flags.build.set", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Event event, Boolean value)
        {
            if (event instanceof BlockPlaceEvent)
            {
                BlockPlaceEvent e1 = (BlockPlaceEvent) event;
                if (value)
                {
                    e1.getPlayer().sendMessage("[UProtectCommand] No Build");
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
                    e1.getPlayer().sendMessage("[UProtectCommand] No Build");
                    e1.setCancelled(true);
                    return;
                }
            }
        }
    });
    */
}
