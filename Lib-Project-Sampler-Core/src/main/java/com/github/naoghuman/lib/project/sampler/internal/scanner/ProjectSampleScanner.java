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
package com.github.naoghuman.lib.project.sampler.internal.scanner;

import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.project.sampler.core.annotation.Project;
import com.github.naoghuman.lib.project.sampler.core.annotation.Sample;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class ProjectSampleScanner {
    
    private static final Optional<ProjectSampleScanner> INSTANCE = Optional.of(new ProjectSampleScanner());

    /**
     * Returns a singleton instance from the class {@code ProjectScanner}.
     * 
     * @return a singleton instance from the class {@code ProjectScanner}.
     */
    public static final ProjectSampleScanner getDefault() {
        return INSTANCE.get();
    }

    private ProjectSampleScanner() {
        
    }

    public void scan(final String[] whiteAndBlackList) {
        LoggerFacade.getDefault().debug(this.getClass(), "#scan(String[])"); // NOI18N
        
        LoggerFacade.getDefault().debug(this.getClass(), "Found following projects:"); // NOI18N
        final List<Class<?>> projects = this.scan(Project.class, whiteAndBlackList);
        projects.stream()
                .forEach(project -> {
                    LoggerFacade.getDefault().debug(this.getClass(), " -> " + project.getName()); // NOI18N
                });
        
        LoggerFacade.getDefault().debug(this.getClass(), "Found following samples:"); // NOI18N
        final List<Class<?>> samples = this.scan(Sample.class, whiteAndBlackList);
        samples.stream()
                .forEach(sample -> {
                    LoggerFacade.getDefault().debug(this.getClass(), " -> " + sample.getName()); // NOI18N
                });
    }
    
    private List<Class<?>> scan(final Class annotation, final String[] whiteAndBlackList) {
        LoggerFacade.getDefault().debug(this.getClass(), String.format(
                "#scan(%s, String[]): List<Class<?>>", annotation.getSimpleName())); // NOI18N
        
        final List<Class<?>> classes = FXCollections.observableArrayList();
        
        try {
            final Constructor          constructor          = FastClasspathScanner.class.getConstructor(String[].class);
            final FastClasspathScanner fastClasspathScanner = (FastClasspathScanner) constructor.newInstance(new Object[] {whiteAndBlackList});
            fastClasspathScanner
//                    .verbose()
                    .matchClassesWithAnnotation(annotation, classes::add)
                    .scan();
        } catch (
                IllegalAccessException
                | IllegalArgumentException
                | InstantiationException
                | NoSuchMethodException
                | SecurityException
                | InvocationTargetException ex
        ) {
            LoggerFacade.getDefault().error(this.getClass(), String.format(
                    "Can't scan for annotation [%s]", annotation.getSimpleName()), ex); // NOI18N
        }

        return classes;
    }
}
