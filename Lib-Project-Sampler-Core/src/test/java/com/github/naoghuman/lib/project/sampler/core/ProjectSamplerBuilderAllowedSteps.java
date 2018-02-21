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
package com.github.naoghuman.lib.project.sampler.core;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class ProjectSamplerBuilderAllowedSteps {
    
    void allowedStepsForInTheStartSection() {
        
        ProjectSamplerBuilder
                .start()     // Starts the start functionality section which should 
                             // be used in the Application#start(Stage) section.
                
                .stage(null)
                .handle();
        
    }
    
    void allowedStepsForInTheInitSection() {
        
        ProjectSamplerBuilder
                .init()               // Starts the initialize functionality section which should 
                                      // be used in the Application#init() section. Moves to 
                                      // addToBlackList(null) or addToWhiteList(null)
                
                .addToBlackList(null)
                .addToBlackList(null) // Repeat addToBlackList(null) or move to addToWhiteList(null)
                
                .addToWhiteList(null)
                .addToWhiteList(null) // Repeat addToWhiteList(null) or move to handle()
                
                .handle();            // Initialize the scanner with the previous configured values.
        
    }
    
}
