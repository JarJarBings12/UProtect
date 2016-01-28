package ch.jarjarbings12.uprotect.protect.kernel.services.future;

/**
 * @author JarJarBings12
 * @creationDate 20.01.2016
 * © 2016 JarJarBings12
 */
public interface TriggerCall<E>
{
    void onTrigger(E future);
}
