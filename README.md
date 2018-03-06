Lib-Project-Sampler
===



Intention
---

`Lib-Project-Sampler` is inspired by [FXSampler] which is a subproject from the awesome 
project [ControlFX]. 

`FXSampler` is a generic sampler application for any [JavaFX] framework. An introduction 
to it can be found in the article [JavaFX Tip 25: Use FXSampler] from [Dirk Lemmermann].

So what's are the points from `Lib-Project-Sampler`?
* Architecture TODO
* The fluent builder [ProjectSamplerBuilder] let the developer allowed to easily configure 
  and start the framework.
* Usage from the library [fast-annotation-scanner] to scan for `projects` and `samples`.
* Usage from the awesome library [afterburner.fx] from [Adam Bien] which is a minimalistic 
  JavaFX MVP framework based on [Convention over Configuration] and [Dependency Injection]. 
* Integration from my libraries [Lib-Action], [Lib-Database-ObjectDB], [Lib-Logger], 
  [Lib-Preferences], [Lib-Properties] and [Lib-Validation].

Written in [JavaFX] and with the [NetBeans IDE] the project contains several sub-libraries 
for specific tasks. For example:
* The sub-library `Lib-Project-Sampler-Core` contains the core functionalities from the project.
    * These are on the one side the annotations, eg. [@Project] or [@Sample], which can be used 
      to annotate classes which then can be scanned by the framework.
    * On the other side with the class [ProjectSampleScanner] `defined areas` can be scanned for the annotations 
      and load then the founded classes in cache for later usage.
    * At last the fluent builder [ProjectSamplerBuilder] should be mentioned which allowed the 
      developer to define the `scan areas` and also to start the framework.
* The sub-library `Lib-Project-Sampler-Demo` shows a demonstration from the features from 
  the core library.

TODO Screenshot Demo



Content
---

* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you could report an [Issue].
* If you want to contribute to the project plz fork the project and do a [Pull Request].



License<a name="License" />
---

The project `Lib-Project-Sampler` is licensed under [General Public License 3.0].

TODO Add clue for the sub-projects.


Autor<a name="Autor" />
---

The project `Lib-Project-Sampler` is maintained by me, Peter Rogge. See [Contact](#Contact).

TODO Add clue for the sub-projects.



Contact<a name="Contact" />
---

You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Images)



[//]: # (Links)
[Adam Bien]:http://www.adam-bien.com/
[afterburner.fx]:https://github.com/AdamBien/afterburner.fx
[ControlFX]:https://bitbucket.org/controlsfx/controlsfx
[Convention over Configuration]:https://en.wikipedia.org/wiki/Convention_over_configuration
[Dependency Injection]:https://en.wikipedia.org/wiki/Dependency_injection
[Dirk Lemmermann]:http://dlsc.com/
[fast-annotation-scanner]:https://github.com/lukehutch/fast-classpath-scanner
[FXSampler]:https://bitbucket.org/controlsfx/controlsfx/src/b8d2f373b35d4ce31b60f365a93fa9e50da9f133/fxsampler/?at=default
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[Issue]:https://github.com/Naoghuman/lib-project-sampler/issues
[JavaFX]:http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html
[JavaFX Tip 25: Use FXSampler]:http://dlsc.com/2017/03/02/fxsampler/
[Lib-Action]:https://github.com/Naoghuman/lib-action
[Lib-Database-ObjectDB]:https://github.com/Naoghuman/lib-database-objectdb
[Lib-Logger]:https://github.com/Naoghuman/lib-logger
[Lib-Preferences]:https://github.com/Naoghuman/lib-preferences
[Lib-Properties]:https://github.com/Naoghuman/lib-properties
[Lib-Validation]:https://github.com/Naoghuman/lib-validation
[NetBeans IDE]:https://netbeans.org/
[@Project]:https://github.com/Naoghuman/lib-project-sampler/blob/master/Lib-Project-Sampler-Core/src/main/java/com/github/naoghuman/lib/project/sampler/core/annotation/Project.java
[ProjectSamplerBuilder]:https://github.com/Naoghuman/lib-project-sampler/blob/master/Lib-Project-Sampler-Core/src/main/java/com/github/naoghuman/lib/project/sampler/core/ProjectSamplerBuilder.java
[ProjectSampleScanner]:https://github.com/Naoghuman/lib-project-sampler/blob/master/Lib-Project-Sampler-Core/src/main/java/com/github/naoghuman/lib/project/sampler/internal/scanner/ProjectSampleScanner.java
[Pull Request]:https://help.github.com/articles/using-pull-requests
[@Sample]:https://github.com/Naoghuman/lib-project-sampler/blob/master/Lib-Project-Sampler-Core/src/main/java/com/github/naoghuman/lib/project/sampler/core/annotation/Sample.java
