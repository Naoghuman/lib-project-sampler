/**
 * Copyright (C) 2018 - 2018 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.lib.project.sampler.core.annotation;

import java.lang.annotation.Annotation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the annotation {@link com.github.naoghuman.lib.project.sampler.core.annotation.Project}.
 *
 * @author Naoghuman
 * @since  0.1.0
 * @see    com.github.naoghuman.lib.project.sampler.core.annotation.Project
 */
public class ProjectTest {
    
    public ProjectTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDefaultProjectImpl() {
        Class<DefaultProjectImpl> obj = DefaultProjectImpl.class;
        assertTrue(obj.isAnnotationPresent(Project.class));
        
        Annotation annotation = obj.getAnnotation(Project.class);
        assertNotNull(annotation);
        
        Project project = (Project) annotation;
        assertEquals("[undefined]", project.description());
        assertEquals("ProjectBasicImpl", project.name());
        assertEquals(-1, project.projectNr());
        assertEquals("[undefined]", project.projectURL());
        assertEquals("[undefined]", project.version());
        assertTrue(project.visible());
    }

    @Test
    public void testExtendedProjectImpl() {
        Class<ExtendedProjectImpl> obj = ExtendedProjectImpl.class;
        assertTrue(obj.isAnnotationPresent(Project.class));
        
        Annotation annotation = obj.getAnnotation(Project.class);
        assertNotNull(annotation);
        
        Project project = (Project) annotation;
        assertEquals("description", project.description());
        assertEquals("ExtendedProjectImpl", project.name());
        assertEquals(123, project.projectNr());
        assertEquals("url", project.projectURL());
        assertEquals("0.1.0", project.version());
        assertFalse(project.visible());
    }

    @Project(
        name = "ProjectBasicImpl"
    )
    public class DefaultProjectImpl {

    }

    @Project(
        description = "description",
        name = "ExtendedProjectImpl",
        projectNr = 123,
        projectURL = "url",
        version = "0.1.0",
        visible = false
    )
    public class ExtendedProjectImpl {

    }
    
}
