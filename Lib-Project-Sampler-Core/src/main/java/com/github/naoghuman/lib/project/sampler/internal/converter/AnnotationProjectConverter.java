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
import com.github.naoghuman.lib.project.sampler.core.annotation.Project;
import com.github.naoghuman.lib.project.sampler.internal.annotation.DefaultProject;
import java.util.List;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class AnnotationProjectConverter {
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @param  projects
     * @return 
     */
    public static final List<DefaultProject> convert(final List<Project> projects) {
        LoggerFacade.getDefault().debug(AnnotationSampleConverter.class, "#convert(final List<Project>): List<DefaultProject>"); // NOI18N
        
        final List<DefaultProject> defaultProjects = FXCollections.observableArrayList();
        projects.stream()
                .forEach(project -> {
                    defaultProjects.add(DefaultProject.create(project));
                });
        
        return defaultProjects;
    }
    
}
