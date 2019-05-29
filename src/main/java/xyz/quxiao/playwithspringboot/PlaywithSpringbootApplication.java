package xyz.quxiao.playwithspringboot;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import java.io.File;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaywithSpringbootApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(PlaywithSpringbootApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {
    List<String> files = applicationArguments.getOptionValues("file");
    if (CollectionUtils.isNotEmpty(files)) {
      String file = files.get(0);
      String fileName = file.substring(0, file.lastIndexOf("."));
      String newName = fileName + "_clean.xls";

      ExcelReader reader = ExcelUtil.getReader(new File(file));
    }
  }
}
