package io.renren.modules.curriculum.service.impl;

import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.curriculum.dao.InetClassNameDao;
import io.renren.modules.curriculum.entity.InetClassNameEntity;
import io.renren.modules.curriculum.service.InetClassNameService;


@Service("inetClassNameService")
public class InetClassNameServiceImpl extends ServiceImpl<InetClassNameDao, InetClassNameEntity> implements InetClassNameService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InetClassNameEntity> page = this.page(
                new Query<InetClassNameEntity>().getPage(params),
                new QueryWrapper<InetClassNameEntity>()
                        .like(StringUtils.isNotBlank((String)params.get("name")),"name",params.get("name"))
                        .eq(StringUtils.isNotBlank((String)params.get("gradeNumber")),"grade_number",params.get("gradeNumber"))
                        .orderByDesc("create_time")
        );
        List<InetClassNameEntity> records = page.getRecords();
        for (InetClassNameEntity inetClassNameEntity : records) {
            SysUserEntity sysUserEntity = sysUserService.getById(inetClassNameEntity.getCreateUserId());
            inetClassNameEntity.setCreateUserAccountName(sysUserEntity.getAccountName());
        }

        return new PageUtils(page);
    }

    @Override
    public List<InetClassNameEntity> listByGradeNumber(String gradeNumber) {
        return this.list(new QueryWrapper<InetClassNameEntity>().eq("grade_number",gradeNumber));
    }

}
