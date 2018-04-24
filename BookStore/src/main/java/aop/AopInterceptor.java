package aop;
import dao.BookDao;
import java.util.Arrays;  

import org.aspectj.lang.JoinPoint;  
import org.aspectj.lang.ProceedingJoinPoint;  
import org.aspectj.lang.annotation.After;  
import org.aspectj.lang.annotation.AfterReturning;  
import org.aspectj.lang.annotation.AfterThrowing;  
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;  
import org.aspectj.lang.annotation.Pointcut;  
  
@Aspect  
public class AopInterceptor {  
  
    @Pointcut("execution(* dao.*.*(..))")  
    public void anyMethod(){}  
      
    @Before("anyMethod()")  
    public void checkMessage(JoinPoint joinPoint){  
        System.out.println("@Before-check!!!");  
        System.out.println("@Before-目标对象为：" + joinPoint.getTarget());  
        System.out.println("@Before-目标方法：" + joinPoint.getSignature().getName());  
        System.out.println("@Before-目标方法的参数：" + Arrays.toString(joinPoint.getArgs()));  
    }  
      
    @AfterReturning(pointcut = "anyMethod()", returning = "obj")  
    public void checkReturn(JoinPoint joinPoint, Object obj){  
        System.out.println("@AfterReturning-目标方法返回值：" + obj);  
        System.out.println("@AfterReturning-生成日志");  
          
        System.out.println("@AfterReturning-目标对象为：" + joinPoint.getTarget());  
        System.out.println("@AfterReturning-目标方法：" + joinPoint.getSignature().getName());  
        System.out.println("@AfterReturning-目标方法的参数：" + Arrays.toString(joinPoint.getArgs()));  
    }  
      
    @After("anyMethod()")  
    public void checkAfter(JoinPoint joinPoint){  
        System.out.println("@After-释放资源！");  
          
        System.out.println("@After-目标对象为：" + joinPoint.getTarget());  
        System.out.println("@After-目标方法：" + joinPoint.getSignature().getName());  
        System.out.println("@After-目标方法的参数：" + Arrays.toString(joinPoint.getArgs()));  
    }  
      
    @Around("anyMethod()")  
    public Object checkAround(ProceedingJoinPoint joinPoint) throws Throwable{  
        System.out.println("@Around！");  
          
        Object[] args = joinPoint.getArgs();  
        if(args != null && args.length > 0 && args[0].getClass() == String.class){  
            args[0] = "@Around" + args[0];  
        }  
        Object result = joinPoint.proceed(args);  
        if(result != null && result.getClass() == String.class){  
            result = result + "--wy";  
        }  
        return result;  
    }  
      
    @AfterThrowing(throwing = "ex", pointcut = "anyMethod()")  
    public void checkAfterThrowing(Throwable ex){  
        System.out.println("@AfterThrowing-异常抛出！");  
        System.out.println("@AfterThrowing-异常日志！");  
          
    }  
      
      
}  