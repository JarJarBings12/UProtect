package ch.jarjarbings12.uprotect3.protect.FlagModule.low;

import org.bukkit.event.Event;

/**
 * Created by tobias on 05.09.2015.
 */
public abstract class flagCall<E extends Event, T>
{
    public abstract void flagCall(E event, T value);
}
