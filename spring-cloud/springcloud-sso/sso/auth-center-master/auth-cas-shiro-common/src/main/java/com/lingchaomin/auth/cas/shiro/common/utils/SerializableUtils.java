package com.lingchaomin.auth.cas.shiro.common.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/21 下午4:29
 * @description 序列化工具类
 */
public class SerializableUtils {

    public static String serialize(Session session) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }
    public static Session deserialize(String sessionStr) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Session)ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}