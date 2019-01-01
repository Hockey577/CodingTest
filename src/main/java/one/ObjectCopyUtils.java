package one;

import java.io.*;

/**
 * 深拷贝和浅拷贝的区别在于是否重新开辟一个内存区域
 * 通过序列化方式实现深拷贝：先将要拷贝对象写入到内存中的字节流中，
 * 然后再从这个字节流中读出刚刚存储的信息，作为一个新对象返回
 */
public class ObjectCopyUtils {
    public static <T extends Serializable> T copy(T source) {
        T copyObj = null;
        try {
            //写入字节流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(source);
            oos.close();

            //分配内存,写入原始对象,生成新对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());//获取上面的输出字节流
            ObjectInputStream ois = new ObjectInputStream(bais);

            //返回生成的新对象
            copyObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyObj;
    }
}
