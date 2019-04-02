package com.zhulong.framework.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 两个数的dto对象
 * Created by shanb on 2019-2-25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueDTO<K,V> implements Serializable {

    private K key;

    private V value;

}