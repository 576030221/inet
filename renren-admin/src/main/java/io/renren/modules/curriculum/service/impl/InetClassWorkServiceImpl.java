package io.renren.modules.curriculum.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.curriculum.dao.InetClassWorkDao;
import io.renren.modules.curriculum.entity.InetClassWorkEntity;
import io.renren.modules.curriculum.service.InetClassWorkService;


@Service("inetClassWorkService")
public class InetClassWorkServiceImpl extends ServiceImpl<InetClassWorkDao, InetClassWorkEntity> implements InetClassWorkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassWorkEntity> page = this.page(
                new Query<InetClassWorkEntity>().getPage(params),
                new QueryWrapper<InetClassWorkEntity>()
        );

        return new PageUtils(page);
    }

}
