package ch.jarjarbings12.uprotect2.protect.flags;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public abstract class Flag<T>
{

	public abstract String getName();

	public abstract void setValue(T value);

	public abstract T getValue();

	public abstract String getPermission();

	public abstract type getType();

	public enum type
	{
		STRING,
		BOOLEAN
	}
}
