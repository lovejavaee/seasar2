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
package org.seasar.extension.jdbc.types;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

/**
 * @author taedium
 * 
 */
public class DateTimestampTypeTest extends TestCase {

    private DateTimestampType dtType = new DateTimestampType();

    /**
     * 
     * @throws Exception
     */
    public void testToTimestamp_fromString() throws Exception {
        Timestamp timestamp = dtType.toTimestamp("01/02/03");
        assertNotNull(timestamp);
        assertEquals("2001/02/03 00:00:00", new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss").format(timestamp));

        timestamp = dtType.toTimestamp("2001/02/03 12:34:56");
        assertNotNull(timestamp);
        assertEquals("2001/02/03 12:34:56", new SimpleDateFormat(
                "yyyy/MM/dd HH:mm:ss").format(timestamp));
    }

    /**
     * 
     * @throws Exception
     */
    public void testToText() throws Exception {
        Timestamp timestamp = Timestamp
                .valueOf("2007-11-29 13:14:15.123456789");
        Date value = new Date(timestamp.getTime());
        assertEquals("'2007-11-29 13:14:15.123000000'", dtType.toText(value));
    }
}
