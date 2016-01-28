package ch.jarjarbings12.uprotect.protect.kernel.services.objects;

/**
 * @author JarJarBings12
 * @creationDate 04.06.2015
 * � 2015 JarJarBings12
 */
public interface serialize
{
    byte[] serializeObject(Object object);

    Object deserializeByteArray(byte[] bytes);
}
