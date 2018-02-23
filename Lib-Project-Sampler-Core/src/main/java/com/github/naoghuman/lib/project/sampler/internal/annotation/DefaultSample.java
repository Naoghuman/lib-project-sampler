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

import com.github.naoghuman.lib.project.sampler.core.annotation.Sample;
import com.github.naoghuman.lib.project.sampler.core.annotation.SampleType;
import com.github.naoghuman.lib.project.sampler.internal.converter.SampleNumberConverter;
import com.github.naoghuman.lib.validation.core.validator.PreConditionValidator;
import java.util.Optional;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class DefaultSample implements Comparable<DefaultSample> {
    
    private static final int    DEFAULT_SAMPLE_NR = -1;
    private static final String UNDEFINED         = "[undefined]"; // NOI18N
    
    private final boolean visible;
    private final int     sampleNr;
    
    private final SampleType sampleType;
    private final String     name;
    private final String     project;
    
    private final Optional<String> cssURL;
    private final Optional<String> description;
    private final Optional<String> javaDocURL;
    private final Optional<String> overviewURL;
    private final Optional<String> sourceCodeURL;

    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @param  sample
     * @return 
     */
    public static final DefaultSample create(final Sample sample) {
        return new DefaultSample(
                sample.name(), sample.project(), sample.cssURL(),
                sample.description(), sample.javaDocURL(), sample.overviewURL(),
                sample.sampleNr(), sample.sampleType(), sample.sourceCodeURL(),
                sample.visible());
    }
    
    private DefaultSample(
            final String name, final String project, final String cssURL,
            final String description, final String javaDocURL, final String overviewURL,
            final int sampleNr, final SampleType sampleType, final String sourceCodeURL, 
            final boolean visible
    ) {
        PreConditionValidator.getDefault().requireNonNullAndNotEmpty(name);
        PreConditionValidator.getDefault().requireNonNullAndNotEmpty(project);
        PreConditionValidator.getDefault().requireNonNull(sampleType);
        
        this.name            = name;
        this.project         = project;
        this.overviewURL     = Optional.ofNullable((overviewURL   == null) ? overviewURL   : ((overviewURL.isEmpty())   ? null : overviewURL));
        this.sourceCodeURL   = Optional.ofNullable((sourceCodeURL == null) ? sourceCodeURL : ((sourceCodeURL.isEmpty()) ? null : sourceCodeURL));
        this.javaDocURL      = Optional.ofNullable((javaDocURL    == null) ? javaDocURL    : ((javaDocURL.isEmpty())    ? null : javaDocURL));
        this.cssURL          = Optional.ofNullable((cssURL        == null) ? cssURL        : ((cssURL.isEmpty())        ? null : cssURL));
        this.sampleNr        = (sampleNr > DEFAULT_SAMPLE_NR)              ? sampleNr      : DEFAULT_SAMPLE_NR;
        this.sampleType      = sampleType;
        this.description     = Optional.ofNullable((description   == null) ? description   : ((description.isEmpty())   ? null : description));
        this.visible         = visible;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasCssURL() {
        return this.getCssURL().isPresent()
                && !this.getCssURL().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final Optional<String> getCssURL() {
        return cssURL;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasJavaDocURL() {
        return this.getJavaDocURL().isPresent()
                && !this.getJavaDocURL().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final Optional<String> getJavaDocURL() {
        return javaDocURL;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasOverviewURL() {
        return this.getOverviewURL().isPresent()
                && !this.getOverviewURL().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final Optional<String> getOverviewURL() {
        return overviewURL;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public boolean hasSourceCodeURL() {
        return this.getSourceCodeURL().isPresent()
                && !this.getSourceCodeURL().get().equals(UNDEFINED);
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public int getSampleNr() {
        return sampleNr;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final SampleType getSampleType() {
        return sampleType;
    }
    
    /**
     * 
     * @author Naoghuman
     * @since  0.1.0
     * @return 
     */
    public final Optional<String> getSourceCodeURL() {
        return sourceCodeURL;
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
    public final Optional<String> getDescription() {
        return description;
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
    public final String getProject() {
        return project;
    }
    
    @Override
    public int compareTo(final DefaultSample other) {
        int compareTo = (SampleNumberConverter.convert(this.getSampleNr()) + this.getName())
                .compareTo(SampleNumberConverter.convert(other.getSampleNr()) + other.getName());
        if (compareTo != 0) {
            return compareTo;
        }
        
        compareTo = this.getProject().compareTo(other.getProject());
        if (compareTo != 0) {
            return compareTo;
        }
        
        compareTo = this.getSampleType().compareTo(other.getSampleType());
        if (compareTo != 0) {
            return compareTo;
        }
        
        return compareTo;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getName().hashCode();
        result = prime * result + this.getProject().hashCode();
        result = prime * result + this.getSampleType().hashCode();
        
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
        
        final DefaultSample other = (DefaultSample) obj;
        if (!this.getName().equals(other.getName())) {
            return false;
        }
        
        if (!this.getProject().equals(other.getProject())) {
            return false;
        }
        
        return this.getSampleType().equals(other.getSampleType());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ConcreteSample ["); // NOI18N
        
        sb.append(", name=")         .append(this.getName());    // NOI18N
        sb.append(", project=")      .append(this.getProject()); // NOI18N
        sb.append(", overviewURL=")  .append(this.getOverviewURL()  .isPresent() ? this.getOverviewURL()  .get() : UNDEFINED); // NOI18N
        sb.append(", sourceCodeURL=").append(this.getSourceCodeURL().isPresent() ? this.getSourceCodeURL().get() : UNDEFINED); // NOI18N
        sb.append(", javaDocURL=")   .append(this.getJavaDocURL()   .isPresent() ? this.getJavaDocURL()   .get() : UNDEFINED); // NOI18N
        sb.append(", cssURL=")       .append(this.getCssURL()       .isPresent() ? this.getCssURL()       .get() : UNDEFINED); // NOI18N
        sb.append(", sampleNr=")     .append(this.getSampleNr());             // NOI18N
        sb.append(", sampleType=")   .append(this.getSampleType()   .name()); // NOI18N
        sb.append(", description=")  .append(this.getDescription()  .isPresent() ? this.getDescription()  .get() : UNDEFINED); // NOI18N
        sb.append(", visible=")      .append(this.isVisible());               // NOI18N
        
        sb.append("]"); // NOI18N
        
        return sb.toString();
    }
    
}
