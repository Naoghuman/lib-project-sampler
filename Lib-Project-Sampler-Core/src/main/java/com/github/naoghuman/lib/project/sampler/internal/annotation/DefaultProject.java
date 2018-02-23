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
package com.github.naoghuman.lib.project.sampler.internal.annotation;

import com.github.naoghuman.lib.project.sampler.core.annotation.Project;
import com.github.naoghuman.lib.project.sampler.internal.converter.ProjectNumberConverter;
import com.github.naoghuman.lib.validation.core.validator.PreConditionValidator;
import java.util.Optional;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class DefaultProject implements Comparable<DefaultProject> {
    
    private static final int    DEFAULT_PROJECT_NR = -1;
    private static final String UNDEFINED          = "[undefined]"; // NOI18N
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @param  project
     * @return 
     */
    public static final DefaultProject create(final Project project) {
        return new DefaultProject(
                project.name(), project.description(), project.projectNr(),
                project.projectURL(), project.version(), project.visible());
    }
    
    private final boolean visible;
    private final int     projectNr;
    
    private final String name;
    
    private final Optional<String> description;
    private final Optional<String> projectURL;
    private final Optional<String> version;
    
    private DefaultProject(
            final String name, final String description, final int projectNr,
            final String projectURL, final String version, final boolean visible
    ) {
        PreConditionValidator.getDefault().requireNonNullAndNotEmpty(name);
        
        this.name        = name;
        this.description = Optional.ofNullable((description == null) ? description : ((description.isEmpty()) ? null : description));
        this.projectNr   = (projectNr > DEFAULT_PROJECT_NR)          ? projectNr   : DEFAULT_PROJECT_NR;
        this.projectURL  = Optional.ofNullable((projectURL  == null) ? projectURL  : ((projectURL.isEmpty())  ? null : projectURL));
        this.version     = Optional.ofNullable((version     == null) ? version     : ((version.isEmpty())     ? null : version));
        this.visible     = visible;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasDescription() {
        return this.getDescription().isPresent()
                && !this.getDescription().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public Optional<String> getDescription() {
        return description;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public int getProjectNr() {
        return projectNr;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasProjectURL() {
        return this.getProjectURL().isPresent()
                && !this.getProjectURL().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public Optional<String> getProjectURL() {
        return projectURL;
    }

    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final String getName() {
        return name;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasVersion() {
        return this.getVersion().isPresent()
                && !this.getVersion().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public Optional<String> getVersion() {
        return version;
    }

    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final boolean isVisible() {
        return visible;
    }
    
    @Override
    public int compareTo(final DefaultProject other) {
        int compareTo = (ProjectNumberConverter.convert(this.getProjectNr()) + this.getName())
                .compareTo(ProjectNumberConverter.convert(other.getProjectNr()) + other.getName());
        if (compareTo != 0) {
            return compareTo;
        }
        
        if (this.getVersion().isPresent() && other.getVersion().isPresent()) {
            compareTo = this.getVersion().get().compareTo(other.getVersion().get());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        
        if (this.getVersion().isPresent() && !other.getVersion().isPresent()) {
            return +1;
        }
        
        if (!this.getVersion().isPresent() && other.getVersion().isPresent()) {
            return -1;
        }
        
        return compareTo;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getName()      .hashCode();
        result = prime * result + this.getVersion()   .hashCode();
        
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // version
        final DefaultProject other = (DefaultProject) obj;
        if (!this.getName().equals(other.getName())) {
            return false;
        }
        
        if (this.getVersion().isPresent() && !other.getVersion().isPresent()) {
            return false;
        }
        
        if (!this.getVersion().isPresent() && other.getVersion().isPresent()) {
            return false;
        }
        
        if (
                this.getVersion().isPresent()
                && other.getVersion().isPresent()
                && !this.getVersion().get().equals(other.getVersion().get())
        ) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DefaultProject ["); // NOI18N
        
        sb.append(", name=")       .append(this.getName());      // NOI18N
        sb.append(", description=").append(this.getDescription().isPresent() ? this.getDescription().get() : UNDEFINED); // NOI18N
        sb.append(", projectNr=")  .append(this.getProjectNr()); // NOI18N
        sb.append(", projectURL=") .append(this.getProjectURL().isPresent()  ? this.getProjectURL().get()  : UNDEFINED); // NOI18N
        sb.append(", version=")    .append(this.getVersion().isPresent() ? this.getVersion().get() : UNDEFINED); // NOI18N
        sb.append(", visible=")    .append(this.isVisible()); // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
}
