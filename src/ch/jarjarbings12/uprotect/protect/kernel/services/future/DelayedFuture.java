package ch.jarjarbings12.uprotect.protect.kernel.services.future;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author JarJarBings12
 * @creationDate 20.01.2016
 * © 2016 JarJarBings12
 */
public final class DelayedFuture extends Future<>
{
    private final long delay;
    private final Timer timer = new Timer();
    private final CancelledFuture cancelledFuture;
    private final ExecutedFuture executedFuture;

    public DelayedFuture(TriggerCall onTrigger, CancelledFuture cancelledFuture, ExecutedFuture executedFuture, long delay)
    {
        super(onTrigger);
        this.cancelledFuture = cancelledFuture;
        this.executedFuture = executedFuture;
        this.delay = delay;
    }

    @Override
    public void trigger()
    {
        super.getTriggerCall().onTrigger(this);
    }

    public void cancel()
    {
        this.cancelledFuture.cancel();
    }

    public void execute()
    {
        this.executedFuture.execute();
    }

    public static DelayedFuture cast(Future future)
    {
        return (DelayedFuture) future;
    }

    protected void setupDelay()
    {
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                trigger();
            }
        }, this.delay);
    }

}
