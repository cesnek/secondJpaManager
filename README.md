# secondJpaManager
Sample for https://github.com/spring-projects/spring-boot/issues/6830

- cz.marbes.app.MainApp is "Big big standalone application. It is imposible to refactor all pieces and dependencies"
- cz.marbes.app.PlugableModuleConfiguration is "Small independent module with own datasource".

Standalone MainApp is running.
When I plug "plugablemodule.jar" (in pom.xml) it has three problems:

- cz.marbes.app.service.DogService.entityManager

- cz.marbes.app.service.DogService.entityManager2

- cz.marbes.app.MainApp @ComponentScan - owerwrite all entityManagerFactoryBean.setPackagesToScan()
  (we have over twenty @ComponentScan in modules)
