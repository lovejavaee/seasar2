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
package org.seasar.extension.jdbc.gen.reader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.seasar.extension.jdbc.gen.dialect.StandardGenDialect;
import org.seasar.extension.jdbc.gen.model.DbColumnDesc;
import org.seasar.framework.mock.sql.MockResultSet;
import org.seasar.framework.util.ArrayMap;

import static junit.framework.Assert.*;

/**
 * @author taedium
 * 
 */
public class SchemaReaderImplTest {

    @Test
    public void testGetPrimaryKeys() throws Exception {
        final MockResultSet resultSet = new MockResultSet();

        ArrayMap rowData = new ArrayMap();
        rowData.put("TABLE_CAT", "catalog");
        rowData.put("TABLE_SCHEM", "schema");
        rowData.put("TABLE_NAME", "table");
        rowData.put("COLUMN_NAME", "pk1");
        resultSet.addRowData(rowData);

        rowData = new ArrayMap();
        rowData.put("TABLE_CAT", "catalog");
        rowData.put("TABLE_SCHEM", "schema");
        rowData.put("TABLE_NAME", "table");
        rowData.put("COLUMN_NAME", "pk2");
        resultSet.addRowData(rowData);

        MockDatabaseMetaData metaData = new MockDatabaseMetaData() {

            @Override
            public ResultSet getPrimaryKeys(String catalog, String schema,
                    String table) throws SQLException {
                return resultSet;
            }

        };
        SchemaReaderImpl reader = new SchemaReaderImpl(null,
                new StandardGenDialect());
        Set<String> primaryKeys = reader.getPrimaryKeys(metaData, "schemaName",
                "tableName");
        assertEquals(2, primaryKeys.size());
        assertTrue(primaryKeys.contains("pk1"));
        assertTrue(primaryKeys.contains("pk2"));
    }

    @Test
    public void testGetDbColumnDescs() throws Exception {
        final MockResultSet resultSet = new MockResultSet();

        ArrayMap rowData = new ArrayMap();
        rowData.put("TABLE_CAT", "catalog");
        rowData.put("TABLE_SCHEM", "schema");
        rowData.put("TABLE_NAME", "table");
        rowData.put("COLUMN_NAME", "column1");
        rowData.put("DATA_TYPE", Types.DECIMAL);
        rowData.put("TYPE_NAME", "DECIMAL");
        rowData.put("COLUMN_SIZE", 10);
        rowData.put("BUFFER_LENGTH", null);
        rowData.put("DECIMAL_DIGITS", 3);
        rowData.put("NUM_PREC_RADIX", 10);
        rowData.put("NULLABLE ", DatabaseMetaData.columnNoNulls);
        resultSet.addRowData(rowData);

        rowData = new ArrayMap();
        rowData.put("TABLE_CAT", "catalog");
        rowData.put("TABLE_SCHEM", "schema");
        rowData.put("TABLE_NAME", "table");
        rowData.put("COLUMN_NAME", "column2");
        rowData.put("DATA_TYPE", Types.VARCHAR);
        rowData.put("TYPE_NAME", "VARCHAR");
        rowData.put("COLUMN_SIZE", 10);
        rowData.put("BUFFER_LENGTH", null);
        rowData.put("DECIMAL_DIGITS", 0);
        rowData.put("NUM_PREC_RADIX", 10);
        rowData.put("NULLABLE ", DatabaseMetaData.columnNullable);
        resultSet.addRowData(rowData);

        MockDatabaseMetaData metaData = new MockDatabaseMetaData() {

            @Override
            public ResultSet getColumns(String catalog, String schemaPattern,
                    String tableNamePattern, String columnNamePattern)
                    throws SQLException {
                return resultSet;
            }
        };

        SchemaReaderImpl reader = new SchemaReaderImpl(null,
                new StandardGenDialect());
        List<DbColumnDesc> columnDescs = reader.getDbColumnDescs(metaData,
                "schemaName", "tableName");
        assertEquals(2, columnDescs.size());
        DbColumnDesc desc = columnDescs.get(0);
        assertEquals("column1", desc.getName());
        assertEquals(Types.DECIMAL, desc.getSqlType());
        assertEquals("DECIMAL", desc.getTypeName());
        assertEquals(10, desc.getLength());
        assertEquals(3, desc.getScale());
        assertFalse(desc.isNullable());

        desc = columnDescs.get(1);
        assertEquals("column2", desc.getName());
        assertEquals(Types.VARCHAR, desc.getSqlType());
        assertEquals("VARCHAR", desc.getTypeName());
        assertEquals(10, desc.getLength());
        assertEquals(0, desc.getScale());
        assertTrue(desc.isNullable());
    }

    @Test
    public void testGetTables() throws Exception {
        final MockResultSet resultSet = new MockResultSet();

        ArrayMap rowData = new ArrayMap();
        rowData.put("TABLE_CAT", "catalog");
        rowData.put("TABLE_SCHEM", "schema");
        rowData.put("TABLE_NAME", "table1");
        resultSet.addRowData(rowData);

        rowData = new ArrayMap();
        rowData.put("TABLE_CAT", "catalog");
        rowData.put("TABLE_SCHEM", "schema");
        rowData.put("TABLE_NAME", "table2");
        resultSet.addRowData(rowData);

        MockDatabaseMetaData metaData = new MockDatabaseMetaData() {

            @Override
            public ResultSet getTables(String catalog, String schemaPattern,
                    String tableNamePattern, String[] types)
                    throws SQLException {
                return resultSet;
            }
        };

        SchemaReaderImpl reader = new SchemaReaderImpl(null,
                new StandardGenDialect());
        List<String> tables = reader.getTables(metaData, "schemaName");
        assertEquals(2, tables.size());
        assertEquals("table1", tables.get(0));
        assertEquals("table2", tables.get(1));
    }

}