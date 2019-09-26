package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.InetClassDao;
import io.renren.modules.sys.entity.InetClassEntity;
import io.renren.modules.sys.service.InetClassService;


@Service("inetClassService")
public class InetClassServiceImpl extends ServiceImpl<InetClassDao, InetClassEntity> implements InetClassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassEntity> page = this.page(
                new Query<InetClassEntity>().getPage(params),
                new QueryWrapper<InetClassEntity>()
        );

        return new PageUtils(page);
    }

}
