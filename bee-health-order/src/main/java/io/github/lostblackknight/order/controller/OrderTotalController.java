package io.github.lostblackknight.order.controller;

import cn.hutool.core.date.DateUtil;
import io.github.lostblackknight.model.vo.CommonResult;
import io.github.lostblackknight.order.service.OrderInfoService;
import io.github.lostblackknight.order.vo.OrderChartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class OrderTotalController {

    private final OrderInfoService orderInfoService;

    @GetMapping("/orders/total/chart")
    public CommonResult<?> getOrderChart() {
        final String from0 = DateUtil.format(DateUtil.offsetDay(new Date(), -6).toJdkDate(), "yyyy-MM-dd");
        final String from1 = DateUtil.format(DateUtil.offsetDay(new Date(), -5).toJdkDate(), "yyyy-MM-dd");
        final String from2 = DateUtil.format(DateUtil.offsetDay(new Date(), -4).toJdkDate(), "yyyy-MM-dd");
        final String from3 = DateUtil.format(DateUtil.offsetDay(new Date(), -3).toJdkDate(), "yyyy-MM-dd");
        final String from4 = DateUtil.format(DateUtil.offsetDay(new Date(), -2).toJdkDate(), "yyyy-MM-dd");
        final String from5 = DateUtil.format(DateUtil.offsetDay(new Date(), -1).toJdkDate(), "yyyy-MM-dd");
        final String from6 = DateUtil.format(DateUtil.offsetDay(new Date(), 0).toJdkDate(), "yyyy-MM-dd");

        final OrderChartVO vo0 = new OrderChartVO();
        vo0.setDate(from0);
        final OrderChartVO vo1 = new OrderChartVO();
        vo1.setDate(from1);
        final OrderChartVO vo2 = new OrderChartVO();
        vo2.setDate(from2);
        final OrderChartVO vo3 = new OrderChartVO();
        vo3.setDate(from3);
        final OrderChartVO vo4 = new OrderChartVO();
        vo4.setDate(from4);
        final OrderChartVO vo5 = new OrderChartVO();
        vo5.setDate(from5);
        final OrderChartVO vo6 = new OrderChartVO();
        vo6.setDate(from6);

        final ArrayList<OrderChartVO> res = new ArrayList<>();

        final long count0 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), -6).toJdkDate());
        vo0.setCount(count0);
        res.add(vo0);

        final long count1 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), -5).toJdkDate());
        vo1.setCount(count1);
        res.add(vo1);

        final long count2 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), -4).toJdkDate());
        vo2.setCount(count2);
        res.add(vo2);

        final long count3 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), -3).toJdkDate());
        vo3.setCount(count3);
        res.add(vo3);

        final long count4 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), -2).toJdkDate());
        vo4.setCount(count4);
        res.add(vo4);

        final long count5 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), -1).toJdkDate());
        vo5.setCount(count5);
        res.add(vo5);

        final long count6 = orderInfoService.getOrderByDate(DateUtil.offsetDay(new Date(), 0).toJdkDate());
        vo6.setCount(count6);
        res.add(vo6);

        return CommonResult.success(res);
    }
}
