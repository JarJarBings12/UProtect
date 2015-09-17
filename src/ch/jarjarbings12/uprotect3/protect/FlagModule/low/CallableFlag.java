package ch.jarjarbings12.uprotect3.protect.FlagModule.low;

/**
 * Created by tobias on 05.09.2015.
 */
@FunctionalInterface
public interface CallableFlag<T>
{
    void call(T event);
}
