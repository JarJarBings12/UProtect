package ch.jarjarbings12.uprotect3.protect.kernel.services.interfaces;

import java.io.InputStream;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 12.06.2015
 */
public interface compressor
{
    byte[] compress(Object object);

    Object decompress(InputStream inputStream);

}
