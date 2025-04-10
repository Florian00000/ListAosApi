# Faire sa Liste d'armée.

En tant qu'utilisateur, je souhaite pouvoir créer mes listes d'armées, qu'elles respectent les règles du jeu égale ou non, et ayant la possibilitée de les enregistrer en ligne. Pour créer mes listes d'armée, je peux accéder aux chartes d'unités des différentes factions, affichés de manière filtrés ou non pour plus de facilité. 

## User Stories:

1. Faire sa liste d'armée: 
    - User Story:*En tant qu'utilisateur connecté ou non, je peux créer mes listes d'armées. Pour cela je peux décider de respecter ou non les règles du jeu égal.* 
    - Critères d'acceptation:
        - Je peux ajouter des unités parmis une liste.
        - Je peux dupliquer mes listes. 
        - Une checkbox me permet de respecter ou non les règles de jeu égal. Si la checkbox est activé et que ma liste ne respecte pas les règles, je ne peux pas enregistrer ma liste et j'ai un message d'alerte.
        - Si je respecte les règles de jeu égal, mes régiments doivent suivre les restrictions de régiments par héros (ex: *sentinelle des forêts 0-1*).
        - Les listes d'armées peuvent contenir des unités auxillaires (si ma liste ne repecte pas les règles de jeu égal et que je ne veux pas suivre la construction en régiments, je peux mettre toutes mes unités en auxilliaire).
        - Je peux choisir des optimisations pour mes listes d'armées (si je resecte les règles je suis limité).
        - Une liste doit respecter une limite de points.

2. Enregistrer en ligne ma liste d'armée.
    - User Story: *En tant qu'utilisateur connecté, je peux enregistrer en ligne mes listes d'armées, les modifier ou les supprimer.*
    - Critères d'acceptation:
        - Sur mon profil j'ai accès à la liste des compositions d'armées. 
        - Un bouton permet d'enregisrer en local (JSON ?) mes les listes presentes en ligne (BDD).
        - Les listes peuvent êtres modifiés ou supprimés, ce qui affecte automatiquement le dupplica (si présent) en local.

3. Modifier, ajouter à ma liste. 
    - User Story: *En tant qu'utilisateur connecté ou non, je peux ajouter des chartes ou des optimisations à ma liste.*
    - Critères d'acceptation: 
        - Un bouton permet d'ajouter une entrée (charte d'unité, optimisation, trait de régiment) à ma liste. 
        - Je peux filtrer les chartes par alliances, par types d'unités. 
        - Les couts en points des profils de batailles sont affichés, et je peux trier par coût en points. 
        - Je peux également ajouter des régiments de renom, un bouton permet d'accéder à la liste des régiments de renoms. 