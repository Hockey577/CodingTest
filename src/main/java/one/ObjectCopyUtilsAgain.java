package one;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 深拷贝和浅拷贝的区别在于是否重新开辟一个内存区域
 */
public class ObjectCopyUtilsAgain {
    public static <T> T copy(T source) {
        //创建一个新的对象(空对象)
        T target = null;

        try {
            Class clz = source.getClass();//获取源对象的class对象
            target = (T)clz.newInstance();//源对象必须有空的构造器
            //获取类中的所有属性
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                //获取属性名
                String fieldName = field.getName();
                //根据属性名称获取setter/getter方法名
                String set = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                String get = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                //根据方法名称获取方法对象
                Method method_set = clz.getMethod(set, field.getType());
                Method method_get = clz.getMethod(get);
                //执行源对象的get方法，获取返回值
                Object returnVal = method_get.invoke(source);
                //执行目标对象的set方法，将源对象方法的返回值作为参数设置给目标对象
                method_set.invoke(target, returnVal);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return target;
    }

}
