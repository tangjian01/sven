package com.sven.netty.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/11 9:49
 * @description：
 * @version:
 * @see
 */
@Setter
@Getter
public class UserInfo implements Serializable {
    private String name;
    private int age;
}
