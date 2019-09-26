package io.renren.modules.curriculum.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.curriculum.dao.InetClassWordRecordDao;
import io.renren.modules.curriculum.entity.InetClassWordRecordEntity;
import io.renren.modules.curriculum.service.InetClassWordRecordService;


@Service("inetClassWordRecordService")
public class InetClassWordRecordServiceImpl extends ServiceImpl<InetClassWordRecordDao, InetClassWordRecordEntity> implements InetClassWordRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassWordRecordEntity> page = this.page(
                new Query<InetClassWordRecordEntity>().getPage(params),
                new QueryWrapper<InetClassWordRecordEntity>()
        );

        return new PageUtils(page);
    }

}
