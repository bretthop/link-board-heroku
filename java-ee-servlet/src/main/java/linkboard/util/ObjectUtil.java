package linkboard.util;

public class ObjectUtil
{
    public static boolean isObjectEmpty(Object o)
    {
        if (o == null) { return true; }

        if (o instanceof String) {
            String s = (String) o;

            if (s.trim().equals("")) { return true; }
        }

        return false;
    }
}
