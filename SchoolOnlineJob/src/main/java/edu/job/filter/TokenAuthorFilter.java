package edu.job.filter;

import com.alibaba.fastjson.JSON;
import edu.job.dto.RequestResult;
import edu.job.enumeration.RequestStatusEnum;
import edu.job.utils.RequestCacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;



/**
 * token验证拦截
 * 过滤器
 */
@Component
@WebFilter(urlPatterns = { "/api/*" }, filterName = "tokenAuthorFilter")
public class TokenAuthorFilter implements Filter {


    private static Logger logger = LoggerFactory
            .getLogger(TokenAuthorFilter.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        rep.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        rep.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = req.getHeader("token");//header方式
        RequestResult resultInfo = new RequestResult();
        boolean isFilter = false;


        String method = ((HttpServletRequest) request).getMethod();
        if (method.equals("OPTIONS")) {
            rep.setStatus(HttpServletResponse.SC_OK);
        }else{


            if (null == token || token.isEmpty()) {
                resultInfo.setStatus(RequestStatusEnum.ACCESSDENIED.getCode());
                resultInfo.setMsg("用户授权认证没有通过!客户端请求参数中无token信息");
            } else {
                if (RequestCacheUtils.getCache(token) != null) {
                    resultInfo.setStatus(RequestStatusEnum.SUCCESS.getCode());
                    resultInfo.setMsg("用户授权认证通过!");
                    isFilter = true;
                } else {
                    resultInfo.setStatus(RequestStatusEnum.ACCESSDENIED.getCode());
                    resultInfo.setMsg("用户授权认证没有通过!客户端请求参数token信息无效");
                }
            }
            if (RequestStatusEnum.ACCESSDENIED.getCode().equals(resultInfo.getStatus())) {// 验证失败
                PrintWriter writer = null;
                OutputStreamWriter osw = null;
                try {
                    osw = new OutputStreamWriter(response.getOutputStream(),
                            "UTF-8");
                    writer = new PrintWriter(osw, true);
                    String jsonStr = JSON.toJSONString(resultInfo);
                    writer.write(jsonStr);
                    writer.flush();
                    writer.close();
                    osw.close();
                } catch (UnsupportedEncodingException e) {
                    logger.error("过滤器返回信息失败:" + e.getMessage(), e);
                } catch (IOException e) {
                    logger.error("过滤器返回信息失败:" + e.getMessage(), e);
                } finally {
                    if (null != writer) {
                        writer.close();
                    }
                    if (null != osw) {
                        osw.close();
                    }
                }
                return;
            }

            if (isFilter) {
                logger.info("token filter过滤ok!");
                chain.doFilter(request, response);
            }
        }


    }

    @Override
    public void init(FilterConfig filterReg) throws ServletException {
        //Filter过滤器初始化
    }

}