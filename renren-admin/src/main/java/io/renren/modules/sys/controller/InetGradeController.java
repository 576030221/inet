package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.InetGradeEntity;
import io.renren.modules.sys.service.InetGradeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 10:33:37
 */
@RestController
@RequestMapping("sys/inetgrade")
public class InetGradeController {
    @Autowired
    private InetGradeService inetGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:inetgrade:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetGradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:inetgrade:info")
    public R info(@PathVariable("id") Long id){
        InetGradeEntity inetGrade = inetGradeService.getById(id);

        return R.ok().put("inetGrade", inetGrade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:inetgrade:save")
    public R save(@RequestBody InetGradeEntity inetGrade){
        inetGradeService.save(inetGrade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:inetgrade:update")
    public R update(@RequestBody InetGradeEntity inetGrade){
        ValidatorUtils.validateEntity(inetGrade);
        inetGradeService.updateById(inetGrade);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:inetgrade:delete")
    public R delete(@RequestBody Long[] ids){
        inetGradeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
