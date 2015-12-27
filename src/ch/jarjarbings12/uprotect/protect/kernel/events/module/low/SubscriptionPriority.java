package ch.jarjarbings12.uprotect.protect.kernel.events.module.low;

/**
 * @author JarJarBings12
 * @creationDate 23.12.2015
 * © 2015 JarJarBings12
 */
public enum SubscriptionPriority
{
    MONITOR(0),
    HIGHEST(1),
    HIGH(2),
    MEDIUM(3),
    NORMAL(4),
    LOW(5),
    LOWEST(6);

    final int priority;

    SubscriptionPriority(int priority)
    {
        this.priority = priority;
    }

    public int getPriority()
    {
        return this.priority;
    }
}
