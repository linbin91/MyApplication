package com.lin.request.core;

import java.io.Serializable;

public class BaseResponseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public int code;

    public String msg;

    public boolean success() {
        return code == 200;
    }
}
