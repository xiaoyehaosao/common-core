package com.xyhs.b2c.common.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liuyadu
 */
public class BeanConvertUtils {
    /**
     * 方法说明：将bean转化为另一种bean实体
     * 
     * @param object  要拷贝的对象
     * @param entityClass  拷贝的目标的对象
     * @return 拷贝的目标的对象
     */
    public static <T> T convert(Object object, Class<T> entityClass) {
        if(null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }


    /**
     * 方法说明：对象转换
     * 
     * @param source	原对象
     * @param target	目标对象
     * @param ignoreProperties	排除要copy的属性
     * @return 拷贝的目标的对象
     */
    public static <T> T copy(Object source, Class<T> target, String...ignoreProperties){
        T targetInstance = null;
        try {
            targetInstance = target.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(targetInstance == null){
            return null;
        }
        if(ArrayUtils.isEmpty(ignoreProperties)) {
            BeanUtils.copyProperties(source, targetInstance);
        }else {
            BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
        }
        return targetInstance;

    }

    /**
     * 方法说明：对象转换(List)
     * 
     * @param list	原对象
     * @param target	目标对象
     * @param ignoreProperties	排除要copy的属性
     * @return 拷贝的目标的对象队列
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target, String...ignoreProperties){
        List<T> targetList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for(E e : list) {
            targetList.add(copy(e, target, ignoreProperties));
        }
        return targetList;
    }


    /**
     * 方法说明：对象转换(List)
     * 
     * @param list	原对象
     * @param target	目标对象
     * @return 拷贝的目标的对象队列
     */
    public static <T, E> List<T> copyList(List<E> list, Class<T> target){
        List<T> targetList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)) {
            return targetList;
        }
        for(E e : list) {
            targetList.add(convert(e, target));
        }
        return targetList;
    }


    /**
     * 方法说明：map转化为对象
     * 
     * @param map 要拷贝的map
     * @param t 拷贝的目标对象
     * @return  拷贝的目标对象
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> t){
        try {
            T instance = t.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(instance, map);
            return instance;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 方法说明：对象转化为Map
     * 
     * @param object 要拷贝的对象
     * @return 拷贝的目标map
     */
    public static Map<?, ?> objectToMap(Object object){
        return convert(object, Map.class);
    }

}
