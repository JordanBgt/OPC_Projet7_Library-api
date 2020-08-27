# Openclassrooms projet 7 : Développez le nouveau système d'information des bibliothèques d'une grande ville

## Contexte 

Le client souhaite la création d'un ensemble d'outils numériques pour les différents acteurs des bibliothèques de la ville : 

* Un site web et une appliaction mobile pour les usagers des bibliothèques
* Un logiciel pour le personnel des bibliothèques
* Un logiciel pour des traitements automatisés (mails de relance)

Le site web permettra aux usagers de suivre les prêts de leurs ouvrages. Ils pourront : 

* Rechercher des ouvrages et voir le nombre d’exemplaires disponibles
* Consulter leurs prêts en cours. Les prêts sont pour une période de 4 semaines
* Prolonger un prêt en cours. Le prêt d’un ouvrage n’est prolongeable qu’une seule fois. La prolongation ajoute une nouvelle période de prêt (4 semaines) à la période initiale.

L'application mobile (IOS et Android) devra fournir les mêmes services que le site web.

Le logiciel pour le personnel des bibliothèques permettra notamment de gérer les emprunts et les livres rendus.

Le logiciel pour les traitements automatisés permettra d'envoyer des mails de relance aux usagers n'ayant pas rendu les livres en fin de période de prêt. L'envoi est automatique à la fréquence d'un par jour.

### Plan de travail

* Release 1.0 : API Web, Application web, Batch
* Release 2.0 : Logiciel pour le personnel
* Release 3.0 : Application mobile

Nous sommes chargés uniquement de la release 1.0. Mais dès cette release, les actions de création d'un prêt et retour d'un prêt seront implémentées même si elles ne seront utilisées par le logiciel pour le personnel uniquement à la release 2.0.

## Contraintes techniques 

* API Web en REST ou SOAP : les clients (site web, batch, logiciel pour les personnels, application mobile) communiquerons à travers une API Web.
* Application web avec un framework MVC (Spring MVC, Struts, ...)
* Packaging avec Maven

## Référentiel d'évaluation

Concevoir une application web avec une approche par composants

* L’application web est consommatrice d’une API web.
* Un framework MVC est utilisé.
* Le scope fonctionnel est respecté.

Créer une API web avec un web service SOAP ou un microservice REST

* La logique métier est implémentée au sein d’un composant qui expose une API web.

Interagir avec des composants externes

* L’API web communique avec la base de données.

Sélectionner les langages de programmation adaptés pour le développement de l’application

* Différents frameworks sont mis en oeuvre en fonction des choix techniques.
* Les choix techniques sont justifiés à l’oral.

Respecter les bonnes pratiques de développement en vigueur

* Le packaging est géré avec Apache Maven.
* Chaque composant a un cycle de développement qui lui est propre.
* Une architecture n-tiers est mise en oeuvre.
* Le patron de conception ‘Inversion de contrôle’ est mis en oeuvre.
