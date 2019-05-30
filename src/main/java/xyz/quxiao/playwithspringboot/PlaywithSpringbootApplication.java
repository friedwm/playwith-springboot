package xyz.quxiao.playwithspringboot;

import cn.hutool.http.HtmlUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaywithSpringbootApplication implements ApplicationRunner {

  private static final Logger logger = LoggerFactory.getLogger(PlaywithSpringbootApplication.class);

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
      List<List<Object>> rows = reader.read();
      ExcelWriter writer = ExcelUtil.getWriter(newName);
      logger.info("file:{}, dest:{}", file, newName);
      CollectionUtils.emptyIfNull(rows).stream().forEach(row -> {
        List<String> newRow = row.stream().map(c -> {
          return HtmlUtil.unescape(String.valueOf(c));
        }).collect(Collectors.toList());

        writer.writeRow(newRow);
      });

      writer.close();
      reader.close();

    }
  }
}
