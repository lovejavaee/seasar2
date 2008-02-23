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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.seasar.extension.jdbc.gen.GenDialect;
import org.seasar.extension.jdbc.gen.SchemaReader;
import org.seasar.extension.jdbc.gen.model.DbColumnDesc;
import org.seasar.extension.jdbc.gen.model.DbTableDesc;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.jdbc.util.DataSourceUtil;
import org.seasar.extension.jdbc.util.DatabaseMetaDataUtil;
import org.seasar.framework.exception.SQLRuntimeException;
import org.seasar.framework.util.ResultSetUtil;

/**
 * {@code SchemaReader}の実装クラスです。
 * 
 * @author taedium
 */
public class SchemaReaderImpl implements SchemaReader {

    /** データソース */
    protected DataSource dataSource;

    /** 方言 */
    protected GenDialect dialect;

    /**
     * インスタンスを構築します。
     * 
     * @param dataSource
     *            データソース
     * @param dialect
     *            方言
     */
    public SchemaReaderImpl(DataSource dataSource, GenDialect dialect) {
        this.dataSource = dataSource;
        this.dialect = dialect;
    }

    public List<DbTableDesc> read(String schemaName) {
        Connection con = DataSourceUtil.getConnection(dataSource);
        try {
            DatabaseMetaData metaData = ConnectionUtil.getMetaData(con);
            String schema = schemaName != null ? schemaName
                    : getDefaultSchemaName(metaData);
            List<DbTableDesc> result = new ArrayList<DbTableDesc>();
            for (String table : getTables(metaData, schema)) {
                if (!dialect.isUserTable(table)) {
                    continue;
                }
                DbTableDesc tableDesc = new DbTableDesc();
                tableDesc.setName(table);

                Set<String> primaryKeys = getPrimaryKeys(metaData, schema,
                        table);
                for (DbColumnDesc cm : getDbColumnDescs(metaData, schema, table)) {
                    if (primaryKeys.contains(cm.getName())) {
                        cm.setPrimaryKey(true);
                    }
                    tableDesc.addColumnDesc(cm);
                }
                result.add(tableDesc);
            }
            return result;
        } finally {
            ConnectionUtil.close(con);
        }
    }

    /**
     * デフォルトのスキーマ名を返します。
     * 
     * @param metaData
     *            メタデータ
     * @return デフォルトのスキーマ名
     */
    protected String getDefaultSchemaName(DatabaseMetaData metaData) {
        String userName = DatabaseMetaDataUtil.getUserName(metaData);
        return dialect.getDefaultSchemaName(userName);
    }

    /**
     * テーブル名のセットを返します。
     * 
     * @param metadata
     *            メタデータ
     * @param schemaName
     *            スキーマ名
     * @return テーブル名のセット
     */
    protected List<String> getTables(DatabaseMetaData metadata,
            String schemaName) {
        List<String> result = new ArrayList<String>();
        try {
            ResultSet rs = metadata.getTables(null, schemaName, null,
                    new String[] { "TABLE" });
            try {
                while (rs.next()) {
                    result.add(rs.getString(3));
                }
                return result;
            } finally {
                ResultSetUtil.close(rs);
            }
        } catch (SQLException e) {
            throw new SQLRuntimeException(e);
        }
    }

    /**
     * カラム記述のリストを返します。
     * 
     * @param metaData
     *            メタデータ
     * @param schemaName
     *            スキーマ名
     * @param tableName
     *            テーブル名
     * @return カラム記述のリスト
     */
    protected List<DbColumnDesc> getDbColumnDescs(DatabaseMetaData metaData,
            String schemaName, String tableName) {
        List<DbColumnDesc> result = new ArrayList<DbColumnDesc>();
        try {
            ResultSet rs = metaData.getColumns(null, schemaName, tableName,
                    null);
            try {
                while (rs.next()) {
                    DbColumnDesc columnDesc = new DbColumnDesc();
                    columnDesc.setName(rs.getString(4));
                    columnDesc.setSqlType(rs.getInt(5));
                    columnDesc.setTypeName(rs.getString(6));
                    columnDesc.setLength(rs.getInt(7));
                    columnDesc.setScale(rs.getInt(9));
                    columnDesc.setNullable(rs.getBoolean(11));
                    result.add(columnDesc);
                }
                return result;
            } finally {
                ResultSetUtil.close(rs);
            }
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * 主キーのセットを返します。
     * 
     * @param metaData
     *            メタデータ
     * @param schemaName
     *            スキーマ名
     * @param tableName
     *            テーブル名
     * @return 主キーのセット
     */
    protected Set<String> getPrimaryKeys(DatabaseMetaData metaData,
            String schemaName, String tableName) {
        Set<String> result = new HashSet<String>();
        try {
            ResultSet rs = metaData.getPrimaryKeys(null, schemaName, tableName);
            try {
                while (rs.next()) {
                    result.add(rs.getString(4));
                }
            } finally {
                ResultSetUtil.close(rs);
            }
            return result;
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }
}
