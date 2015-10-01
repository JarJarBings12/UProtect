package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015.
 * © 2015 JarJarBings12
 */
public final class FlagNode <V>
{
    private final UUID flagID;
    private V value;

    public FlagNode(UUID flagID, V value)
    {
        this.flagID = flagID;
        this.value = value;
    }

    public UUID getFlagID()
    {
        return this.flagID;
    }

    public V getValue()
    {
        return this.value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }

}
