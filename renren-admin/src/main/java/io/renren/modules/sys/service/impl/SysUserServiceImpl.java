/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import io.renren.common.annotation.DataFilter;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.shiro.ShiroUtils;
import io.renren.utils.CheckUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        String gradeNumber = (String) params.get("gradeNumber");
        String accountName = (String) params.get("accountName");
        String status = (String) params.get("status");

        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<SysUserEntity>()
                .like(StringUtils.isNotBlank(username), "username", username)
                .like(StringUtils.isNotBlank(accountName), "account_name", accountName)
                .eq(StringUtils.isNotBlank(gradeNumber), "grade_number", gradeNumber)
                .eq(StringUtils.isNotBlank(status), "status", status)
                .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER));

        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params), wrapper
        );


        for (SysUserEntity sysUserEntity : page.getRecords()) {
            System.out.println(new Gson().toJson(sysUserEntity));
            SysDeptEntity sysDeptEntity = sysDeptService.getById(sysUserEntity.getDeptId());
            sysUserEntity.setDeptName(sysDeptEntity.getName());
        }

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        this.save(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            SysUserEntity userEntity = this.getById(user.getUserId());
            user.setPassword(ShiroUtils.sha256(user.getPassword(), userEntity.getSalt()));
        }
        this.updateById(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }


    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }

    @Override
    public R paramCheckRegist(SysUserEntity user) {
        // 判断参数是否为空
        if (CheckUtil.StringsIsBlank(user.getUsername(), user.getAccountName(), user.getQq())) {
            return R.error("请填写完整信息");
        }
        if (user.getLevel() == null || user.getLevel() == 0) {
            return R.error("请填写完整信息");
        }
        // 判断学号格式是否正确
        String pattern = "^(20)((1|2)[0-9])\\d{6}$";
        if (!CheckUtil.StringPattern(pattern, user.getUsername())) {
            return R.error("学号格式不正确");
        }
//		if (user.getMobile().length()!=11) {
//			return R.error("手机号格式有误");
//		}
        return null;
    }

    @Override
    public void regist(SysUserEntity user) {
        user.setPassword(user.getUsername());
        // 获取其年级 17级 18级 还是 19级
        // 获取当前年份后两位
        int year = Calendar.getInstance().get(Calendar.YEAR) - 2000;
        // 获取当前月份
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        // 判断当前月份是否<8 , 如果小于8 即使是大一的，也要把年份减1  如果>8 ，则不变，那年进来就是哪年的年级
        if (month > 6) {
            user.setLevel(user.getLevel() - 1);
        }
        user.setGradeNumber(String.valueOf(year - user.getLevel()));
        user.setCreateTime(new Date());
        user.setStatus(2);
        this.saveUser(user);
    }

}
