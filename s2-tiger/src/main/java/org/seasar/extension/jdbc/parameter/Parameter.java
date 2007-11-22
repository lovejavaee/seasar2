/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.extension.jdbc.parameter;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.TemporalType;

/**
 * 値をラップし特別な意味を持たせるクラスです。
 * 
 * @author taedium
 * 
 */
public class Parameter {

    /**
     * {@link TemporalType#DATE}用のパラメータを作成します。
     * 
     * @param value
     *            値
     * 
     * @return 時制パラメータ
     */
    public static TemporalParameter date(Date value) {
        return new TemporalParameter(value, TemporalType.DATE);
    }

    /**
     * {@link TemporalType#DATE}用のパラメータを作成します。
     * 
     * @param value
     *            値
     * @return 時制パラメータ
     */
    public static TemporalParameter date(Calendar value) {
        return new TemporalParameter(value, TemporalType.DATE);
    }

    /**
     * {@link TemporalType#TIME}用のパラメータを作成します。
     * 
     * @param value
     *            値
     * @return 時制パラメータ
     */
    public static TemporalParameter time(Date value) {
        return new TemporalParameter(value, TemporalType.TIME);
    }

    /**
     * {@link TemporalType#TIME}用のパラメータを作成します。
     * 
     * @param value
     *            値
     * @return 時制パラメータ
     */
    public static TemporalParameter time(Calendar value) {
        return new TemporalParameter(value, TemporalType.TIME);
    }

    /**
     * {@link TemporalType#TIMESTAMP}用のパラメータを作成します。
     * 
     * @param value
     *            値
     * @return 時制パラメータ
     */
    public static TemporalParameter timestamp(Date value) {
        return new TemporalParameter(value, TemporalType.TIMESTAMP);
    }

    /**
     * {@link TemporalType#TIMESTAMP}用のパラメータを作成します。
     * 
     * @param value
     *            値
     * @return 時制パラメータ
     */
    public static TemporalParameter timestamp(Calendar value) {
        return new TemporalParameter(value, TemporalType.TIMESTAMP);
    }
}