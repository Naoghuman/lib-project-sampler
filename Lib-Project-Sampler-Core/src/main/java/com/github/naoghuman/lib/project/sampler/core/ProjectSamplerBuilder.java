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

import com.airhacks.afterburner.injection.Injector;
import com.github.naoghuman.lib.database.core.DatabaseFacade;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.preferences.core.PreferencesFacade;
import com.github.naoghuman.lib.project.sampler.internal.application.ApplicationPresenter;
import com.github.naoghuman.lib.project.sampler.internal.application.ApplicationView;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.DURATION__125;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__BORDER_SIGN;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__DATABASE;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__MESSAGE_START;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__MESSAGE_STOP;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__RESOURCE_BUNDLE;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__TITLE;
import static com.github.naoghuman.lib.project.sampler.internal.configuration.ApplicationConfiguration.KEY__APPLICATION__VERSION;
import com.github.naoghuman.lib.project.sampler.internal.scanner.ProjectSampleScanner;
import com.github.naoghuman.lib.properties.core.PropertiesFacade;
import com.github.naoghuman.lib.validation.core.validator.PreConditionValidator;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public final class ProjectSamplerBuilder {

    public static final BlackAndWhiteListStep init() {
        return new ProjectSamplerBuilderInitImpl();
    }
    
    public static final StageStep start() {
        return new ProjectSamplerBuilderStartImpl();
    }
    
    public interface BlackAndWhiteListStep {
        
        public BlackAndWhiteListStep addToBlackList(String exclude);
        
        public WhiteListStep addToWhiteList(String include);
        
    }
    
    public interface StageStep {
        
        public StartHandleStep stage(final Stage primaryStage);
        
    }
    
    public interface StartHandleStep {
        
        public void handle();
        
    }
    
    public interface WhiteListStep {

        public WhiteListStep addToWhiteList(String include);
        
        public void handle();
        
    }
    
    private static String getProperty(String propertyKey) {
        return PropertiesFacade.getDefault().getProperty(KEY__APPLICATION__RESOURCE_BUNDLE, propertyKey);
    }
    
    private static final class ProjectSamplerBuilderInitImpl implements BlackAndWhiteListStep, WhiteListStep {
        
        private static final List<String> BLACKLIST = FXCollections.observableArrayList();
        private static final List<String> WHITELIST = FXCollections.observableArrayList();

        @Override
        public BlackAndWhiteListStep addToBlackList(final String exclude) {
            PreConditionValidator.getDefault().requireNonNullAndNotEmpty(exclude);
            
            BLACKLIST.add(exclude);
            
            return this;
        }

        @Override
        public WhiteListStep addToWhiteList(final String include) {
            PreConditionValidator.getDefault().requireNonNullAndNotEmpty(include);
            
            WHITELIST.add(include);
            
            return this;
        }

        @Override
        public void handle() {
            LoggerFacade.getDefault().debug(this.getClass(), "#handle()"); // NOI18N
        
            PropertiesFacade.getDefault().register(KEY__APPLICATION__RESOURCE_BUNDLE);

            final char   borderSign = getProperty(KEY__APPLICATION__BORDER_SIGN).charAt(0);
            final String message    = getProperty(KEY__APPLICATION__MESSAGE_START);
            final String title      = getProperty(KEY__APPLICATION__TITLE) + getProperty(KEY__APPLICATION__VERSION);
            LoggerFacade.getDefault().message(borderSign, 80, String.format(message, title));

            final Boolean dropPreferencesFileAtStart = Boolean.FALSE;
            PreferencesFacade.getDefault().init(dropPreferencesFileAtStart);

            DatabaseFacade.getDefault().register(getProperty(KEY__APPLICATION__DATABASE));

            final List<String> blackAndWhiteList = FXCollections.observableArrayList();
            blackAndWhiteList.addAll(BLACKLIST);
            blackAndWhiteList.addAll(WHITELIST);
            
            ProjectSampleScanner.getDefault().scan(blackAndWhiteList.toArray(new String[] {}));
            
            BLACKLIST.clear();
            WHITELIST.clear();
        }
        
    }
    
    private static final class ProjectSamplerBuilderStartImpl implements StageStep, StartHandleStep {
        
        private Stage primaryStage;

        @Override
        public StartHandleStep stage(Stage primaryStage) {
            PreConditionValidator.getDefault().requireNonNull(primaryStage);
            
            this.primaryStage = primaryStage;
            
            return this;
        }

        @Override
        public void handle() {
            LoggerFacade.getDefault().debug(this.getClass(), "#handle()"); // NOI18N
            
            final ApplicationView      applicationView      = new ApplicationView();
            final ApplicationPresenter applicationPresenter = applicationView.getRealPresenter();

            final Scene scene = new Scene(applicationView.getView(), 1280, 720);
            primaryStage.setTitle(getProperty(KEY__APPLICATION__TITLE) + getProperty(KEY__APPLICATION__VERSION));
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest((WindowEvent we) -> {
               we.consume();

               this.onCloseRequest();
            });

            primaryStage.show();
            applicationPresenter.initializeAfterWindowIsShowing();
        }
    
        private void onCloseRequest() {
            LoggerFacade.getDefault().debug(this.getClass(), "#onCloseRequest()"); // NOI18N
            
            // afterburner.fx
            Injector.forgetAll();

            // Database
            DatabaseFacade.getDefault().shutdown();

            // Message
            final char   borderSign = getProperty(KEY__APPLICATION__BORDER_SIGN).charAt(0);
            final String message    = getProperty(KEY__APPLICATION__MESSAGE_STOP);
            final String title      = getProperty(KEY__APPLICATION__TITLE) + getProperty(KEY__APPLICATION__VERSION);
            LoggerFacade.getDefault().message(borderSign, 80, String.format(message, title));

            // Timer
            final PauseTransition pt = new PauseTransition(DURATION__125);
            pt.setOnFinished((ActionEvent event) -> {
                Platform.exit();
            });
            pt.playFromStart();
        }
        
    }
    
}
