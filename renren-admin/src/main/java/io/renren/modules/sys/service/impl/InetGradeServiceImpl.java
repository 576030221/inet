package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.InetGradeDao;
import io.renren.modules.sys.entity.InetGradeEntity;
import io.renren.modules.sys.service.InetGradeService;


@Service("inetGradeService")
public class InetGradeServiceImpl extends ServiceImpl<InetGradeDao, InetGradeEntity> implements InetGradeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetGradeEntity> page = this.page(
                new Query<InetGradeEntity>().getPage(params),
                new QueryWrapper<InetGradeEntity>()
        );

        return new PageUtils(page);
    }

}
