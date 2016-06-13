package javacommon.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对List进行排序的工具类。
 * 
 * @ClassName: ListSortUtil.java
 * @author YangZhenhua
 * @Date 2013-4-24 上午11:07:19
 */
@SuppressWarnings("unchecked")
public class ListSortUtil
{
    /**
     * 日志对象。
     */
    private static final Logger logger = LoggerFactory.getLogger(ListSortUtil.class);

    public static Map<Method, String> mapSortField = new LinkedHashMap<Method, String>();
    public static Map<String, Method> mapProMethod = null;

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    /**
     * 空构造 。
     */
    private ListSortUtil()
    {
        // 禁止访问
    }

    /**
     * 解析数据 。
     */
    private static void prepareProMethod(final Class objClazz) throws IntrospectionException
    {
        BeanInfo objBeanInfo = Introspector.getBeanInfo(objClazz);// 通过内省，取得bean的全部信息。
        PropertyDescriptor[] arrayPropertyDescriptor = objBeanInfo.getPropertyDescriptors();// 取得属性集合。
        Map<String, Method> mapPropertyMethod = new HashMap<String, Method>();

        for (PropertyDescriptor objPD : arrayPropertyDescriptor)
        {
            String strKey = objPD.getName();
            Method objValue = objPD.getReadMethod();

            mapPropertyMethod.put(strKey, objValue);
        }

        mapProMethod = mapPropertyMethod;
    }

    /**
     * 清除map中的数据。
     * 
     * @Title: clear
     * @author YangZhenhua
     * @Date 2013-4-24 上午10:43:12
     */
    public static void clear()
    {
        mapSortField.clear();
    }

    /**
     * 增加一个降序。
     * 
     * @param strFieldName
     * @throws NoSuchMethodException
     * @author Ken_xu
     */
    private static void addDesc(String strFieldName) throws NoSuchMethodException
    {
        addFieldMethod(strFieldName, DESC);
    }

    /**
     * 增加一个升序。
     * 
     * @Title: addAsc
     * @author YangZhenhua
     */
    private static void addAsc(String strFieldName) throws NoSuchMethodException
    {
        addFieldMethod(strFieldName, ASC);
    }

    /**
     * 增加一个字段排序模式。
     * 
     * @Title: addFieldMethod
     * @author YangZhenhua
     */
    private static void addFieldMethod(String strFieldName, String strDirection) throws NoSuchMethodException
    {
        Method objMethod = mapProMethod.get(strFieldName);

        if (objMethod == null)
        {
            throw new NoSuchMethodException(strFieldName);
        }
        else
        {
            mapSortField.put(objMethod, strDirection);
        }
    }

    /**
     * 对List集合进行排序。
     * 
     * @param objClazz
     * @param strDirection
     * @param strFieldName
     * @param lstList
     * @return
     * @throws IntrospectionException
     */
    public static List sortList(final Class objClazz, String strDirection, final String strFieldName, List lstList) throws Exception
    {
        // 准备数据
        prepareProMethod(objClazz);

        clear();

        // 排序
        if (!StringUtils.isEmpty(strDirection))
        {
            if (strDirection.equalsIgnoreCase(ASC))
            {
                addAsc(strFieldName);
            }
            else
            {
                addDesc(strFieldName);
            }
        }
        else
        {
            addAsc(strFieldName);
        }

        // 对数据排序
        if (!mapSortField.isEmpty())
        {
            Comparator objComparator = new Comparator()
            {
                /**
                 * 重写compare方法。
                 */
                public int compare(Object objCom1, Object objCom2)
                {
                    int nFlag = 0;

                    for (Map.Entry<Method, String> objEntity : mapSortField.entrySet())
                    {
                        Method objMethod = objEntity.getKey();
                        String strDirection = objEntity.getValue();

                        if (ASC.equalsIgnoreCase(strDirection))
                        {
                            nFlag = this.compareByFlag(objMethod, objCom1, objCom2); // DESC:降序
                        }
                        else
                        {
                            nFlag = this.compareByFlag(objMethod, objCom2, objCom1); // ASC:升序
                        }
                        if (nFlag != 0)
                        {
                            break;
                        }
                    }
                    if (nFlag > 0)
                    {
                        nFlag = 1;
                    }
                    else if (nFlag < 0)
                    {
                        nFlag = -1;
                    }
                    return nFlag;
                }

                /**
                 * 如果objCom1小于objCom2,返回一个负数;如果objCom1大于objCom2，返回一个正数;如果他们相等，则返回0。
                 * 
                 * @Title: compareByFlag
                 * @author YangZhenhua
                 */
                private int compareByFlag(Method objMethod, Object objCom1, Object objCom2)
                {
                    int nFlag = 0;

                    try
                    {
                        String strPattern = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$";

                        String strMethodReturn1 = objMethod.invoke(objCom1).toString();
                        String strMethodReturn2 = objMethod.invoke(objCom2).toString();

                        // 如果是数字类型，怎转换为数字，再进行比较。
                        if (strMethodReturn1.matches(strPattern) && strMethodReturn2.matches(strPattern))
                        {
                            Integer nNum1 = Integer.valueOf(strMethodReturn1);
                            Integer nNum2 = Integer.valueOf(strMethodReturn2);
                            
                            nFlag = nNum1.compareTo(nNum2);
                        }
                        else
                        {
                            nFlag = strMethodReturn1.compareTo(strMethodReturn2);
                        }

                    }
                    catch (Exception objException)
                    {
                        logger.error(objException.toString());
                    }

                    return nFlag;
                }
            };
            Collections.sort(lstList, objComparator);
        }
        return lstList;
    }

}
