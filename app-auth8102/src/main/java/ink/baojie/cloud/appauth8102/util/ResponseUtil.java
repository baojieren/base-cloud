package ink.baojie.cloud.appauth8102.util;

import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renbaojie
 */
public class ResponseUtil {

    public static void response(HttpServletResponse response, int code, String json) {
        try {
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            response.setStatus(code);
            response.getWriter().println(json);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
