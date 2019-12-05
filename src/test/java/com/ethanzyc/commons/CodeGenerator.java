package com.ethanzyc.commons;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author ethan
 * @date 2019/8/26 22:36
 */
public class CodeGenerator {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://49.234.11.97:33306/lechi?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        generate("employee", "employee");
    }
    /**
     *
     */
    public static void generate(String moudule,String table) {
        String outputDir = "/Users/ethan/Documents/工作/lechi/commons/src/test/java/com/ethanzyc/commons/code";
        String parent = "com.ethanzyc.commons.business";
//        String basePackage = parent + "." + moudule;
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setOpen(false);
        /**
         * XML 二级缓存
         */
        gc.setEnableCache(false);
        gc.setAuthor("ethan");
        gc.setActiveRecord(true);
        /**
         * XML ResultMap
         */
        gc.setBaseResultMap(true);
        /**
         * XML columList
         */
        gc.setBaseColumnList(true);
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setIdType(IdType.AUTO);
        //TODO: 临时解决方案  推荐java8
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        dsc.setUrl(URL);
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix("t_");// 此处可以修改为您的表前缀
        /**
         * 表名生成策略
         */
        strategy.setNaming(NamingStrategy.underline_to_camel);
        /**
         * 需要生成的表
         */
        strategy.setInclude(table);
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        strategy.setEntityLombokModel(true);

        // 自定义 controller 父类
         strategy.setSuperControllerClass("com.ethanzyc.commons.business.controller.base.BaseController");
        strategy.setRestControllerStyle(true);
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        strategy.setEntityColumnConstant(true);
        strategy.setLogicDeleteFieldName("enable");
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parent);
//        pc.setModuleName(moudule);
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("tpl/entity.java.vm");
        tc.setMapper("tpl/mapper.java.vm");
        tc.setXml("tpl/mapper.xml.vm");
        tc.setService("tpl/service.java.vm");
        tc.setServiceImpl("tpl/serviceImpl.java.vm");
        tc.setController("tpl/controller.java.vm");
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
}
