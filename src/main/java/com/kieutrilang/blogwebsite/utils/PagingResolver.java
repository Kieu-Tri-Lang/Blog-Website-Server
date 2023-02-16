package com.kieutrilang.blogwebsite.utils;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PagingResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(PagingParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        PagingParam pagingParam = parameter.getParameterAnnotation(PagingParam.class);

        // Page
        String pageStr = getPropStringFromWebRequest(webRequest, "page");
        Integer page = pageStr.isEmpty() || pageStr.matches("\\D+") ? pagingParam.page() : Integer.valueOf(pageStr);
        page = page < 1 ? pagingParam.page() : page;

        // Limited
        String limitedSizeStr = getPropStringFromWebRequest(webRequest, "limitedSize");
        Integer limitedSize = limitedSizeStr.isEmpty() || limitedSizeStr.matches("\\D+") ? pagingParam.limitedSize()
                : Integer.valueOf(limitedSizeStr);
        limitedSize = limitedSize < 0 ? pagingParam.limitedSize() : limitedSize;

        // Sort target
        String sortTarStr = getPropStringFromWebRequest(webRequest, "sortTar");
        String sortTar = sortTarStr.isEmpty() ? pagingParam.sortTar() : sortTarStr;

        // Sort direction
        String sortDirStr = getPropStringFromWebRequest(webRequest, "sortDir");
        String sortDir = sortDirStr.isEmpty() ? pagingParam.sortDir() : sortDirStr;
        sortDir = sortDir.equals("asc") ? "asc" : "desc";

        return createPageRequest(page, limitedSize, sortTar, sortDir);

    }

    private String getPropStringFromWebRequest(NativeWebRequest webRequest, String paramName) {
        String param = webRequest.getParameter(paramName);
        return param == null ? "" : param;
    }

    private Pageable createPageRequest(int page, int limitSize, String sortTar, String sortDir) {
        Direction direction = sortDir.equals("desc") ? Direction.DESC : Direction.ASC;

        return PageRequest.of(page -1 , limitSize, Sort.by(direction, sortTar));
    }
}