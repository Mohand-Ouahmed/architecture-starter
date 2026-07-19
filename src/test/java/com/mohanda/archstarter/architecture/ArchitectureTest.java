package com.mohanda.archstarter.architecture;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Ces tests font tenir l'architecture hexagonale dans le temps : toute
   * violation des regles de dependance fait echouer la build, independamment
   * de la vigilance des relecteurs de code.
   */
class ArchitectureTest {

    private static JavaClasses classes;

    @BeforeAll
      static void importClasses() {
                classes = new ClassFileImporter().importPackages("com.mohanda.archstarter");
      }

    @Test
      void domainDoesNotDependOnSpringOrJpa() {
                ArchRule rule = noClasses()
                                  .that().resideInAPackage("..domain..")
                                  .should().dependOnClassesThat()
                                  .resideInAnyPackage("org.springframework..", "jakarta.persistence..");

          rule.check(classes);
      }

    @Test
      void domainDoesNotDependOnInfrastructure() {
                ArchRule rule = noClasses()
                                  .that().resideInAPackage("..domain..")
                                  .should().dependOnClassesThat()
                                  .resideInAPackage("..infrastructure..");

          rule.check(classes);
      }

    @Test
      void applicationDoesNotDependOnInfrastructure() {
                ArchRule rule = noClasses()
                                  .that().resideInAPackage("..application..")
                                  .should().dependOnClassesThat()
                                  .resideInAPackage("..infrastructure..");

          rule.check(classes);
      }

    @Test
      void layersRespectDependencyDirection() {
                ArchRule rule = classes()
                                  .that().resideInAPackage("..infrastructure..")
                                  .should().onlyDependOnClassesThat()
                                  .resideInAnyPackage("..infrastructure..", "..application..", "..domain..", "java..", "org.springframework..", "jakarta..");

          rule.check(classes);
      }
}
