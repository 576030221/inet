package io.renren.modules.curriculum.service.impl;

import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.curriculum.dao.InetClassDao;
import io.renren.modules.curriculum.entity.InetClassEntity;
import io.renren.modules.curriculum.service.InetClassService;


@Service("inetClassService")
public class InetClassServiceImpl extends ServiceImpl<InetClassDao, InetClassEntity> implements InetClassService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassEntity> page = this.page(
                new Query<InetClassEntity>().getPage(params),
                new QueryWrapper<InetClassEntity>()
        );
        List<InetClassEntity> records = page.getRecords();
        for (InetClassEntity inetClassEntity : records) {
            inetClassEntity.setCreateUserAccountName(sysUserService.getById(inetClassEntity.getCreateUserId()).getAccountName());
        }

        return new PageUtils(page);
    }

}
