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
package org.seasar.extension.jdbc.gen.internal.model;

import java.util.Date;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.jdbc.EntityMeta;
import org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl;
import org.seasar.extension.jdbc.gen.model.ServiceModel;
import org.seasar.extension.jdbc.meta.ColumnMetaFactoryImpl;
import org.seasar.extension.jdbc.meta.EntityMetaFactoryImpl;
import org.seasar.extension.jdbc.meta.PropertyMetaFactoryImpl;
import org.seasar.extension.jdbc.meta.TableMetaFactoryImpl;
import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.framework.convention.PersistenceConvention;
import org.seasar.framework.convention.impl.PersistenceConventionImpl;

import static org.junit.Assert.*;

/**
 * @author taedium
 * 
 */
public class ServiceModelFactoryImplTest {

    private EntityMetaFactoryImpl entityMetaFactory;

    private ServiceModelFactoryImpl serviceModelFactory;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        PersistenceConvention pc = new PersistenceConventionImpl();
        ColumnMetaFactoryImpl cmf = new ColumnMetaFactoryImpl();
        cmf.setPersistenceConvention(pc);
        PropertyMetaFactoryImpl propertyMetaFactory = new PropertyMetaFactoryImpl();
        propertyMetaFactory.setPersistenceConvention(pc);
        propertyMetaFactory.setColumnMetaFactory(cmf);
        TableMetaFactoryImpl tmf = new TableMetaFactoryImpl();
        tmf.setPersistenceConvention(pc);
        entityMetaFactory = new EntityMetaFactoryImpl();
        entityMetaFactory.setPersistenceConvention(pc);
        entityMetaFactory.setPropertyMetaFactory(propertyMetaFactory);
        entityMetaFactory.setTableMetaFactory(tmf);
        serviceModelFactory = new ServiceModelFactoryImpl("aaa.bbb", "Service");
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSingleId() throws Exception {
        EntityMeta entityMeta = entityMetaFactory.getEntityMeta(Aaa.class);
        ServiceModel serviceModel = serviceModelFactory
                .getServiceModel(entityMeta);
        assertNotNull(serviceModel);
        assertEquals("aaa.bbb", serviceModel.getPackageName());
        assertEquals("ServiceModelFactoryImplTest$AaaService", serviceModel
                .getShortClassName());
        assertEquals("Aaa", serviceModel.getShortEntityClassName());
        assertEquals(1, serviceModel.getIdPropertyMetaList().size());
        assertEquals(2, serviceModel.getImportNameSet().size());
        Iterator<String> iterator = serviceModel.getImportNameSet().iterator();
        assertEquals(Aaa.class.getName(), iterator.next());
        assertEquals(S2AbstractService.class.getName(), iterator.next());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCompositeId() throws Exception {
        EntityMeta entityMeta = entityMetaFactory.getEntityMeta(Bbb.class);
        ServiceModel serviceModel = serviceModelFactory
                .getServiceModel(entityMeta);
        assertNotNull(serviceModel);
        assertEquals("aaa.bbb", serviceModel.getPackageName());
        assertEquals("ServiceModelFactoryImplTest$BbbService", serviceModel
                .getShortClassName());
        assertEquals(2, serviceModel.getIdPropertyMetaList().size());
        assertEquals(3, serviceModel.getImportNameSet().size());
        Iterator<String> iterator = serviceModel.getImportNameSet().iterator();
        assertEquals(Date.class.getName(), iterator.next());
        assertEquals(Bbb.class.getName(), iterator.next());
        assertEquals(S2AbstractService.class.getName(), iterator.next());
    }

    /** */
    @Entity
    public static class Aaa {

        /** */
        @Id
        protected Integer id;

        /** */
        protected String name;
    }

    /** */
    @Entity
    public static class Bbb {

        /** */
        @Id
        @Column(nullable = false)
        protected Integer id1;

        /** */
        @Id
        @Temporal(TemporalType.DATE)
        @Column(nullable = false)
        protected Date id2;

        /** */
        protected String name;
    }
}