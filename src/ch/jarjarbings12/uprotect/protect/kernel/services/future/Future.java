package ch.jarjarbings12.uprotect.protect.kernel.services.future;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 20.01.2016
 * © 2016 JarJarBings12
 */
public abstract class Future<E>
{
    private final UUID fid = UUID.randomUUID();
    private TriggerCall<E> triggerCall;

    public Future(TriggerCall triggerCall)
    {
        this.triggerCall = triggerCall;
    }

    public abstract void trigger();

    public final TriggerCall<E> getTriggerCall()
    {
        return this.triggerCall;
    }

    public final UUID getFutureID()
    {
        return this.fid;
    }


}
