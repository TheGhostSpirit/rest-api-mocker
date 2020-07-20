# Model Manager User Documentation

Le rôle principal du manager de modèle est de répertorier tout ses modèles d'API sur un serveur et de pouvoir les mocker, les déployer et les tester.

## Authentification

### Connexion

L'utilisateur peut cliquer sur le bouton "Sign in" au milieu de la page d'Accueil ou en haut à droite dans l'en tête de l'application.
Il devra ensuite s'authentifier avec un compte Microsoft.

### Deconnexion

L'utilisateur doit cliquer sur le bouton menu en haut a droite dans l'en tête de l'application, puis sur le bouton "Sign out".

## Gestion des modèles

### Import

Dans la liste des modèles, l'utilisateur doit appuyer sur le bouton "Import", puis sélectionner le fichier à importer.
Ce fichier doit être au format YAML.

### Refresh

A coté du bouton d'import se trouve le bouton "Refresh" qui permet de rafraichir la liste des modèles.

### Suppression

A coté de chaque modèle se trouve une poubelle rouge permettant de supprimer le modèle.

### Edition

A cote de chaque modèle se trouve un crayon noir permettant d'ouvrir un éditeur de YAML.

### Déploiement

A coté de chaque modèle se trouve une fléche noire vers le haut permettant de mocker et de déployer l'API.

## Client de test de l'API

Une fois déployée l'API peut être testée dans un client HTTP integré.

### Choix d'une route

A gauche de l'écran se trouve une liste permettant de choisir la route a tester.
Il est aussi possible de modifier la route à la main grace aux champs méthode et path au milieu de l'écran.

### Envoi d'une requête

L'utilisateur doit sélectionner le bouton "Send" pour envoyer la requête. Il peut aussi y ajouter un body dans le champ de texte en dessous de la route.
L'utilisateur peut ensuite voire la réponse s'afficher.