package com.vn.redditclone.service;

import com.vn.redditclone.bean.request.RegisterRequest;
import com.vn.redditclone.bean.response.BaseResponse;

public interface AuthService {
    BaseResponse signup(RegisterRequest request);
}
