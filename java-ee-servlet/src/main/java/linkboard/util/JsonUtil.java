package linkboard.util;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil
{
    public static String serialise(Object obj)
    {
        String result = null;

        try {
            result = new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            // TODO: Add logging
            System.out.println("Error serialising object. Stack trace follows.");
            e.printStackTrace();
        }

        return result;
    }

    public static <T> T deserialise(String j, Class<T> clazz)
    {
        T result = null;

        try {
            result = new ObjectMapper().readValue(j, clazz);
        }
        catch (Exception e) {
            // TODO: Add logging
            System.out.println(String.format("Error deserialising object. JSON given: %s. Stack trace follows.", j));
            e.printStackTrace();
        }

        return result;
    }
}
