package hyc.upload.dataupload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan(value = {"hyc.upload.dataupload.uploadDao.*"})
@ComponentScan("hyc.upload.*")
@EnableAutoConfiguration
public class DatauploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatauploadApplication.class, args);
	}
}
//aop实现分布式锁，以及定义一个接口，重写里面的实现方法，来处理获取获取锁失败的处理。
