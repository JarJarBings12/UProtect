package ch.jarjarbings12.uprotect.protect.kernel.events.module.low;

import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public interface BasicSubscription
{
    void onSubscribe() throws NotInUseException;

    void onUnsubscribe() throws NotInUseException;
}
