package com.maven.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maven.Bean.User;
import com.maven.Dto.LoginFormMessage;
import com.maven.Service.Impl.UserServiceImpl;
import com.maven.Util.CreateVerifiCodeImageUtil;
import org.apache.ibatis.session.SqlSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢秉均
 * @date 2022/8/30--21:57
 */

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    //存储预返回页面的数据对象
    private Map<String,Object> result=new HashMap<>();

    //日志记录器
    private static final Logger logger= LoggerFactory.getLogger(SystemController.class);


    /**
    *@description：登录验证
    *@Param:
    *@return:
    *@Author: 谢秉均
    *@date: 2022/9/1--14:28
    */
    @ResponseBody
    @RequestMapping(value = "/loginform",method = RequestMethod.POST)
    public Object register(HttpServletRequest request, LoginFormMessage message){
        /**
         * 表单提交处理方式
         */
        logger.info("上传表单参数："+message.toString());
        ModelAndView modelAndView = new ModelAndView();//设置视图模型
        HttpSession session = request.getSession();
        String verifiCode = null; //存在session的验证码
        if(session.getAttribute("verifiCode") != null ){
            verifiCode = (String)session.getAttribute("verifiCode");
            logger.info("验证码不为空:"+verifiCode);
        }
        else {
            logger.info("验证码为空:"+verifiCode);
        }
        if(!verifiCode.equals(message.getVerifiCode()) ){//判断验证码是否正确，无论结果对否都移除session的对应verifiCode，需要客户端重新获取
            result.put("status","verifiCode error");
            session.removeAttribute("verifiCode");
            String jsonObject = JSON.toJSONString(result);
            return jsonObject;
        }
        session.removeAttribute("verifiCode");  //验证码对也要移除
        try {
            Boolean result2 = userServiceImpl.selectByJobAndPasswordService(message); //判断账号、密码是否正确
            if(result2){
                message.setName(userServiceImpl.selectByJobnumber(message.getJobNumber()).getName());   //给userinfo设置用户名属性
                session.setAttribute("userinfo",message);   //加入到session域记录用户信息
                modelAndView.setViewName("templates/MainModule/main");  //成功则跳转到主页面
//                return modelAndView;
                result.put("status","success");
                String jsonObject = JSON.toJSONString(result);
                return jsonObject;
            }
        }catch (Exception e){
            logger.error("sql异常："+e.toString());
        }
        modelAndView.setViewName("templates/system/login"); //失败则跳转回登录界面，后续优化
//        return modelAndView;
        result.put("status","error");
        String jsonObject2 = JSON.toJSONString(result);
        return jsonObject2;
    }

    /**
     * 注册用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/adduser")
    public Object AddUser(User users,HttpServletRequest request) {
        String verifiCode = (String)request.getSession().getAttribute("verifiCode");
        System.out.println("值："+users);
        if(users==null || users.getJobNumber()==null){
            result.put("status","用户信息为空");
            return result;
        }else if (!users.getVerifiCode().equals(verifiCode)){
            result.put("status","验证码错误");
            logger.warn("验证码校验错误");
            return result;
        }else {
            User user= userServiceImpl.selectByJobnumber(users.getJobNumber());
            //若查询返回为0条数据，则对象为null
            if(user==null){
                try {
                    //如果新添加用户没有对账号类型进行选择，默认不是超管
                    if(users.getIssuperadmin()==null || users.getIssuperadmin()==2 || users.getIssuperadmin()==-1){
                        users.setIssuperadmin(0);
                    }else if(users.getIssuperadmin()==3){
                        //设置为超管
                        users.setIssuperadmin(1);
                    }
                    //新建用户是未删除状态
                    users.setIsdeleted(0);
                    String format = LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
                    );  // 取当前时间并将datetime转字符串
                    LocalDateTime dateTime = LocalDateTime.parse(
                            format,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
                    );  // 字符串转datetime;
                    users.setCreateusertime(dateTime);
                    userServiceImpl.AddUser(users);
                    result.put("status","success");
                    return result;
                }catch (Exception e){
                    result.put("status","error，添加失败");
                    e.printStackTrace();
                    return result;
                }
            }else{
                result.put("status","error，已存在该用户");
                return result;
            }
        }
    }


    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response){
        //生成验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImageUtil.getVerifiCodeImage();
        //将验证码String存在session
        String verifiCode = String.valueOf(CreateVerifiCodeImageUtil.getVerifiCode());
        HttpSession session=request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        //将验证码图片输出到登录界面
        try{
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
            logger.error("验证码图片获取成功");
        }catch (IOException e){
            e.printStackTrace();
            logger.error("验证码图片获取失败");
        }
    }
}
