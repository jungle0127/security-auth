package com.ps.security.auth.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

public interface IValidateCodeProcessor {
    String SESSION_KEY_PREFIX="SESSION_KEY_FOR_CODE_";

    /**
     *
     * @param request Adaptor for HttpServletRequest, encapsulate for HttpRequest&HttpResponse
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;
}
