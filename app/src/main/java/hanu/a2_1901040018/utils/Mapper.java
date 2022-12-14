package hanu.a2_1901040018.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author capt
 * Mapper: class to map from one model to another one
 */
public class Mapper {
    public static <I, O> O map(I in, Class<O> outClass) {
        Field[] fields = in.getClass().getDeclaredFields();
        O outInstance;
        try {
            outInstance = outClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        for (Field inField : fields) {
            try {
                inField.setAccessible(true);
                Field outField = outClass.getDeclaredField(inField.getName());
                outField.setAccessible(true);
                outField.set(outInstance, inField.get(in));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return outInstance;
    }

    public static <I, O> List<O> map(List<I> inList, Class<O> outClass) {
        List<O> rs = new ArrayList<>();
        for (I in :
                inList) {
            rs.add(map(in, outClass));
        }
        return rs;
    }
}
