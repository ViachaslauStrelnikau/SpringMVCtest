package com.test;

import javax.servlet.http.HttpServletRequest;

public interface FileProcService {

    boolean FileUpload(HttpServletRequest request);
    boolean FileProcess();
    String GetFlist();
}
