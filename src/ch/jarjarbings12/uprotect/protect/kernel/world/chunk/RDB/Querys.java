package ch.jarjarbings12.uprotect.protect.kernel.world.chunk.RDB;

/**
 * @author JarJarBings12
 * @creationDate 30.09.2015
 * © 2015 JarJarBings12
 */
public enum  Querys
{

    INSERT("INSERT INTO WORLD_%w (x, z, bin, time) VALUES (?, ?, ?, ?)"),
    SELECT("SELECT * FROM WORLD_%w WHERE x=? AND z=?"),
    EXIST("SELECT COUNT(*) AS REC FROM WORLD_%w WHERE x=? AND z=?"),
    REMOVE("REMOVE * FROM WORLD_%w WHERE x=? AND z=?");

    private String query;

    Querys(String query)
    {
        this.query = query;
    }

    public String getQuery()
    {
        return this.query;
    }
}