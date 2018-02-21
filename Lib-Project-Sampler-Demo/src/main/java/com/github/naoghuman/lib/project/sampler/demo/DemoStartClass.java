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
package com.github.naoghuman.lib.project.sampler.demo;

import com.github.naoghuman.lib.project.sampler.core.ProjectSamplerBuilder;
import com.github.naoghuman.lib.project.sampler.demo.projects.Project_1;
import com.github.naoghuman.lib.project.sampler.demo.samples.Sample_1_1;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class DemoStartClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        
        ProjectSamplerBuilder.init()
                .addToWhiteList(Project_1.class.getPackage().getName())
                .addToWhiteList(Sample_1_1.class.getPackage().getName())
                .handle();
    }
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        ProjectSamplerBuilder.start()
                .stage(primaryStage)
                .handle();
    }
    
}
