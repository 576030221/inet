package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.InetClassNameDao;
import io.renren.modules.sys.entity.InetClassNameEntity;
import io.renren.modules.sys.service.InetClassNameService;


@Service("inetClassNameService")
public class InetClassNameServiceImpl extends ServiceImpl<InetClassNameDao, InetClassNameEntity> implements InetClassNameService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassNameEntity> page = this.page(
                new Query<InetClassNameEntity>().getPage(params),
                new QueryWrapper<InetClassNameEntity>()
        );

        return new PageUtils(page);
    }

}
