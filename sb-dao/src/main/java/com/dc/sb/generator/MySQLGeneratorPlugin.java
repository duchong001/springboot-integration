/**
 * Wangyin.com Inc.
 * Copyright (c) 2003-2014 All Rights Reserved.
 */
package com.dc.sb.generator;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 扩展mybaties生成代码,添加字段注释.
 * @author DUCHONG
 */
public class MySQLGeneratorPlugin extends PluginAdapter {

    /**
     * generate 方法
     */
    public static void generate() {
        String config = MySQLGeneratorPlugin.class.getClassLoader().getResource("META-INF/mybatis/mybatis-generator-config.xml").getFile();
        String[] arg = {"-configfile", config, "-overwrite"};
        ShellRunner.main(arg);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        //interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
        interfaze.addAnnotation("@Repository");
        //interfaze.addAnnotation("@Transactional");
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return true;
    }

    private void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
        List<Field> fields = topLevelClass.getFields();
        Map<String, Field> map = new HashMap<String, Field>();
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columns) {
            Field f = map.get(column.getJavaProperty());
            if (f != null) {
                f.getJavaDocLines().clear();
                if (column.getRemarks() != null) {
                    f.addJavaDocLine("/** ");
                    f.addJavaDocLine("* " + column.getRemarks());
                    f.addJavaDocLine("* DB Column Name: " + column.getActualColumnName());
                    f.addJavaDocLine("*/");
                }
            }
        }
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * MySQLGeneratorPlugin main method
     *
     * @param args
     */
    public static void main(String[] args) {
        generate();
    }
}
