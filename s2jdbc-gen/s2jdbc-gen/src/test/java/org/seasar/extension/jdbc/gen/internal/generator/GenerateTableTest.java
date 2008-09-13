/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.extension.jdbc.gen.internal.generator;

import java.io.File;

import org.junit.Test;
import org.seasar.extension.jdbc.gen.desc.ColumnDesc;
import org.seasar.extension.jdbc.gen.desc.PrimaryKeyDesc;
import org.seasar.extension.jdbc.gen.desc.TableDesc;
import org.seasar.extension.jdbc.gen.generator.GenerationContext;
import org.seasar.extension.jdbc.gen.internal.dialect.MssqlGenDialect;
import org.seasar.extension.jdbc.gen.internal.model.TableModelFactoryImpl;
import org.seasar.extension.jdbc.gen.model.SqlIdentifierCaseType;
import org.seasar.extension.jdbc.gen.model.SqlKeywordCaseType;
import org.seasar.extension.jdbc.gen.model.TableModel;
import org.seasar.framework.util.TextUtil;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class GenerateTableTest {

    private GeneratorImplStub generator = new GeneratorImplStub();

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate_singleId() throws Exception {
        ColumnDesc no = new ColumnDesc();
        no.setName("no");
        no.setDefinition("integer");
        no.setIdentity(true);
        no.setNullable(false);
        no.setUnique(true);

        ColumnDesc name = new ColumnDesc();
        name.setName("name");
        name.setDefinition("varchar");
        name.setNullable(false);
        name.setUnique(false);

        PrimaryKeyDesc primaryKeyDesc = new PrimaryKeyDesc();
        primaryKeyDesc.addColumnName("no");

        TableDesc tableDesc = new TableDesc();
        tableDesc.setCatalogName("AAA");
        tableDesc.setSchemaName("BBB");
        tableDesc.setName("HOGE");
        tableDesc.setCanonicalName("aaa.bbb.hoge");
        tableDesc.addColumnDesc(no);
        tableDesc.addColumnDesc(name);
        tableDesc.setPrimaryKeyDesc(primaryKeyDesc);

        TableModelFactoryImpl factory = new TableModelFactoryImpl(
                new MssqlGenDialect(), SqlIdentifierCaseType.ORIGINALCASE,
                SqlKeywordCaseType.ORIGINALCASE, ';', null);
        TableModel model = factory.getTableModel(tableDesc);

        GenerationContext context = new GenerationContextImpl(model, new File(
                "file"), "sql/create-table.ftl", "UTF-8", false);
        generator.generate(context);

        String path = getClass().getName().replace(".", "/")
                + "_Create_singleId.txt";
        assertEquals(TextUtil.readUTF8(path), generator.getResult());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate_compositeId() throws Exception {
        ColumnDesc no = new ColumnDesc();
        no.setName("no");
        no.setDefinition("integer");
        no.setNullable(false);
        no.setUnique(true);

        ColumnDesc name = new ColumnDesc();
        name.setName("name");
        name.setDefinition("varchar");
        name.setNullable(false);
        name.setUnique(false);

        PrimaryKeyDesc primaryKeyDesc = new PrimaryKeyDesc();
        primaryKeyDesc.addColumnName("no");
        primaryKeyDesc.addColumnName("name");

        TableDesc tableDesc = new TableDesc();
        tableDesc.setCatalogName("AAA");
        tableDesc.setSchemaName("BBB");
        tableDesc.setName("FOO");
        tableDesc.setCanonicalName("aaa.bbb.foo");
        tableDesc.addColumnDesc(no);
        tableDesc.addColumnDesc(name);
        tableDesc.setPrimaryKeyDesc(primaryKeyDesc);

        TableModelFactoryImpl factory = new TableModelFactoryImpl(
                new MssqlGenDialect(), SqlIdentifierCaseType.ORIGINALCASE,
                SqlKeywordCaseType.ORIGINALCASE, ';', null);
        TableModel model = factory.getTableModel(tableDesc);

        GenerationContext context = new GenerationContextImpl(model, new File(
                "file"), "sql/create-table.ftl", "UTF-8", false);
        generator.generate(context);

        String path = getClass().getName().replace(".", "/")
                + "_Create_compositeId.txt";
        assertEquals(TextUtil.readUTF8(path), generator.getResult());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate_noId() throws Exception {
        ColumnDesc no = new ColumnDesc();
        no.setName("no");
        no.setDefinition("integer");
        no.setNullable(true);
        no.setUnique(true);

        ColumnDesc name = new ColumnDesc();
        name.setName("name");
        name.setDefinition("varchar");
        name.setNullable(true);
        name.setUnique(false);

        TableDesc tableDesc = new TableDesc();
        tableDesc.setCatalogName("AAA");
        tableDesc.setSchemaName("BBB");
        tableDesc.setName("BAR");
        tableDesc.setCanonicalName("aaa.bbb.bar");
        tableDesc.addColumnDesc(no);
        tableDesc.addColumnDesc(name);

        TableModelFactoryImpl factory = new TableModelFactoryImpl(
                new MssqlGenDialect(), SqlIdentifierCaseType.ORIGINALCASE,
                SqlKeywordCaseType.ORIGINALCASE, ';', null);
        TableModel model = factory.getTableModel(tableDesc);

        GenerationContext context = new GenerationContextImpl(model, new File(
                "file"), "sql/create-table.ftl", "UTF-8", false);
        generator.generate(context);

        String path = getClass().getName().replace(".", "/")
                + "_Create_noId.txt";
        assertEquals(TextUtil.readUTF8(path), generator.getResult());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate_tableOption() throws Exception {
        ColumnDesc no = new ColumnDesc();
        no.setName("no");
        no.setDefinition("integer");
        no.setIdentity(true);
        no.setNullable(false);
        no.setUnique(true);

        ColumnDesc name = new ColumnDesc();
        name.setName("name");
        name.setDefinition("varchar");
        name.setNullable(false);
        name.setUnique(false);

        PrimaryKeyDesc primaryKeyDesc = new PrimaryKeyDesc();
        primaryKeyDesc.addColumnName("no");

        TableDesc tableDesc = new TableDesc();
        tableDesc.setCatalogName("AAA");
        tableDesc.setSchemaName("BBB");
        tableDesc.setName("HOGE");
        tableDesc.setCanonicalName("aaa.bbb.hoge");
        tableDesc.addColumnDesc(no);
        tableDesc.addColumnDesc(name);
        tableDesc.setPrimaryKeyDesc(primaryKeyDesc);

        TableModelFactoryImpl factory = new TableModelFactoryImpl(
                new MssqlGenDialect(), SqlIdentifierCaseType.ORIGINALCASE,
                SqlKeywordCaseType.ORIGINALCASE, ';', "ENGINE = INNODB");
        TableModel model = factory.getTableModel(tableDesc);

        GenerationContext context = new GenerationContextImpl(model, new File(
                "file"), "sql/create-table.ftl", "UTF-8", false);
        generator.generate(context);

        String path = getClass().getName().replace(".", "/")
                + "_Create_tableOption.txt";
        assertEquals(TextUtil.readUTF8(path), generator.getResult());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDrop() throws Exception {
        ColumnDesc no = new ColumnDesc();
        no.setName("no");
        no.setDefinition("integer");
        no.setIdentity(true);
        no.setNullable(false);
        no.setUnique(true);

        ColumnDesc name = new ColumnDesc();
        name.setName("name");
        name.setDefinition("varchar");
        name.setNullable(false);
        name.setUnique(false);

        PrimaryKeyDesc primaryKeyDesc = new PrimaryKeyDesc();
        primaryKeyDesc.addColumnName("no");

        TableDesc tableDesc = new TableDesc();
        tableDesc.setCatalogName("AAA");
        tableDesc.setSchemaName("BBB");
        tableDesc.setName("HOGE");
        tableDesc.setCanonicalName("aaa.bbb.hoge");
        tableDesc.addColumnDesc(no);
        tableDesc.addColumnDesc(name);
        tableDesc.setPrimaryKeyDesc(primaryKeyDesc);

        TableModelFactoryImpl factory = new TableModelFactoryImpl(
                new MssqlGenDialect(), SqlIdentifierCaseType.ORIGINALCASE,
                SqlKeywordCaseType.ORIGINALCASE, ';', null);
        TableModel model = factory.getTableModel(tableDesc);

        GenerationContext context = new GenerationContextImpl(model, new File(
                "file"), "sql/drop-table.ftl", "UTF-8", false);
        generator.generate(context);

        String path = getClass().getName().replace(".", "/") + "_Drop.txt";
        assertEquals(TextUtil.readUTF8(path), generator.getResult());
    }

}
