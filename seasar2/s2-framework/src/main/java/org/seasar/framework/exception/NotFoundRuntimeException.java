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
package org.seasar.framework.exception;

import javassist.NotFoundException;

/**
 * <code>NotFoundException</code>をラップする例外です。
 * 
 * @author koichik
 */
public class NotFoundRuntimeException extends SRuntimeException {
    private static final long serialVersionUID = 1960782343618833636L;

    /**
     * {@link NotFoundRuntimeException}を作成します。
     * 
     * @param cause
     */
    public NotFoundRuntimeException(final NotFoundException cause) {
        super("ESSR0017", new Object[] { cause }, cause);
    }
}