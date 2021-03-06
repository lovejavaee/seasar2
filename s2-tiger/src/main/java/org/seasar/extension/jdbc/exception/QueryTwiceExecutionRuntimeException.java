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
package org.seasar.extension.jdbc.exception;

import org.seasar.extension.jdbc.Query;
import org.seasar.framework.exception.SRuntimeException;

/**
 * {@link Query}の最後のメソッドが繰り返し呼び出された場合にスローされる例外です。
 * 
 * @author koichik
 */
public class QueryTwiceExecutionRuntimeException extends SRuntimeException {

    private static final long serialVersionUID = 1L;

    private final Class<?> queryClass;

    private final String methodName;

    /**
     * インスタンスを構築します。
     * 
     * @param queryClass
     *            クエリのクラス
     * @param methodName
     *            クエリを呼び出すメソッド名
     */
    public QueryTwiceExecutionRuntimeException(final Class<?> queryClass,
            final String methodName) {
        super("ESSR0765", new Object[] { queryClass, methodName });
        this.queryClass = queryClass;
        this.methodName = methodName;
    }

    /**
     * クエリのクラスを返します。
     * 
     * @return クエリのクラス
     */
    public Class<?> getQueryClass() {
        return queryClass;
    }

    /**
     * 呼び出されたメソッド名を返します。
     * 
     * @return 呼び出されたメソッド名
     */
    public String getMethodName() {
        return methodName;
    }

}
