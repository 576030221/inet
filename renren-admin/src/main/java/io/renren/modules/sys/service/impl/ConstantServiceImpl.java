package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.service.ConstantService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service("constantService")
public class ConstantServiceImpl implements ConstantService {

    @Override
    public List<String> getGrade() {
        List<String> list = new LinkedList<>();
        // 获取当前年份后两位
        int year = Calendar.getInstance().get(Calendar.YEAR) - 2000;
        // 获取当前月份
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;

        list.add(String.valueOf(year));
        list.add(String.valueOf(year+1));
        // 如果当前月份已经放暑假了，那么起始年级就是当前的年份
        if (month>6) {
            list.add(String.valueOf(year+3));
        }else {
            // 如果当前月份是上半年，那么表示，还有一些去年入学的人没有升到大二，那么要把年份减1放进去，而且是放在第一个
            list.add(0,String.valueOf(year-1));
        }
        return list;
    }
}
