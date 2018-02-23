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

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.project.sampler.core.annotation.Sample;
import com.github.naoghuman.lib.project.sampler.internal.annotation.DefaultSample;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class AnnotationSampleConverter {
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @param  samples
     * @return 
     */
    public static final List<DefaultSample> convert(final List<Sample> samples) {
        LoggerFacade.getDefault().debug(AnnotationSampleConverter.class, "#convert(final List<Sample>): List<DefaultSample>"); // NOI18N
        
        final List<DefaultSample> defaultSamples = FXCollections.observableArrayList();
        samples.stream()
                .forEach(sample -> {
                    defaultSamples.add(DefaultSample.create(sample));
                });
        
        return defaultSamples;
    }
    
}
