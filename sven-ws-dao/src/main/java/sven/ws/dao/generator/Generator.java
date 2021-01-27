package sven.ws.dao.generator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/27 11:21
 * @description：
 * @version:
 * @see
 */
public class Generator {
    public static void main(String[] args) {

        // 代码生成器
        GeneratorStrategy generator = new GeneratorStrategy();
        AutoGenerator mpg = new AutoGenerator();
        // 如果指定表，则只会生成指定的表；如果未指定，则会生成所有
        String tableList = generator.getPropertie("tableList");
        String[] includeTableName = new String[0];
        if(StringUtils.isNotBlank(tableList)) {
            includeTableName = tableList.split(",");
        }
        List<String> tablePrefix = new ArrayList<>();
        tablePrefix.add("sv");
        generator.globalConfig(mpg);
        generator.dataSourceConfig(mpg);
        generator.packageConfig(mpg);
        generator.strategyConfig(mpg, includeTableName, tablePrefix);

        generator.templateConfig(mpg);
        generator.injectionConfig(mpg);
        mpg.execute();

    }
}
