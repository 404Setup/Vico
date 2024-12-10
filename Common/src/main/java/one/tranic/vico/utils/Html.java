package one.tranic.vico.utils;

import java.util.Base64;

public class Html {
    public static String decodeAndStripHtml(String base64EncodedString) {
        return new String(Base64.getDecoder().decode(base64EncodedString)).replaceAll("<[^>]+>", "").replaceFirst("^\\s+", "");
    }
}
