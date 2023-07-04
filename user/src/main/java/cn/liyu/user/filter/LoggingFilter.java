package cn.liyu.user.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author liyu
 * date 2021/1/22 15:20
 * description
 */
public class LoggingFilter extends OncePerRequestFilter {
    public final static String TRACE_ID = "TRACE_ID";
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    private static final String REQUEST_PREFIX = "Request: ";
    private static final String RESPONSE_PREFIX = "Response: ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest requestToUse = request;
        HttpServletResponse responseToUse = response;
        MDC.put(TRACE_ID, UUID.randomUUID().toString());
        if (!(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request, 2000);
        }
        responseToUse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(requestToUse, responseToUse);
        } finally {
            logRequest(requestToUse);
            logResponse(responseToUse);
        }
    }


    private void logRequest(HttpServletRequest request) {
        StringBuilder msg = new StringBuilder();
        msg.append(REQUEST_PREFIX)
                .append(createMessage(request, "{", "}"));
        logger.info(msg.toString());
    }


    private void logResponse(HttpServletResponse response) throws IOException {

        StringBuilder msg = new StringBuilder();
        msg.append(RESPONSE_PREFIX);

        String payload = getMessagePayload(response);
        if (payload != null) {
            msg.append(";payload=").append(payload);
        }
        logger.info(msg.toString());
    }


    protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
        StringBuilder msg = new StringBuilder();
        msg.append(prefix);
        msg.append("uri=").append(request.getRequestURI());

        //isIncludeQueryString
        String queryString = request.getQueryString();
        if (queryString != null) {
            msg.append('?').append(queryString);
        }

//        //isIncludeClientInfo
//        String client = request.getRemoteAddr();
//        if (StringUtils.hasLength(client)) {
//            msg.append(";client=").append(client);
//        }
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            msg.append(";session=").append(session.getId());
//        }
        String user = request.getRemoteUser();
        if (user != null) {
            msg.append(";user=").append(user);
        }
//
        //isIncludeHeaders
        msg.append(";headers=").append(new ServletServerHttpRequest(request).getHeaders());


        //isIncludePayload
        String payload = getMessagePayload(request);
        if (payload != null) {
            msg.append(";payload=").append(payload);
        }


        msg.append(suffix);
        return msg.toString();
    }

    /**
     * Extracts the message payload portion of the message
     */
    @Nullable
    protected String getMessagePayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = buf.length;
                return new String(buf, 0, length, StandardCharsets.UTF_8);
            }
        }
        return null;
    }

    @Nullable
    protected String getMessagePayload(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (responseWrapper != null) {
            byte[] buf = responseWrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = buf.length;
                try {
                    return new String(buf, 0, length, StandardCharsets.UTF_8);
                } finally {
                    responseWrapper.copyBodyToResponse();
                }
            }
        }
        return null;
    }
}