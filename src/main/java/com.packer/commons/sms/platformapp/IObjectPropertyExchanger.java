package com.packer.commons.sms.platformapp;


public interface IObjectPropertyExchanger<E> {
    Class<?> getObjectClass();

    Class<E> getPropertyClass();

    String object2String(E var1);

    E string2Object(String var1);
}