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

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.project.sampler.internal.configuration.ImageConfiguration;
import com.github.naoghuman.lib.properties.core.PropertiesFacade;
import java.net.URI;
import java.util.Optional;

import javafx.scene.image.Image;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class ImageLoader implements ImageConfiguration {
    
    private static final String IMAGE__COMING_SOON = PropertiesFacade.getDefault().getProperty(KEY__IMAGE__RESOURCE_BUNDLE, KEY__IMAGE__COMING_SOON);
    
    private static final Optional<ImageLoader> INSTANCE = Optional.of(new ImageLoader());

    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public static final ImageLoader getDefault() {
        return INSTANCE.get();
    }

    private ImageLoader() {

    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public Optional<Image> loadComingSoonImage() {
        Optional<Image> placeHolderImage;
        try {
            final URI uri = ImageLoader.class.getResource(IMAGE__COMING_SOON).toURI();
            placeHolderImage = Optional.ofNullable(new Image(uri.toString(), 300.0d, 145.0d, true, true, true));
//            placeHolderImage = Optional.ofNullable(new Image(uri.toString(), 400.0d, 193.0d, true, true, true));
        } catch (Exception ex) {
            LoggerFacade.getDefault().error(this.getClass(), String.format("Can't load the image: [%s] return Optional.empty()", IMAGE__COMING_SOON), ex); // NOI18N
            
            placeHolderImage = Optional.empty();
        }
        
        return placeHolderImage;
    }
    
}
