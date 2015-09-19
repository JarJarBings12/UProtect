package ch.jarjarbings12.uprotect.protect.kernel.flag.module.high;

import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.*;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public class BooleanFlag extends Flag implements ValueFlag<Boolean>, PermissionFlag, CallableFlag<Event>
{
    private final UUID flagID;
    private final String name;
    private final String flagTag;
    private Boolean value;
    private final String permission;
    private final ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.flagCall<Event, Boolean> flagCall;

    public BooleanFlag(UUID flagID, String name, String flagTag, Boolean value, String permission, flagCall<Event, Boolean> flagCall)
    {
        this.flagID = flagID;
        this.flagTag = flagTag;
        this.name = name;
        this.value = value;
        this.permission = permission;
        this.flagCall = flagCall;
    }

    @Override
    public UUID getFlagID()
    {
        return this.flagID;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getFlagTag()
    {
        return null;
    }

    @Override
    public void eventTriggeredCall(Event event)
    {
        call(event);
    }

    @Override
    public String getPermission()
    {
        return this.permission;
    }

    @Override
    public Flag setValue(Boolean value)
    {
        this.value = value;
        return this;
    }

    @Override
    public Boolean getValue()
    {
        return this.value;
    }

    @Override
    public void call(Event event)
    {
        this.flagCall.flagCall(event, value);
    }

}
