package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * � 2015 JarJarBings12
 */
public interface ValueFlag <V>
{
    Flag setValue(V value);

    V getValue();
}
