package ch.jarjarbings12.uprotect.protect.kernel.services.DirectInput;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 18.01.2016
 * © 2016 JarJarBings12
 */
public class DirectInput
{
    private HashMap<UUID, DirectHandler> handlers = new HashMap<>();

    public void addDirectInput(DirectHandler directHandler)
    {
        this.handlers.put(directHandler.getHandlerID(), directHandler);
    }
}
