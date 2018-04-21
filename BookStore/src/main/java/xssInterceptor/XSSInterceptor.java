package xssInterceptor;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class XSSInterceptor extends AbstractInterceptor{

	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // TODO Auto-generated method stub
        ActionContext actionContext = invocation.getInvocationContext();
        Map<String, Object> map = actionContext.getParameters();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String value =  ((String[])(entry.getValue()))[0];
            entry.setValue(StringEscapeUtils.escapeSql(StringEscapeUtils.escapeHtml(value)));//将提交上来的字符串进行转码
            System.out.println((entry.getValue()));
        }
        return invocation.invoke();
    }
}