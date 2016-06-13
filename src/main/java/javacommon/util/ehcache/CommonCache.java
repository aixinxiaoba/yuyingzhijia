package javacommon.util.ehcache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存工具类
 * 
 * @author wangzg
 * @version 2.0
 * @see
 * @since 1.0
 */
public class CommonCache
{
    /**
     * 考生注册的Key
     */
    public static final String COMMON_SAVE_STUDENT = "commonSaveStudent";

    /**
     * 保存考试的Key
     */
    public static final String COMMON_SAVE_EXAM = "commonSaveExam";

    /**
     * 保存易宝支付返回的Key
     */
    public static final String COMMON_DEALWITH_JTEST_YEEPAY_RETURN = "commonDealWithJtestYeePayReturn";

    /**
     * 保存考试的Key
     */
    public static final String COMMON_ADD_JDJH = "commonAddJianDingJiHua";
    
    /**
     * 增加机构的Key 。
     */
    public static final String COMMON_ADD_ORG = "commonAddOrg";

    /**
     * 缓存Map:---用于防止重复提交/增加了并发处理/用完后注意清空，否则容易造成内存溢出 JDK1.4没有这个API/改成Map类
     */
    // public final static Map catchMap = new ConcurrentHashMap();
    public final static Map mapCatchMap = new HashMap();

    /**
     * 空构造:---不允许创建对象 。
     */
    private CommonCache()
    {
        super();
    }

    /**
     * 存入缓存 。
     * 
     * @param strMapKay:---key中由两部分组成:本类中的常量值+业务逻辑相关的字符串
     * @return
     */
    public static boolean addItem(final String strMapKay)
    {
        return mapCatchMap.put(strMapKay, "") == null;
    }

    /**
     * 判断是否已经在内存中存在了 。
     * 
     * @param strMapKay:---key中由两部分组成:本类中的常量值+业务逻辑相关的字符串
     * @return
     */
    public static boolean isProcessed(String strMapKay)
    {
        if (mapCatchMap.containsKey(strMapKay))
        {
            return true;
        }

        return false;
    }

    /**
     * 删除缓存。
     * 
     * @param strMapKay:---key中由两部分组成:本类中的常量值+业务逻辑相关的字符串
     */
    public static void removeItem(String strMapKay)
    {
        mapCatchMap.remove(strMapKay);
    }
}
