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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Sample {
    
    public String     cssURL()        default "[undefined]"; // NOI18N
    public String     description()   default "[undefined]"; // NOI18N
    public String     javaDocURL()    default "[undefined]"; // NOI18N
    public String     name();
    public String     overviewURL()   default "[undefined]"; // NOI18N
    public String     project();
    public int        sampleNr()      default -1;
    public SampleType sampleType()    default SampleType.NORMAL;
    public String     sourceCodeURL() default "[undefined]"; // NOI18N
    public boolean    visible()       default true;
    
}
