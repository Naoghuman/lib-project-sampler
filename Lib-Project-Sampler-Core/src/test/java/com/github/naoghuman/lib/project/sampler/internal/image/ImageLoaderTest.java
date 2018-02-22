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
package com.github.naoghuman.lib.project.sampler.internal.image;

import static com.github.naoghuman.lib.project.sampler.internal.configuration.ImageConfiguration.KEY__IMAGE__RESOURCE_BUNDLE;
import com.github.naoghuman.lib.properties.core.PropertiesFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the class {@link com.github.naoghuman.lib.project.sampler.internal.image.ImageLoader}
 *
 * @author Naoghuman
 * @since  0.1.0
 * @see    com.github.naoghuman.lib.project.sampler.internal.image.ImageLoader
 */
public class ImageLoaderTest {
    
    public ImageLoaderTest() {
    }
    
    @Before
    public void setUp() {
        PropertiesFacade.getDefault().register(KEY__IMAGE__RESOURCE_BUNDLE);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetDefault() {
        assertNotNull(ImageLoader.getDefault());
    }

    // Needs JavaFX thread
//    @Test
//    public void testLoadComingSoonImage() {
//        Optional<Image> image = ImageLoader.getDefault().loadComingSoonImage();
//        assertTrue(image.isPresent());
//    }
    
}
