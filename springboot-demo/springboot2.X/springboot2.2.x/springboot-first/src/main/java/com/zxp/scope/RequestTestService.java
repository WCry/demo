package com.zxp.scope;

import com.zxp.scope.RequestScopeAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class RequestTestService {
    @Autowired
   public RequestScopeAction requestScopeAction;

    public String getRequestUrl(){
        return requestScopeAction.getName();
    }
}
