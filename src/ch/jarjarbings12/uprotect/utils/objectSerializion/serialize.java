package ch.jarjarbings12.uprotect.utils.objectSerializion;

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