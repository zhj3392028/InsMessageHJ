package com.giveof.insmessagehj.presenter;

import com.giveof.insmessagehj.entity.BaseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Time on 16/10/9.
 */
public class BasePresenter {

    public static <T extends BaseEntity> Map<Long,T> idEntityMap(Collection<T> list){
        Map<Long,T> map = new HashMap<>();
        if (null == null || 0 == list.size()) {
            return map;
        }

        for (T entity:list
                ) {
            map.put(entity.getId(),entity);
        }
        return map;

    }
}
