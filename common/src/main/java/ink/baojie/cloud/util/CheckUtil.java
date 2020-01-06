package ink.baojie.cloud.util;


import ink.baojie.cloud.base.exception.BaseError;
import ink.baojie.cloud.base.exception.BaseRuntimeException;

public class CheckUtil {

    private static final int CHECK_ERR_CODE_START = 1000;

    /**
     * 检查参数是否为空
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     */
    public static void checkEmpty(String paramName, Object paramValue) throws BaseRuntimeException {
        if (paramValue == null) {
            throw new BaseRuntimeException(
                    new BaseError(CHECK_ERR_CODE_START + 1, "【" + paramName + "】不能为空")
            );
        }
    }

    /**
     * 检查参数是否大于等于最小值
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param minValue   有效范围内最小值（包含）
     */
    public static void checkMin(String paramName, float paramValue, float minValue) throws BaseRuntimeException {
        if (paramValue < minValue) {
            throw new BaseRuntimeException(
                    new BaseError(CHECK_ERR_CODE_START + 2, "【" + paramName + "】不能小于" + minValue)
            );
        }
    }

    /**
     * 检查参数是否小于于等于最大值
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param maxValue   有效范围内最大值（包含）
     */
    public static void checkMax(String paramName, float paramValue, float maxValue) throws BaseRuntimeException {
        if (paramValue > maxValue) {
            throw new BaseRuntimeException(new BaseError(CHECK_ERR_CODE_START + 3, "【" + paramName + "】不能大于" + maxValue)
            );
        }
    }

    /**
     * 检查参数是否在最小值和最大值之间
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     * @param minValue   有效范围内最小值（包含）
     * @param maxValue   有效范围内最大值（包含）
     */
    public static void checkRange(String paramName, float paramValue, float minValue, float maxValue) throws BaseRuntimeException {
        if (paramValue < minValue || paramValue > maxValue) {
            throw new BaseRuntimeException(
                    new BaseError(CHECK_ERR_CODE_START + 5, "【" + paramName + "】必须限定在[" + minValue + ", " + maxValue + "]之间")
            );
        }
    }

    /**
     * 检查参数是否为数字(可含小数点)
     *
     * @param paramName  参数名
     * @param paramValue 参数值
     */
    public static void checkFloat(String paramName, String paramValue) throws BaseRuntimeException {
        try {
            Float.valueOf(paramValue);
        } catch (Exception e) {
            throw new BaseRuntimeException(
                    new BaseError(CHECK_ERR_CODE_START + 7, "【" + paramName + "】只能为数字")
            );
        }
    }

}
