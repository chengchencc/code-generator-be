package com.ludan.generator;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @author: chengchen
 * @create: 2021-03-16 10:45
 **/
public class DateTest {
    @Test
    public void calc_test() {
        Date startDate = DateUtil.date();
        logDate("startDate",startDate);
        Date endDate = DateUtil.offsetMonth(startDate,36);
        endDate = DateUtil.offsetDay(endDate,30);
        logDate("endDate",endDate);

        Date iterator = startDate;
        int time = 1;

//        List<ProjectTimesDto> timesDtos = new ArrayList<>();
        while (DateUtil.compare(DateUtil.offsetMonth(iterator,1) ,endDate) < 0 ){
            Date offset = DateUtil.offsetMonth(iterator,12);
            if (DateUtil.compare(DateUtil.offsetMonth(offset,1) ,endDate) > 0){
//                timesDtos.add(new ProjectTimesDto(time,iterator,endDate));
                System.out.println("第"+time+"期:"+format(iterator)+"-"+format(endDate));
            }else {
//                timesDtos.add(new ProjectTimesDto(time,iterator,offset));
                System.out.println("第"+time+"期:"+format(iterator)+"-"+format(offset));
            }
            iterator = offset;
            time++;
        }
    }

    public void logDate(String name,Date date){
        System.out.println(name+"::"+ DateUtil.format(date, DatePattern.NORM_DATETIME_PATTERN));
    }
    public String format(Date date){
        return DateUtil.format(date,DatePattern.NORM_DATETIME_PATTERN);
    }
}


