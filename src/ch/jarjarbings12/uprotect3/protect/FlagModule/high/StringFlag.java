package ch.jarjarbings12.uprotect3.protect.FlagModule.high;

import ch.jarjarbings12.uprotect3.protect.FlagModule.low.*;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by tobias on 05.09.2015.
 */
public class StringFlag extends Flag implements ValueFlag<String>, PermissionFlag, CallableFlag<Event>
{
    private final UUID uuid;
    private final String name;
    private String value;
    private final String permission;
    private final ch.jarjarbings12.uprotect3.protect.FlagModule.low.flagCall flagCall;
    private final FlagType flagType = FlagType.BOOLEANFLAG;

    public StringFlag(String name, String value, String permission, flagCall<Event, String> flagCall)
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
    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String getValue()
    {
        return this.value;
    }

    @Override
    public void call(Event event)
    {
        this.flagCall.flagCall(event, this.value);
    }

}
