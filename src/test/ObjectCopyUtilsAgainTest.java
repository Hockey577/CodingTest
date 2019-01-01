import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectCopyUtilsAgainTest {

    @Test
    public void copy() {
        Person p = new Person("糊七七", 22);
        Person pCopy = one.ObjectCopyUtilsAgain.copy(p);
        pCopy.setName("huqiqi");
        System.out.println(pCopy);
        System.out.println("地址是否相同：" + (pCopy == p));
    }
}