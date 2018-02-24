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
package com.github.naoghuman.lib.project.sampler.internal.application;

import com.github.naoghuman.lib.action.core.RegisterActions;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import com.github.naoghuman.lib.project.sampler.internal.annotation.DefaultProject;
import com.github.naoghuman.lib.project.sampler.internal.annotation.DefaultSample;
import com.github.naoghuman.lib.project.sampler.internal.converter.AnnotationProjectConverter;
import com.github.naoghuman.lib.project.sampler.internal.converter.AnnotationSampleConverter;
import com.github.naoghuman.lib.project.sampler.internal.converter.ProjectNumberConverter;
import com.github.naoghuman.lib.project.sampler.internal.converter.SampleNumberConverter;
import com.github.naoghuman.lib.project.sampler.internal.scanner.ProjectSampleScanner;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 *
 * @author Naoghuman
 * @since  0.1.0
 */
public class ApplicationPresenter implements Initializable, RegisterActions {
    
    private static final String EMPTY_SIGN = " "; // NOI18N
    
    @FXML private ListView<DefaultProject> lvNavigationProjects;
    @FXML private ListView<DefaultSample>  lvNavigationSamples;
    @FXML private Tab tCSS;
    @FXML private Tab tJavaDoc;
    @FXML private Tab tOverview;
    @FXML private Tab tProject;
    @FXML private Tab tSample;
    @FXML private Tab tSourceCode;
    @FXML private TabPane tpEditorPages;
    @FXML private VBox    vbSamplePage;
    @FXML private WebView wvCssPage;
    @FXML private WebView wvJavaDocPage;
    @FXML private WebView wvOverviewPage;
    @FXML private WebView wvSourceCodePage;
    @FXML private WebView wvProjectPage;
    
    private final List<DefaultProject> projects = FXCollections.observableArrayList();
    private final List<DefaultSample>  samples  = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "#initialize(URL, ResourceBundle)"); // NOI18N
        
//        assert (apView != null) : "fx:id=\"apView\" was not injected: check your FXML file 'application.fxml'."; // NOI18N
        
        // initialize
        this.initializeNavigationProjects();
        this.initializeNavigationSamples();
        this.initializeTabPaneProjectSamplerPages();
        this.initializeProjectsAndSamples();
        
        // register
        this.register();
        
        // gui
        this.onActionRefreshNavigationProjects();
        this.onActionResetEditorPages();
    }
    
    public void initializeAfterWindowIsShowing() {
        LoggerFacade.getDefault().info(this.getClass(), "#initializeAfterWindowIsShowing()"); // NOI18N
    }

    private void initializeNavigationProjects() {
        LoggerFacade.getDefault().info(this.getClass(), "#initializeNavigationProjects()"); // NOI18N
        
        final Callback callbackProjects = (Callback<ListView<DefaultProject>, ListCell<DefaultProject>>) (ListView<DefaultProject> listView) -> new ListCell<DefaultProject>() {
            private final Text TEXT = new Text();
            {
                final Font font = TEXT.getFont();
                TEXT.setFill(Color.DIMGRAY.darker());
                TEXT.setFont(Font.font(font.getFamily(), FontPosture.ITALIC, font.getSize()));
                TEXT.setStrikethrough(Boolean.TRUE);
            }
            
            @Override
            protected void updateItem(final DefaultProject project, final boolean empty) {
                super.updateItem(project, empty);
                
                this.setGraphic(null);
                this.setText(null);
                
                if (project != null && !empty) {
                    final StringBuilder text = new StringBuilder();
                    text.append(ProjectNumberConverter.convert(project.getProjectNr()));
                    text.append(EMPTY_SIGN);
                    text.append(project.getName());
                    if (project.isVisible()) {
                        this.setText(text.toString());
                    }
                    else {
                        TEXT.setText(text.toString());
                        this.setGraphic(TEXT);
                    }
                }
            }
        };
        
        lvNavigationProjects.setCellFactory(callbackProjects);
        
//        lvNavigationProjects.setOnMouseClicked(event -> {
//            if (!lvNavigationProjects.getSelectionModel().isEmpty()) {
////                this.onActionPrepareEditorForProjects();
//            }
//        });
        
        lvNavigationProjects.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends DefaultProject> observable, DefaultProject oldValue, DefaultProject project) -> {
            LoggerFacade.getDefault().debug(this.getClass(), String.format("Select in navigation the project: %s", project.getName())); // NOI18N
                
            tProject.setContent(null);
            
            if (project.isVisible()) {
                LoggerFacade.getDefault().debug(this.getClass(), "  -> show [Project]"); // NOI18N
                
//                this.onActionSetTextNormal(tProject);
//                tProject.setContent(wvProjectPage);
//                
//                this.onActionShowPageProject(concreteProject);
                this.onActionRefreshNavigationSamples(project);
            }
            else {
                LoggerFacade.getDefault().debug(this.getClass(), "  -> show [ComingSoonView]"); // NOI18N

//                this.onActionSetTextItalicAndStrikethrough(tProject);
//                tProject.setContent(ComingSoonView.getComingSoonView(concreteProject.getName(), concreteProject.getDescription()));
            }
        });
    }

    private void initializeNavigationSamples() {
        LoggerFacade.getDefault().info(this.getClass(), "#initializeNavigationSamples()"); // NOI18N
        
        final Callback callbackSamples = (Callback<ListView<DefaultSample>, ListCell<DefaultSample>>) (ListView<DefaultSample> listView) -> new ListCell<DefaultSample>() {
            private final Text TEXT = new Text();
            {
                final Font font = TEXT.getFont();
                TEXT.setFill(Color.DIMGRAY.darker());
                TEXT.setFont(Font.font(font.getFamily(), FontPosture.ITALIC, font.getSize()));
                TEXT.setStrikethrough(Boolean.TRUE);
            }
            
            @Override
            protected void updateItem(DefaultSample sample, boolean empty) {
                super.updateItem(sample, empty);
                
                this.setGraphic(null);
                this.setText(null);
                
                if (sample != null && !empty) {
                    final StringBuilder text = new StringBuilder();
                    text.append(SampleNumberConverter.convert(sample.getSampleNr()));
                    text.append(EMPTY_SIGN);
                    text.append(sample.getName());
                    if (sample.isVisible()) {
                        this.setText(text.toString());
                    }
                    else {
                        TEXT.setText(text.toString());
                        this.setGraphic(TEXT);
                    }
                }
            }
        };
        
        lvNavigationSamples.setCellFactory(callbackSamples);
        
        lvNavigationSamples.setOnMouseClicked(event -> {
            if (!lvNavigationSamples.getSelectionModel().isEmpty()) {
//                concreteSample = lvNavigationSamples.getSelectionModel().getSelectedItem();
//                this.onActionPrepareTabsForSamples(concreteSample);
//                
//                // Select new sample in navigation shows the first tab
//                final int selectedIndex = 0;
//                this.onActionShowConcreteSample(selectedIndex);
            }
        });
    }

    private void initializeProjectsAndSamples() {
        LoggerFacade.getDefault().info(this.getClass(), "#initializeProjectsAndSamples()"); // NOI18N
        
        projects.addAll(AnnotationProjectConverter.convert(ProjectSampleScanner.getDefault().getProjects()));
        Collections.sort(projects);
        
        samples .addAll(AnnotationSampleConverter .convert(ProjectSampleScanner.getDefault().getSamples()));
        Collections.sort(samples);
    }

    private void initializeTabPaneProjectSamplerPages() {
        LoggerFacade.getDefault().info(this.getClass(), "#initializeTabPaneProjectSamplerPages()"); // NOI18N
    }
    
    @Override
    public void register() {
        LoggerFacade.getDefault().debug(this.getClass(), "#register()"); // NOI18N
    }
    
    private void onActionResetEditorPages() {
        LoggerFacade.getDefault().debug(this.getClass(), "#onActionResetEditorPages()"); // NOI18N

        Platform.runLater(() -> {
            tpEditorPages.getTabs().clear();
        });
    }

    private void onActionRefreshNavigationProjects() {
        LoggerFacade.getDefault().debug(this.getClass(), "#onActionRefreshNavigationProjects()"); // NOI18N

        lvNavigationProjects.getItems().clear();
        lvNavigationProjects.getItems().addAll(projects);
    }

    private void onActionRefreshNavigationSamples(final DefaultProject project) {
        LoggerFacade.getDefault().debug(this.getClass(), "#onActionRefreshNavigationSamples(DefaultProject)"); // NOI18N
        
        final List<DefaultSample> filterdSamples = FXCollections.observableArrayList();
        samples.stream()
                .filter(sample -> {
                    return sample.getProject().equals(project.getName());
                })
                .forEach(sample -> {
                    filterdSamples.add(sample);
                });
        
        lvNavigationSamples.getItems().clear();
        lvNavigationSamples.getItems().addAll(filterdSamples);
        
    }
    
}
