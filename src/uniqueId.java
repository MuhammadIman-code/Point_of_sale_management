public class uniqueId {
    public static int u_id = 0;
    public static int c_id=0;
    public static int s_id=0;

    public static int c_id()
    {
        c_id++;
        return c_id;
    }

    public static int itemId()
    {
        u_id++;
        return u_id;
    }
    public static int SalesId()
    {
        s_id++;
        return s_id;
    }


}
