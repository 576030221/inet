package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/sys/constant")
public class ConstantController extends AbstractController {

    /**
     * 获取可选年级
     * /sys/constant/getGrade
     */
    @RequestMapping("/getGrade")
    public R getGrade(){
        List<String> list = new LinkedList();
        // 获取当前年份后两位
        int year = Calendar.getInstance().get(Calendar.YEAR) - 2000;
        // 获取当前月份
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        list.add(String.valueOf(year-2));
        list.add(String.valueOf(year-1));
        if (month>6) {
            list.add(String.valueOf(year));
        }else {
            list.add(0, String.valueOf(year-3));
        }
        return R.ok().put("list",list);
    }
}
