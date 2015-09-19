package ch.jarjarbings12.uprotect.protect.kernel.flag.module.low;

import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.CallableFlag;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.PermissionFlag;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.ValueFlag;

/**
 * @author JarJarBings12
 * @creationDate 19.09.2015
 * © 2015 JarJarBings12
 */
public class FlagUtils
{
    public FlagUtils()
    {}

    public String getTechnicalInformation(Flag flag)
    {
        return String.format("-----<Flag>-----\n Interface support:\n ValueFlag: %s\n PermissionFlag: %s\n CallableFlag: %s", doSupportValues(flag), doSupportPermissions(flag), isCallable(flag));
    }

    public boolean doSupportValues(Flag flag)
    {
        return flag instanceof ValueFlag;
    }

    public boolean doSupportPermissions(Flag flag)
    {
        return flag instanceof PermissionFlag;
    }

    public boolean isCallable(Flag flag)
    {
        return flag instanceof CallableFlag;
    }

}
