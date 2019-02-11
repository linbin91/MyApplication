package com.lin.request.cookie;

import java.util.Collection;
import java.util.List;

import okhttp3.Cookie;

public interface CookiePersistor {

    List<Cookie> loadAll();

    void saveAll(Collection<Cookie> cookies);

    void removeAll(Collection<Cookie> cookies);

    void clear();
}
