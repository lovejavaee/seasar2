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
package org.seasar.extension.jdbc.gen.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * エンティティクラスのモデルです。
 * 
 * @author taedium
 */
public class EntityModel extends ClassModel {

    /** カタログ名 */
    protected String catalogName;

    /** スキーマ名 */
    protected String schemaName;

    /** テーブル名 */
    protected String tableName;

    /** 複合識別子を持つ場合{@code true} */
    protected boolean compositeId;

    /** 属性モデルのリスト */
    protected List<AttributeModel> attributeModelList = new ArrayList<AttributeModel>();

    /** 関連モデルのリスト */
    protected List<AssociationModel> associationModelList = new ArrayList<AssociationModel>();

    /** 複合一意制約モデルのリスト */
    protected List<CompositeUniqueConstraintModel> compositeUniqueConstraintModelList = new ArrayList<CompositeUniqueConstraintModel>();

    /**
     * カタログ名を返します。
     * 
     * @return カタログ名
     */
    public String getCatalogName() {
        return catalogName;
    }

    /**
     * カタログ名を設定します。
     * 
     * @param catalogName
     *            カタログ名
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    /**
     * スキーマ名を返します。
     * 
     * @return スキーマ名
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * スキーマ名を設定します。
     * 
     * @param schemaName
     *            スキーマ名
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * テーブル名を返します。
     * 
     * @return テーブル名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * テーブル名を設定します。
     * 
     * @param tableName
     *            テーブル名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 属性モデルを返します。
     * 
     * @return 属性モデル
     */
    public List<AttributeModel> getAttributeModelList() {
        return Collections.unmodifiableList(attributeModelList);
    }

    /**
     * 属性モデルを追加します。
     * 
     * @param attributeModel
     *            属性モデル
     */
    public void addAttributeModel(AttributeModel attributeModel) {
        attributeModelList.add(attributeModel);
    }

    /**
     * 関連モデルのリストを返します。
     * 
     * @return 関連モデルのリスト
     */
    public List<AssociationModel> getAssociationModelList() {
        return Collections.unmodifiableList(associationModelList);
    }

    /**
     * 関連モデルを追加します。
     * 
     * @param associationModel
     *            関連モデル
     */
    public void addAssociationModel(AssociationModel associationModel) {
        associationModelList.add(associationModel);
    }

    /**
     * 複合一意制約モデルのリストを返します。
     * 
     * @return 複合一意制約モデルのリスト
     */
    public List<CompositeUniqueConstraintModel> getCompositeUniqueConstraintModelList() {
        return Collections.unmodifiableList(compositeUniqueConstraintModelList);
    }

    /**
     * 複合一意制約モデルを追加します。
     * 
     * @param compositeUniqueConstraintModel
     *            複合一意制約モデル
     */
    public void addCompositeUniqueConstraintModel(
            CompositeUniqueConstraintModel compositeUniqueConstraintModel) {
        compositeUniqueConstraintModelList.add(compositeUniqueConstraintModel);
    }

    /**
     * 複合識別子を持つ場合{@code true}を返します。
     * 
     * @return 複合識別子を持つ場合{@code true}
     */
    public boolean hasCompositeId() {
        return compositeId;
    }

    /**
     * 複合識別子を持つ場合{@code true}を設定します。
     * 
     * @param compositeId
     *            複合識別子を持つ場合{@code true}
     */
    public void setCompositeId(boolean compositeId) {
        this.compositeId = compositeId;
    }

}