package ru.itis.Services;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JavaScriptService {
    public static void sendJavaScriptResponse(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><script>");
        out.println("alert('" + message + "');");
        out.println("history.back();");
        out.println("</script></head><body></body></html>");
    }
}
