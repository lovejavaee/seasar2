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
package org.seasar.extension.jdbc.it.sql;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.it.entity.Employee;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author taedium
 * 
 */
public class SqlSelectTest extends S2TestCase {

    private static String sql = "select * from Employee order by employee_no";

    private JdbcManager jdbcManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("jdbc.dicon");
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_offsetOnly() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .offset(3).getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_limitOnly() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_offsetZero_limitZero() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .offset(0).limit(0).getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_offset_limitZero() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .offset(3).limit(0).getResultList();
        assertEquals(11, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(14, list.get(10).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_offsetZero_limit() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .offset(0).limit(3).getResultList();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).employeeId);
        assertEquals(3, list.get(2).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_offset_limit() throws Exception {
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .offset(3).limit(5).getResultList();
        assertEquals(5, list.size());
        assertEquals(4, list.get(0).employeeId);
        assertEquals(8, list.get(4).employeeId);
    }

    /**
     * 
     * @throws Exception
     */
    public void testPaging_no_parameter() throws Exception {
        String sql = "select * from Employee";
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql)
                .getResultList();
        assertEquals(14, list.size());
    }

    /**
     * 
     * @throws Exception
     */
    public void testParameter() throws Exception {
        String sql = "select * from Employee where department_Id = ? and salary = ?";
        List<Employee> list = jdbcManager.selectBySql(Employee.class, sql, 2,
                3000).getResultList();
        assertEquals(2, list.size());
    }
}
