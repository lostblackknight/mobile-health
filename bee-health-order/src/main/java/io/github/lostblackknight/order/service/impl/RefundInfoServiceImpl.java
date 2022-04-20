package io.github.lostblackknight.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.lostblackknight.model.entity.order.RefundInfo;
import io.github.lostblackknight.order.service.RefundInfoService;
import io.github.lostblackknight.order.mapper.RefundInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author chensixiang
* @description 针对表【refund_info(退款信息表)】的数据库操作Service实现
* @createDate 2022-04-19 15:41:47
*/
@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo>
    implements RefundInfoService{

}




