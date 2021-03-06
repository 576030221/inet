package io.renren.modules.curriculum.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.curriculum.entity.InetClassNameEntity;
import io.renren.modules.curriculum.service.InetClassNameService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2019-09-26 13:03:46
 */
@RestController
@RequestMapping("curriculum/inetclassname")
public class InetClassNameController {
    @Autowired
    private InetClassNameService inetClassNameService;

    /**
     * 列表 分页
     */
    @RequestMapping("/list")
    @RequiresPermissions("curriculum:inetclassname:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = inetClassNameService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表 全部 根据年级筛选
     */
    @RequestMapping("/listByGradeNumber")
    public R listByGradeNumber(String gradeNumber){
        System.out.println(gradeNumber);
        List<InetClassNameEntity> list = inetClassNameService.listByGradeNumber(gradeNumber);
        System.out.println(new Gson().toJson(list));

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("curriculum:inetclassname:info")
    public R info(@PathVariable("id") Long id){
        InetClassNameEntity inetClassName = inetClassNameService.getById(id);

        return R.ok().put("inetClassName", inetClassName);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("curriculum:inetclassname:save")
    public R save(@RequestBody InetClassNameEntity inetClassName){
        inetClassName.setCreateTime(new Date());
        inetClassName.setCreateUserId(ShiroUtils.getUserId());
        inetClassNameService.save(inetClassName);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("curriculum:inetclassname:update")
    public R update(@RequestBody InetClassNameEntity inetClassName){
        ValidatorUtils.validateEntity(inetClassName);
        inetClassName.setCreateUserId(ShiroUtils.getUserId());
        inetClassNameService.updateById(inetClassName);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("curriculum:inetclassname:delete")
    public R delete(@RequestBody Long[] ids){
        inetClassNameService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
