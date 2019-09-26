package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.InetClassArriveRecordDao;
import io.renren.modules.sys.entity.InetClassArriveRecordEntity;
import io.renren.modules.sys.service.InetClassArriveRecordService;


@Service("inetClassArriveRecordService")
public class InetClassArriveRecordServiceImpl extends ServiceImpl<InetClassArriveRecordDao, InetClassArriveRecordEntity> implements InetClassArriveRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassArriveRecordEntity> page = this.page(
                new Query<InetClassArriveRecordEntity>().getPage(params),
                new QueryWrapper<InetClassArriveRecordEntity>()
        );

        return new PageUtils(page);
    }

}
