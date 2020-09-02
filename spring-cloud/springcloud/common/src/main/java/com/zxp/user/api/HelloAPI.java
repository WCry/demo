package com.zxp.user.api;

import com.zxp.user.dto.user;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface HelloAPI {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String home(@RequestParam(value = "name", defaultValue = "forezp") String name);

    @RequestMapping(value = "/hi123", method = RequestMethod.GET)
    String hi(@RequestBody user name);
}
