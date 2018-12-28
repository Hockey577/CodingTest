import one.ObjectCopyUtils;
import org.junit.Test;

import java.io.File;

public class ObjectCopyUtilsTest {


    @Test
    public void copy() {
        //        AnyType any =	new	AnyType();
        //        any.setXXX(); ....
        //        AnyType copy = one.ObjectCopyUtils.copy(any);
        Person p = new Person("糊七七", 22);
        Person pCopy = ObjectCopyUtils.copy(p);
        pCopy.setName("huqiqi");
        System.out.println(pCopy);
        System.out.println("地址是否相同：" + (pCopy == p));
    }


}