package com.test;

import javax.servlet.http.HttpServletRequest;

public interface FileProcService {

    boolean FileUpload(HttpServletRequest request);
    String GetFlist();
}
