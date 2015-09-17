package ch.jarjarbings12.uprotect3.protect.FlagModule.low;

/**
 * Created by tobias on 05.09.2015.
 */
public interface ValueFlag<T>
{
    void setValue(T value);
    
    T getValue();
}
