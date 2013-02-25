package linkboard.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class RequestUtil
{
    public static String getRequestBody(HttpServletRequest req)
    {
        try {
            String result = "";

            BufferedReader reader = req.getReader();
            char[] buf = new char[4 * 1024];
            while (reader.read(buf, 0, buf.length) != -1) {
                result += String.valueOf(buf);
            }

            return result.trim();
        }
        catch (Exception e) {
            // TODO: Add logging
            System.out.println("Error getting request body. Stack trace follows.");
            e.printStackTrace();

            return null;
        }
    }
}
