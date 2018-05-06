package com.hhui.api;

import com.hhui.entity.User;
import com.hhui.vo.UserCol;
import com.hhui.vo.UserColVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Api(value = "admin", description = "admin",tags = {"admin"})
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/admin/hhui")
public class TestController {

    @ApiOperation(value = "test",notes = "test")
    @GetMapping(value = "/test")
    public User test(User temp){
        User user = new User();
        user.setId(3L);
        user.setName("hhui");

        String zone = TimeZone.getDefault().getID();
        Date date2 = new Date();
        Date date3 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault().toString()));
        String dateStr = sdf.format(date2);
        System.out.println("date2=="+date2);
        try{
            date3 = sdf2.parse(dateStr);
            System.err.println("date3==="+date3);
        }catch (Exception e){
            e.printStackTrace();
        }


//        String timeStr = "2017-8-24 11:17:10"; // 字面时间
//        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
//        bjSdf.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault().toString()));
//        Date date = null;
//                try{
//                   date = bjSdf.parse(dateStr);  // 将字符串时间按北京时间解析成Date对象
//                    System.err.println(date);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
        return user;
    }

    @PostMapping(value = "/testInvoke")
    public User testInvoke(@RequestBody @Validated UserColVO userColVO) throws Exception{
        Class<User> u = User.class;
        User user = null;
        List<UserCol> userCols = userColVO.getUserCols();
            User o = u.newInstance();
            for(int i=0;i<userCols.size();i++){
                Field field = u.getDeclaredField(userCols.get(i).getName());
                field.setAccessible(true);
                String colType = field.getType().getName();
                if(colType.endsWith("String")){
                    field.set(o,userCols.get(i).getValue());
                }else if(colType.endsWith("Integer") || colType.endsWith("int")) {
                    field.set(o,Integer.valueOf(userCols.get(i).getValue()));
                }else if(colType.endsWith("Long")){
                    field.set(o,Long.valueOf(userCols.get(i).getValue()));
                }

            }
            user = o;
        return user;
    }
}
