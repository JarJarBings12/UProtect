package ch.jarjarbings12.uprotect3.protect.flags;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class DefaultFlags
{
	public final BooleanFlag pvp = new BooleanFlag("allow-pvp", false, "uprotect.flags.pvp");
	public final BooleanFlag scan = new BooleanFlag("disable-scan", false, "uprotect.flags.scan");
	public final BooleanFlag bypass = new BooleanFlag("allow-bypass", true, "uprotect.flags.bypass");
	public final BooleanFlag showonlivemap = new BooleanFlag("visible-on-livemap", true, "uprotect.flags.bypass");
	public final BooleanFlag entitydamage = new BooleanFlag("disable-entity-damage", false, "uprotect.flags.entitydamage");

	public final StringFlag greeting = new StringFlag("greeting", "", "uprotect.flags.greeting");
	public final StringFlag farewell = new StringFlag("farewell", "", "uprotect.flags.farewell");

	public final Flag<?>[] flags = {pvp, scan, bypass, greeting, farewell, entitydamage};

}
