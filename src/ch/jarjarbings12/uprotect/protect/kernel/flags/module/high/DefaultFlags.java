package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.Events;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.flagCall;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.support.FlagExtension;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public class DefaultFlags extends FlagExtension
{

    @Override
    public void load()
    {
        System.out.println("CALL");
        this.register(build, Events.BLOCK_BREAK_EVENT.getID(), Events.BLOCK_PLACE_EVENT.getID());
        this.register(pvp, Events.ENTITY_DAMAGE_BY_ENTITY.getID());
        this.register(aggressiveMobKilling, Events.ENTITY_DAMAGE_BY_ENTITY.getID());
        this.register(neutralMobKilling, Events.ENTITY_DAMAGE_BY_ENTITY.getID());
    }

    public final Flag pvp = new BooleanFlag(UUID.fromString("5ac1c3ed-1ba1-4896-b14b-a778ac55a391"), "PVP", false, "uprotect.flags.pvp", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Flag flag, Event event, Boolean value)
        {
            EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent)event;

            if (!(entityDamageByEntityEvent.getEntity() instanceof Player))
            {
                entityDamageByEntityEvent.setCancelled(false);
            }

            if (value)
            {
                entityDamageByEntityEvent.setCancelled(false);
            }
            else
            {
                entityDamageByEntityEvent.getDamager().sendMessage("[UProtect] Pvp isn't enabled in this region!");
                entityDamageByEntityEvent.setCancelled(true);
            }
        }
    });

    public final Flag neutralMobKilling = new BooleanFlag(UUID.fromString("dd886295-1975-48eb-ac3b-c1c3f070e85d"), "ALLOW-NEUTRAL-MOB-KILLING", false, "uprotect.flags.set.pvp", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Flag flag, Event event, Boolean value)
        {
            EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent)event;
            if (entityEvent.getEntity() instanceof Monster)
                return;

            if (value)
                return;
            entityEvent.setCancelled(true);
        }
    });

    public final Flag aggressiveMobKilling = new BooleanFlag(UUID.fromString("718897f5-820b-43d7-be57-6533953710a9"), "ALLOW-AGGRESSIVE-MOB-KILLING", false, "uprotect.flags.set.pvp", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Flag flag, Event event, Boolean value)
        {
            EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent)event;

            if (entityEvent.getEntity() instanceof Animals)
                return;

            if (value)
                return;
            entityEvent.setCancelled(true);
        }
    });

    public final Flag build = new BooleanFlag(UUID.fromString("70e997ce-bf4c-40f0-be6e-1b733c76a89c"), "BUILD", true, "uprotect.flags.build.set", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Flag flag, Event event, Boolean value)
        {
            if (value)
                return;
            if (event instanceof BlockPlaceEvent)
            {
                BlockPlaceEvent e1 = (BlockPlaceEvent) event;
                e1.getPlayer().sendMessage("[UProtect] No Build");
                e1.setCancelled(true);
            }
            else if (event instanceof BlockBreakEvent)
            {
                BlockBreakEvent e1 = (BlockBreakEvent) event;
                e1.getPlayer().sendMessage("[UProtect] No Build");
                e1.setCancelled(true);
            }
        }
    });
}
