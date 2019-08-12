package hyc.upload.dataupload.datasource;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(1)//bean的加载顺序
@Lazy(false)
public class DataSourceAspect {

    Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("execution(* hyc.upload.dataupload..*.*ServiceImpl.find*(..)) " +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.count*(..))" +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.sel*(..))" +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.get*(..))"
    )
    public void setReadDataSourceType() {
        logger.info("拦截[read]方法");
        System.out.println("拦截[read]方法");
        DataSourceContextHolder.read();
    }

    @Before("execution(* hyc.upload.dataupload..*.*ServiceImpl.insert*(..)) " +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.save*(..))" +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.update*(..))" +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.set*(..))" +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.add*(..))" +
            "|| execution(* hyc.upload.dataupload..*.*ServiceImpl.del*(..))"
    )
    public void setWriteDataSourceType() {
        logger.info("拦截[write]操作");
        System.out.println("拦截[write]操作");
        DataSourceContextHolder.write();
    }

}
