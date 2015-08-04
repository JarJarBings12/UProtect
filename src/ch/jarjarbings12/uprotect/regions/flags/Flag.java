package ch.jarjarbings12.uprotect.regions.flags;

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

	enum type
	{
		STRING,
		BOOLEAN
	}
}
