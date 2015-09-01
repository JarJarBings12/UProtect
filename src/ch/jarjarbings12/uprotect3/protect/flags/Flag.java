package ch.jarjarbings12.uprotect3.protect.flags;

/**
 * Created by tobias on 23.08.2015.
 */
public abstract class Flag<T>
{
    public abstract String getName();

    public abstract void setValue(T value);

    public abstract T getValue();

    public abstract String getPermission();

    public abstract type getType();

    public enum type
    {
        STRING,
        BOOLEAN
    }
}
