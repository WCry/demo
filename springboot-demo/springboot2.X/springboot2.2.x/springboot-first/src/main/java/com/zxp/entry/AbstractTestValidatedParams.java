package com.zxp.entry;

import javax.validation.constraints.Max;

/**
 * @author zhangxuepei
 * @since 3.0
 */

public abstract class AbstractTestValidatedParams {

    @Max(value = 100,groups = Groups.groups100.class, message = "数量不能超过一百")
    @Max(value = 1000,groups = Groups.groups1000.class,message = "数量不能超过一千")
    public Integer numbers;

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }
    public interface Groups{
        interface groups100{};
        interface groups1000 {};
    }
}
