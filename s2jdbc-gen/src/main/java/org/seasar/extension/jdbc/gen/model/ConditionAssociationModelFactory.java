/*
 * Copyright 2004-2013 the Seasar Foundation and the Others.
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

import org.seasar.extension.jdbc.PropertyMeta;

/**
 * {@link ConditionAssociationModel 条件クラスの関連モデル}のファクトリです。
 * 
 * @author taedium
 */
public interface ConditionAssociationModelFactory {

    /**
     * 条件クラスの関連モデルを返します。
     * 
     * @param propertyMeta
     *            プロパティメタデータ
     * @return 条件クラスの関連モデル
     */
    ConditionAssociationModel getConditionAssociationModel(
            PropertyMeta propertyMeta);
}
