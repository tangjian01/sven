package sven.ws.dao.generator;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/27 11:25
 * @description：
 * @version:
 * @see
 */
public class GeneratorStrategy {

    private Properties properties;

    public GeneratorStrategy() {
        properties = getProperties();
    }

    protected void strategyConfig(AutoGenerator autoGenerator, String[] tableNames, List<String> tablePrefixs) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);

        String entityClass = properties.getProperty("super.base.model.class");
        String serviceClass = properties.getProperty("super.base.service.class");
        String implClass = properties.getProperty("super.base.impl.class");
        String controllerClass = properties.getProperty("super.base.controller.class");
        // 公共父类
        strategy.setSuperEntityClass(entityClass);
        strategy.setSuperServiceClass(serviceClass);
        strategy.setSuperServiceImplClass(implClass);
        strategy.setSuperControllerClass(controllerClass);

        if (ArrayUtils.isNotEmpty(tableNames)) {
            strategy.setInclude(tableNames);
        }

        strategy.setEntityTableFieldAnnotationEnable(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        if (CollectionUtils.isNotEmpty(tablePrefixs)) {
            strategy.setTablePrefix(tablePrefixs.toArray(new String[tablePrefixs.size()]));
        }
        autoGenerator.setStrategy(strategy);
    }

    protected Properties getProperties() {
        Properties resource = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("generator.properties");
        try {
            resource.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(resource)) {
            throw new IllegalArgumentException("配置文件 generator.properties未配置");
        }
        return resource;
    }

    public String getPropertie(String key) {
        return getProperties().getProperty(key);
    }

    protected void globalConfig(AutoGenerator autoGenerator) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(properties.getProperty("project.path"));
        gc.setAuthor(Objects.toString(properties.getProperty("file.author"), System.getenv("COMPUTERNAME")));
        gc.setOpen(false);
        gc.setMapperName("%sDao");
        gc.setFileOverride(false);
        gc.setDateType(DateType.ONLY_DATE);

        //实体属性 Swagger2 注解
        autoGenerator.setGlobalConfig(gc);
    }

    protected void dataSourceConfig(AutoGenerator autoGenerator) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        String url = properties.getProperty("jdbc.url");
        String userName = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String driver = properties.getProperty("jdbc.driver");

        dsc.setUrl(url);
        dsc.setDriverName(driver);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        autoGenerator.setDataSource(dsc);
    }

    protected void packageConfig(AutoGenerator autoGenerator) {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        pc.setMapper(properties.getProperty("package.path.dao"));
        pc.setEntity(properties.getProperty("package.path.model"));
        pc.setXml(properties.getProperty("package.path.xml"));
        pc.setService(properties.getProperty("package.path.service"));
        pc.setServiceImpl(properties.getProperty("package.path.impl"));
        pc.setController(properties.getProperty("package.path.controller"));

        pc.setPathInfo(setPathInfo(pc));

        autoGenerator.setPackageInfo(pc);
    }

    public Map<String, String> setPathInfo(PackageConfig pc) {
        Map<String, String> pathInfo = new HashMap<>();
        String basePath = Paths.get(getProjectRootPath(), properties.getProperty("project.path")).toString();
        String entityPath = Paths.get(getProjectRootPath(), properties.getProperty("project.entity.path")).toString();
        String mapperPath = Paths.get(getProjectRootPath(), properties.getProperty("project.mapper.path")).toString();
        String servicePath = Paths.get(getProjectRootPath(), properties.getProperty("project.service.path")).toString();
        String controllerPath = Paths.get(getProjectRootPath(), properties.getProperty("project.controller.path")).toString();
        String xmlPath = Paths.get(getProjectRootPath(), properties.getProperty("project.xml.path")).toString();

        pathInfo.put(ConstVal.ENTITY_PATH, setPathWithPackage(StringUtils.isNoneBlank(entityPath) ? entityPath : basePath, pc.getEntity()));
        pathInfo.put(ConstVal.MAPPER_PATH, setPathWithPackage(StringUtils.isNoneBlank(mapperPath) ? mapperPath : basePath, pc.getMapper()));
        pathInfo.put(ConstVal.SERVICE_PATH, setPathWithPackage(StringUtils.isNoneBlank(servicePath) ? servicePath : basePath, pc.getService()));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, setPathWithPackage(StringUtils.isNoneBlank(servicePath) ? servicePath : basePath, pc.getServiceImpl()));
        pathInfo.put(ConstVal.CONTROLLER_PATH, setPathWithPackage(StringUtils.isNoneBlank(controllerPath) ? controllerPath : basePath, pc.getController()));
        pathInfo.put(ConstVal.XML_PATH, setPathWithPackage(StringUtils.isNoneBlank(xmlPath) ? xmlPath : basePath, pc.getXml()));
        return pathInfo;
    }


    private String setPathWithPackage(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!com.baomidou.mybatisplus.core.toolkit.StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }


    protected void injectionConfig(AutoGenerator autoGenerator) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        cfg.setFileCreate((ConfigBuilder configBuilder, FileType fileType, String filePath) -> {
            // xml 和 entity要重新生成，其它不覆盖
            if (fileType == FileType.XML) {
                return true;
            } else if(fileType == FileType.ENTITY) {
                return true;
            } else {
                return true;
            }
        });

//        // 如果模板引擎是 freemarker
//        String templatePath = "D://a.txt";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
//        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

    }

    protected void templateConfig(AutoGenerator autoGenerator) {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        autoGenerator.setTemplate(templateConfig);
    }

    protected String getProjectRootPath() {
        String configPath = properties.getProperty("project.root.path");
        if(configPath == null || "".equalsIgnoreCase(configPath.trim())) {
            configPath = Paths.get(this.getClass().getResource("/").getPath().substring(1)).getParent().getParent().getParent().getParent().toString();
        }
        return configPath;
    }
}
