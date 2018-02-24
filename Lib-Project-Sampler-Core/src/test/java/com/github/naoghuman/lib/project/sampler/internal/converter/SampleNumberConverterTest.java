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
package com.github.naoghuman.lib.project.sampler.internal.converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the class {@link com.github.naoghuman.lib.project.sampler.internal.converter.SampleNumberConverter}
 *
 * @author Naoghuman
 * @since  0.1.0
 * @see    com.github.naoghuman.lib.project.sampler.internal.converter.SampleNumberConverter
 */
public class SampleNumberConverterTest {
    
    public SampleNumberConverterTest() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConvert_3_1() {
        int leadingZeros      = 3;
        int projectOrSampleNr = 1;
        String result = SampleNumberConverter.convert(leadingZeros, projectOrSampleNr);
        assertEquals("001", result);
    }

    @Test
    public void testConvert_3_0() {
        int leadingZeros      = 3;
        int projectOrSampleNr = 0;
        String result = SampleNumberConverter.convert(leadingZeros, projectOrSampleNr);
        assertEquals("000", result);
    }

    @Test
    public void testConvert_3_minus1() {
        int leadingZeros      = 3;
        int projectOrSampleNr = -1;
        String result = SampleNumberConverter.convert(leadingZeros, projectOrSampleNr);
        assertEquals("000", result);
    }

    @Test
    public void testConvert_3_12345() {
        int leadingZeros      = 3;
        int projectOrSampleNr = 12345;
        String result = SampleNumberConverter.convert(leadingZeros, projectOrSampleNr);
        assertEquals("12345", result);
    }

    @Test
    public void testConvert_1() {
        int projectOrSampleNr = 1;
        String result = SampleNumberConverter.convert(projectOrSampleNr);
        assertEquals("01", result);
    }

    @Test
    public void testConvert_minus1() {
        int projectOrSampleNr = -1;
        String result = SampleNumberConverter.convert(projectOrSampleNr);
        assertEquals("00", result);
    }

    @Test
    public void testConvert_1234() {
        int projectOrSampleNr = 1234;
        String result = SampleNumberConverter.convert(projectOrSampleNr);
        assertEquals("1234", result);
    }
    
}
