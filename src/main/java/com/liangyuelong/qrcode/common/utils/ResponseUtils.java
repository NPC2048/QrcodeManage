package com.liangyuelong.qrcode.common.utils;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseUtils {

    public static void json(HttpServletResponse response, Object obj) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        JsonUtils.writeThrow(response.getWriter(), obj);
    }

}
