package ch.jarjarbings12.uprotect.protect.kernel.flag.module.high;

import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.*;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public class StringFlag extends Flag implements ValueFlag<String>, PermissionFlag, CallableFlag<Event>
{
    private final UUID flagID;
    private final String name;
    private final String flagTag;
    private String value;
    private final String permission;
    private final ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.flagCall<Event, String> flagCall;

    public StringFlag(UUID flagID, String name, String flagTag, String value, String permission, flagCall<Event, String> flagCall)
    {
        this.flagID = flagID;
        this.name = name;
        this.flagTag = flagTag;
        this.value = value;
        this.permission = permission;
        this.flagCall = flagCall;
    }

    @Override
    public UUID getFlagID()
    {
        return null;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getFlagTag()
    {
        return this.flagTag;
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
    public Flag setValue(String value)
    {
        this.value = value;
        return this;
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
