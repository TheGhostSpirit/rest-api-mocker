# Model Generator User Documentation

Le rôle principal du générateur de modèle est de générer des modèles d'API.

## Gestion d'un modèle d'API

### Création d'un modèle

Depuis le menu, l'utilisateur pourra sélectionner un bouton lui permettant de créer une nouvelle API.
Une fois sur cette vue il devra renseigner le nom de l'API, sa description, sa version, sa licence, ainsi qu'un contact (nom, prenom, email).

### Import d'un modèle

Depuis le menu, l'utilisateur pourra sélectionner un bouton lui permettant d'ouvrir le sélecteur de fichier permettant de choisir le modèle a importer.
Une fois importée, il pourra modifier ses caractéristiques.
Si le fichier est au format JSON ou YAML, les informations seront préremplie dans le cas contraire une vue nouvelle API sera affichée.

### Export d'un modèle

Depuis la vue API, l'utilisateur aura accès à deux boutons lui permettant d'exporter son modèle d'API en JSON ou en YAML.

## Création de routes

Une fois l'API créée, l'utilisateur pourra accéder au bouton lui permettant de créer des nouvelles routes, ainsi qu'a une liste répertoriant les routes déjà créée avec option d'édition et de suppression.

### Spécification de la méthode HTTP

L'utilisateur a accès à une liste déroulante lui permettant de sélectionner la méthode HTTP. Il peut les ajouter, éditer et supprimer. Il devra également préciser le path de la route.

### Spécification des query params

L'utilisateur pourra ajouter, éditer ou supprimer des query params avec comme caractéristiques:
* name
* type
* required

### Spécification du body

L'utilisateur pourra ajouter, éditer ou supprimer des élements au body avec comme caractéristiques:
* name
* type
* required

### Spécification des responses

L'utilisateur pourra créer des résponses. Il doit renseigner le statut code ainsi qu'une description. Il peut ensuite ajouter, éditer ou supprimer des élements a la response avec comme caractéristiques:
* name
* type
* required