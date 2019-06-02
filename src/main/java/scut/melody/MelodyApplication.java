package scut.melody;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("scut.melody.dao")
public class MelodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelodyApplication.class, args);
    }

}
