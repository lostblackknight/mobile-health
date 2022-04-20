package io.github.lostblackknight.constant;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public class OrderConstant {
    // 预约成功，待支付
    public static final Integer UNPAID = 0;
    // 已支付
    public static final Integer PAID = 1;
    // 已取消
    public static final Integer CANCEL = -1;
    // 已取消，且退款
    public static final Integer CANCEL_UNPAID = -2;
    // 取号，已完成
    public static final Integer CLOSE = 2;
}
