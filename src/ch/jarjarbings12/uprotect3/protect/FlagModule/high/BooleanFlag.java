package ch.jarjarbings12.uprotect3.protect.FlagModule.high;

import ch.jarjarbings12.uprotect3.protect.FlagModule.low.*;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by tobias on 05.09.2015.
 */
public class BooleanFlag extends Flag implements ValueFlag<Boolean>, PermissionFlag, CallableFlag<Event>
{
    private final UUID uuid;
    private final String name;
    private Boolean value;
    private final String permission;
    private final ch.jarjarbings12.uprotect3.protect.FlagModule.low.flagCall flagCall;
    private final FlagType flagType = FlagType.BOOLEANFLAG;

    public BooleanFlag(String name, Boolean value, String permission, flagCall<Event, Boolean> flagCall)
    {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.value = value;
        this.permission = permission;
        this.flagCall = flagCall;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public FlagType getType()
    {
        return this.flagType;
    }

    @Override
    public String getPermission()
    {
        return this.permission;
    }

    @Override
    public void setValue(Boolean value)
    {
        this.value = value;
    }

    @Override
    public Boolean getValue()
    {
        return this.value;
    }

    @Override
    public void call(Event event)
    {
        this.flagCall.flagCall(event, this.value);
    }
}
