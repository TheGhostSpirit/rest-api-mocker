# Model Generator User Documentation

Le rôle principal du générateur de modèle est de générer des modèles d'API. ...

## Gestion d'un modèle

- Création d'un modèle : L'utilisateur pourra choisir de créer un nouveau modèle , il devra renseigné un nom , l'addresse d'un serveur , les informations de contact ainsi que les éventuelles licences

- Import d'un modèle : L'utilisateur pourra choisir d'importer un modèle via un bouton qui lui permettra de sélectionner un dossier.

- Export d'un modèle : L'utilisateur pourra également choisir d'exporter son modèle via un bouton d'export, il devra spécifier le format souhaité (YAML ou JSON).

## Création de routes

L'utilisateur pourra spécifier ces routes en y renseignant l'URL.

- Spécification de la méthode HTTP : L'utilisateur pourra choisir la méthode http (get,post,put,etc..) en sélectionnant l'onglet correspondant. Il pourra également y renseigner une description.

- Spécification des query params : Des paramètres pourront être spécifier , l'utilisateur devra renseigné un nom et un type.

- Spécification du body : L'utilisateur aura la possibilité d'ajouter un body , il pourra y ajouter une description et ajouter un objet ou utiliser un modèle déja défini 

- Spécification des responses : L'utilisateur pourra personnaliser le message correspondant au code d'état retourner. Il pourra également y ajouter un header et un body.

## Création de modèles

Lors de la création d'un modèle accessible depuis un bouton l'utilisateur devra renseigné un nom , une description (optionnelle) et des objets

