package ch.jarjarbings12.uprotect.protect.kernel.services.interfaces;

import java.io.InputStream;

/**
 * @author JarJarBings12
 * @creationDate 12.06.2015
 * © 2015 JarJarBings12
 */
public interface compressor
{
    byte[] compress(Object object);

    Object decompress(InputStream inputStream);

}
