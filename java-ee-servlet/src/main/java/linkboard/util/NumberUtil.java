package linkboard.util;

public class NumberUtil
{
    public static Long tryParseLong(String s)
    {
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

}
