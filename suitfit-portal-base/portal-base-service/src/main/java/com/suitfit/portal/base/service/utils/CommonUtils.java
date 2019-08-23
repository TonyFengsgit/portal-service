package com.suitfit.portal.base.service.utils;


public class CommonUtils {
    /**
     * 批量递归删除时 判断target是否在ids中 避免重复删除
     *
     * @param target
     * @param ids
     * @return
     */
    public static Boolean judgeIds(Long target, Long[] ids) {
        Boolean flag = false;
        for (Long id : ids) {
            if (id.equals(target)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
