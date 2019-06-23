package com.jackie.lee.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by lxb on 2019/6/23.
 */
public class UrlUtils {

    /**
     * 将url更换为https开头的
     *
     * @param uri
     * @return
     */
    public static String checkUriHttps(String uri) {
        if (StringUtils.isBlank(uri) || StringUtils.startsWithIgnoreCase(uri, "https://")) {
            return uri;
        }
        if (StringUtils.startsWithIgnoreCase(uri, "http://")) {
            uri = uri.replaceFirst("http://", "https://");
        } else if (StringUtils.startsWith(uri, "//")) {
            uri = "https:" + uri;
        } else {
            uri = "https://" + uri;
        }
        return uri;
    }


}
