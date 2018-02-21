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
 * Test class for the annotation {@link com.github.naoghuman.lib.project.sampler.core.annotation.Sample}.
 *
 * @author Naoghuman
 * @since  0.1.0
 * @see    com.github.naoghuman.lib.project.sampler.core.annotation.Sample
 */
public class SampleTest {
    
    public SampleTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testDefaultSampleImpl() {
        Class<DefaultSampleImpl> obj = DefaultSampleImpl.class;
        assertTrue(obj.isAnnotationPresent(Sample.class));
        
        Annotation annotation = obj.getAnnotation(Sample.class);
        assertNotNull(annotation);
        
        Sample sample = (Sample) annotation;
        assertEquals("hello sample", sample.name());
        assertEquals("Hello World",  sample.project());
        
        assertEquals("[undefined]", sample.cssURL());
        assertEquals("[undefined]", sample.description());
        assertEquals("[undefined]", sample.javaDocURL());
        assertEquals("[undefined]", sample.overviewURL());
        assertEquals("[undefined]", sample.sourceCodeURL());
        
        assertEquals(-1,                sample.sampleNr());
        assertEquals(SampleType.NORMAL, sample.sampleType());
        assertTrue(sample.visible());
    }

    @Test
    public void testExtendedSampleImpl() {
        Class<ExtendedSampleImpl> obj = ExtendedSampleImpl.class;
        assertTrue(obj.isAnnotationPresent(Sample.class));
        
        Annotation annotation = obj.getAnnotation(Sample.class);
        assertNotNull(annotation);
        
        Sample sample = (Sample) annotation;
        assertEquals("hello sample 2", sample.name());
        assertEquals("Hello World 2",  sample.project());
        
        assertEquals("cssURL",        sample.cssURL());
        assertEquals("description",   sample.description());
        assertEquals("javaDocURL",    sample.javaDocURL());
        assertEquals("overviewURL",   sample.overviewURL());
        assertEquals("sourceCodeURL", sample.sourceCodeURL());
        
        assertEquals(123,                 sample.sampleNr());
        assertEquals(SampleType.OVERVIEW, sample.sampleType());
        assertFalse(sample.visible());
    }

    @Sample(
            name    = "hello sample",
            project = "Hello World"
    )
    public class DefaultSampleImpl {
        
    }

    @Sample(
            name          = "hello sample 2",
            project       = "Hello World 2",
            
            cssURL        = "cssURL",
            description   = "description",
            javaDocURL    = "javaDocURL",
            overviewURL   = "overviewURL",
            sourceCodeURL = "sourceCodeURL",
            
            sampleNr      = 123,
            sampleType    = SampleType.OVERVIEW,
            visible       = false
    )
    public class ExtendedSampleImpl {
        
    }
    
}
