package linkboard.util;

import javax.xml.bind.DatatypeConverter;

public class Base64Util
{
    // TODO: See if there are better libs out there for this
    public static String decode(String base64)
    {
        StringBuilder result = new StringBuilder();

        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(base64);

        for (byte decodedByte : decodedBytes) {
            result.append((char) decodedByte);
        }

        return result.toString();
    }
}
