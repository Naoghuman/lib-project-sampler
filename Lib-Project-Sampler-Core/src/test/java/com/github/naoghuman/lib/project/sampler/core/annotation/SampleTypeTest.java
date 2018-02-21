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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the enum {@link com.github.naoghuman.lib.project.sampler.core.annotation.SampleType}.
 *
 * @author Naoghuman
 * @since  0.1.0
 * @see    com.github.naoghuman.lib.project.sampler.core.annotation.SampleType
 */
public class SampleTypeTest {
    
    public SampleTypeTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testValues() {
        SampleType[] result = SampleType.values();
        assertTrue(result.length == 2);
    }

    @Test
    public void testValueOf() {
        SampleType expResult = SampleType.NORMAL;
        SampleType result = SampleType.valueOf("NORMAL");
        assertEquals(expResult, result);
    }

    @Test
    public void testIsNormalTRUE() {
        boolean result = SampleType.isNormal(SampleType.NORMAL);
        assertTrue(result);
    }

    @Test
    public void testIsNormalFALSE() {
        boolean result = SampleType.isNormal(SampleType.OVERVIEW);
        assertFalse(result);
    }
    
}
