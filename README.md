# architecture-starter

Starter Spring Boot en architecture hexagonale (ports & adaptateurs) avec des regles d'architecture testees automatiquement via ArchUnit.

Objectif : servir de vitrine technique et de base de depart pour les missions d'audit et de developpement. La structure impose elle-meme les bonnes pratiques, elle ne repose pas sur la discipline des developpeurs.

## Pourquoi ce template

Sur la plupart des projets, l'architecture hexagonale est une intention de depart qui se delite avec le temps : le domaine finit par dependre de JPA, un controleur REST appelle directement un repository Spring Data, etc. Ce starter rend ces derives impossibles a merger : un test ArchUnit casse la build si une regle de dependance est violee.

## Structure

domain            coeur metier, aucune dependance framework, avec deux sous-dossiers : model (entites metier pures) et port (interfaces des besoins du domaine)

application       cas d'usage, orchestre le domaine via les ports

infrastructure    adaptateurs, seule couche qui connait Spring, JPA et HTTP, avec deux sous-dossiers : persistence (implementation JPA d'un port) et rest (controleurs REST)

## Regles d'architecture imposees

Voir la classe ArchitectureTest.

1. Le package domain ne depend d'aucune classe Spring, JPA ou infrastructure

2. 2. Le package application ne depend pas de infrastructure
  
   3. 3. Seul infrastructure peut dependre de frameworks externes (Spring Data, Jakarta Persistence)
     
      4. 4. Les couches ne s'appellent que dans le sens : infrastructure vers application vers domain
        
         5. ## Lancer les tests
        
         6. mvn test
        
         7. Le test ArchitectureTest echoue immediatement si une regle de dependance est violee. C'est le principal interet du template : l'architecture se defend elle-meme en revue de code et en CI.
        
         8. ## Prochaines etapes suggerees
        
         9. Ajouter Testcontainers pour des tests d'integration sur une vraie base Postgres.
        
         10. Ajouter un adaptateur de messaging (Kafka ou RabbitMQ) comme second exemple de port sortant.
        
         11. Brancher une pipeline CI (GitHub Actions) qui execute mvn verify sur chaque PR.
        
         12. ---
        
         13. Template realise par Mohand Abellache, Solutions & Applications Architect.
