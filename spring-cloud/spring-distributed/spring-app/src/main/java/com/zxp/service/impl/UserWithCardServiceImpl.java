package com.zxp.service.impl;

import com.zxp.entity.po.User;
import com.zxp.entity.po.UserCard;
import com.zxp.service.UserCardService;
import com.zxp.service.UserService;
import com.zxp.service.UserWithCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Service
public class UserWithCardServiceImpl implements UserWithCardService {
    private final UserService userService;
    private final UserCardService userCardService;
    private final UserWithCardServiceImpl userWithCardService;
    @Autowired
    public UserWithCardServiceImpl(UserService userService, UserCardService userCardService, UserWithCardServiceImpl userWithCardService) {
        this.userService = userService;
        this.userCardService = userCardService;
        this.userWithCardService = userWithCardService;
    }

    @Override
    /**
     * 事务注意点：
     * 1.事务采用的AOP 实现的  事务的注解必须在Public方法上
     * 2.事务不能直接调用本类中方法 不然事务失效
     * 3.事务失效还有可能是 数据库中的事务失效
     * 4.需要执行事务回滚的具体异常类
     * 5.子方法中不能吃了异常
     * rollbackOn设置到指定的异常中，若是拦截器标记的事务出现该异常时，事务将回滚。
     * 相反，dontRollbackOn设置将不会出现回滚。
     * 当一个类有这些元素中的任何一个，指定的行为也适用于该类的子类。如果两个元素都有，
     * dontRollbackOn优先。
     * dontRollbackOn：在某些异常下 不进行回滚操作
     *  注意引用的类：Transactional注解包有JAVAX和Springboot的
     */
    public void addUser(User user, int cardCount) {
        userWithCardService.addUserWitCard(user,cardCount);
    }
    @Transactional(isolation = Isolation.DEFAULT, rollbackFor = {RuntimeException.class, Error.class})
    public void addUserWitCard(User user, int cardCount){
        UUID uuid = UUID.randomUUID();
        user.setUserID(uuid.toString());
        userService.addUser(user);
        UserCard userCard = new UserCard();
        userCard.setCount(cardCount);
        userCard.setUserID(uuid.toString());
        userCardService.addUserCard(userCard);
    }

}
