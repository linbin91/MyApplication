package com.lin.request.cookie;

import okhttp3.CookieJar;

public interface ClearableCookieJar extends CookieJar {
    void clearSession();

    void clear();
}
