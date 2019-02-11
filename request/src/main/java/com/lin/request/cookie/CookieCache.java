package com.lin.request.cookie;

import java.util.Collection;

import okhttp3.Cookie;

public interface CookieCache extends Iterable<Cookie>{
    void addAll(Collection<Cookie> cookies);
    void clear();
}
