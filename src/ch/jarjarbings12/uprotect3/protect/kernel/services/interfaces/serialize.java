package ch.jarjarbings12.uprotect3.protect.kernel.services.interfaces;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 04.06.2015
 */
public interface serialize
{
    byte[] serializeObject(Object object);

    Object deserializeByteArray(byte[] bytes);
}