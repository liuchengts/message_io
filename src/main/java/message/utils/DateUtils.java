package message.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 2017/9/14.
 * 时间工具包
 */
public class DateUtils {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E 当年中的第D天   当年中第w个星期  当月中第W个星期  a k时  时区：z");
    private static Date date=new Date();
    private static Calendar cal=Calendar.getInstance();
    /*****
     * 获得当前时间 完整
     */
    public static String getDate_WZ(){
        return format.format(date);
    }
    /*****
     * 消息前面的时间戳
     */
    public static String getDate_sj(){
        return "["+format.format(date).substring(0, 19)+"]:";
    }
    /*****
     * 获得当前时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getDate(){
        return format.format(date).substring(0, 19);
    }
    /*****
     * 获得当前时间 yyyy-MM-dd
     */
    public static String getDate_YMD(){
        return format.format(date).substring(0, 10);
    }
    /*****
     * 获得时间差  返回秒数
     * start 开始时间
     * end   结束时间
     */
    public static int getDateDifference_INT(Date start,Date end){
        long between=(end.getTime()-start.getTime())/1000;
        return Integer.parseInt(String.valueOf(between));
    }
    /*****
     * 获得时间差  返回相差  天/小时/分/秒
     * start 开始时间
     * end   结束时间
     */
    public static String getDateDifference_String(Date start,Date end){
        long between=(end.getTime()-start.getTime())/1000;
        long day1=between/(24*3600);
        long hour1=between%(24*3600)/3600;
        long minute1=between%3600/60;
        long second1=between%60/60;
        return day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒";
    }
    /*****
     * 获得时间差  返回相差  天/小时/分/秒
     * start 开始时间
     * end   结束时间
     */
    public static String getDateDifference_String(String start,String end){
        Date startDate,endDate;
        String datestr ="";
        try {
            startDate = format.parse(start);
            endDate=format.parse(end);
            long between=(endDate.getTime()-startDate.getTime())/1000;
            long day1=between/(24*3600);
            long hour1=between%(24*3600)/3600;
            long minute1=between%3600/60;
            long second1=between%60/60;
            datestr= day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒";
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("时间计算错误:",e);
        }
        return datestr;
    }
    /*****
     * 判断年是平年还是闰年 true平  false润
     * newdate 时间
     */
    public static boolean getPIsW(Date newdate){
        boolean fag=false;
        int year=Integer.parseInt(format.format(newdate).substring(0,4));
        if((year % 400 == 0)||(year % 4 == 0)&&(year % 100 != 0)){
            fag=true;
        }
        return fag;
    }
    /*****
     * 判断年是平年还是闰年 true平  false润
     * year 时间
     */
    public static boolean getPIsW(Integer year){
        getVYear(year);
        boolean fag=false;
        if((year % 400 == 0)||(year % 4 == 0)&&(year % 100 != 0)){
            fag=true;
        }
        return fag;
    }
    /*****
     * 判断当前年  是平年还是闰年 true平  false润
     */
    public static boolean getPIsW(){
        return getPIsW(date);
    }
    /*****
     * 获得当前年份
     */
    public static Integer getYear(){
        return cal.get(Calendar.YEAR);
    }
    /*****
     * 获得当前月份
     */
    public static Integer getMonth(){
        return cal.get(Calendar.MONTH )+1;
    }
    /*****
     * 获得当前当月最大天数
     */
    public static Integer getDay(){
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /*****
     * 获得指定月的最大天数
     * month  指定月份
     */
    public static Integer getDay(Integer month){
        getVMonth(month);
        cal.set(getYear(), month, 0);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /*****
     * 获得指定年月的最大天数
     * year 年
     * month 月
     */
    public static Integer getDay(Integer year,Integer month){
        getVYear(year);
        getVMonth(month);
        cal.set(year, month, 0);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /*****
     * 获得当前第几天
     */
    public static Integer getDayCurrent(){
        return Integer.parseInt(format.format(date).substring(8,10));
    }
    /*****
     * 获得当前星期几
     */
    public static String getWeek(){
        return format.format(date).substring(20,23);
    }
    /*****
     * 获得某年某月某天是星期几
     * year 年
     * month 月
     * day   天
     */
    public static String getWeek(Integer year,Integer month,Integer day){
        getVYear(year);
        getVMonth(month);
        Integer _day=getDay(year, month);
        if(_day<day){
            throw new RuntimeException("天数输入不合法:"+day);
        }
        //年份必须减一才能使用formcat获得正确时间
        cal.set(year, month-1, day);
        return format.format(cal.getTime()).substring(20,23);
    }
    /***
     * 输入的年份验证
     * year 年
     */
    public static void getVYear(Integer year){
        if(null==year ||0>=year || year.toString().length()>4|| year.toString().length()<4){
            throw new RuntimeException("年份输入不合法:"+year);
        }
    }
    /***
     * 输入的月份验证
     * month 月
     */
    public static void getVMonth(Integer month){
        if(null==month ||0>=month ||month>12){
            throw new RuntimeException("月份输入不合法:"+month);
        }
    }
    /***
     * 输入的天数验证
     * day 天
     */
    public static void getVDay(Integer day){
        if(null==day ||0>=day ||day>31){
            throw new RuntimeException("天数输入不合法:"+day);
        }
    }
}
