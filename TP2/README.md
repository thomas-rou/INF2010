
------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>


------------------------------------------------------------------------

Travail pratique \#2
====================

Tables de hachage
=============================================================

Objectifs
---------
* Apprendre le fonctionnement d’une table de hachage

* Implémenter une variante de la QuadraticProbing HashTable

* Utiliser une table de hachage dans un problème complexe


Partie 1 : Implémentation d'une table de hachage
---------------
Une table de hachage est une structure de données qui utilise une fonction de dispersement (fonction de hashage) pour donner une valeur numérique à une clé qui peut être d’un type quelconque (string, int, MyCustomClass, ..). Cette valeur numérique retournée par la fonction de dispersement est utilisée comme indice dans un tableau, ce qui nous donne un opération d’accès en O(1).

Il arrive que la fonction de dispersement retourne la même valeur numérique pour deux clés différentes. Ce phénomène nommé « collision » est un problème connu des tables de hachage et plusieurs techniques existent pour pallier à ce problème. Dans le cadre de ce laboratoire, Vous êtes offert le code de la Quadratic Probing HashTable de Weiss, et vous deverez implémenter une variante de cette table : Robin Hood HashTable

### Fonctionnement de Robin Hood HashTable
Dans le cadre du TP, la variante admet ces principales règles:

1.  À chaque collision, la table compare la `probeDistance` (distance parcourue) de l'entrée cherchant une position à celle déjà en position. Si l'entrée déjà positionnée a une `probeDistance` strictement inférieure, elle cède sa place à l'entrée en recherche. La recherche continue pour l'entrée déplacée.
2. Si l'entrée déplacée n'est pas `Active`, la recherche ne continue pas.
3.  La `probeDistance` commence à 0 et incrémente de 1 à chaque collision. Pour une entrée déplacée, on ne commence pas sa recherche de 0 mais on continue selon sa probeDistance.

Conseil: Évitez les approches récursives.

### Tests
- Réussir tous les tests vous aidera à avoir une bonne implémentation de la table.
### Question après complétion des tests
Téléchargez le fichier InputArrays.txt sur moodle et ajoutez le dans `./src/test/resources/inputArrays.txt`
- Roulez `executionTimeTest()` une dizaine de fois et notez le temps moyen pris par `RobinHoodHashTable` et `QuadraticProbingHashTable` pour les opérations de recherche.
- Ajoutez du code pour compter les collisions après les insertions dans les deux tables.
- Documentez vos observations, avantages et inconvénients de chaque table de hachage dans les commentaires de la classe `RobinHoodHashTable`.
Que constatez vous sur les avantages de RobinHood sur la Quadratic et vice-versa.
Répondre à la question en commentaires dans la classe RobinHood

------------------------------------------------------------------------

Partie 2 : Problème typique d'entrevue
----------------


On vous demande d'implémenter un algorithme d'analyse de phrase.
Dans celui-ci on vous donne une séquence de mots séparée par des espaces et on vous demande de trouver le terme le plus populaire, qui n'est pas un mot sans intérêt (*stop word*), et son nombre d'occurences.

Votre algorithme devra également suivre ses consignes:
* Il ne devrait pas être sensible aux majuscules et aux minuscules (*case insensitive*). Ex: MA ma Ma mA doivent être reconnu comme étant le même mot.
* Il devra toujours **retourner le mot en minuscule**.
* En cas d'égalité dans le nombre d'occurences entre 2 mots ou plus, il faut choisir le mot en se basant sur l'ordre alphabétique. Ex: arbre aura priorité sur ballon en cas d'égalité entre les deux.

**Note: Votre algorithme n'a pas besoin de tenir compte de la ponctuation ou des accents**

### Entrées
* Un string de n caractères contenant une phrase et un tableau de string de m caractères contenant les mots sans intérêts.
* Voici un exemple d'une phrase: "INF2010 est le meilleur cours de Polytechnique"
* Voici un exemple du tableau contenant les mots sans intérêt: ["est", "le", "de"]

### Sorties
* Une paire contenant un String et un entier (ex : Pair{first = "netflix"; second = 999;})
* Le premier indique **en minuscule** le mot d'intérêt le plus populaire dans la phrase
* Le second indique le nombre d'occurences de ce mot dans la phrase

**Note: La classe Pair est fournit dans le code de départ**

### Contraintes
Supposons n le nombre de caractères dans la phrase et m le nombre de caractères dans le tableau de mots sans intérêts
* Complexité spatiale : O(n+m) en pire cas
* Complexité temporelle : O(n+m) en cas moyen

Vous devez brievement justifiez votre complexité spatiale et temporelle dans l'en-tête de la fonction _findMostCommonValidWord_

**Seul l'utilisation de la librairie java.util est permise pour cette partie**

------------------------------------------------------------------------

### Exemple 1
Entrées : "Tu devrais regarder Arcane Arcane est vraiment bon." et ["tu", "est"]

Sorties : Pair{first = "arcane"; second = 2;}

Explication :

Dans cette phrase, le mot "Arcane" est répété 2 fois alors que les autres mots sont répétés 1 fois.

Le mot "Arcane" ne figure pas dans la liste de mots sans intérêts et il est retourné en minuscule.

------------------------------------------------------------------------

Entrées : "As tu regarde Squid Game Oui jai vu Squid Game hier As" et ["As", "est"]

Sorties : Pair{first = "game"; second = 2;}

Explication :

Dans cette phrase, les mots "Squid", "Game" et "As" sont répétés 2 fois alors que les autres mots sont répétés 1 fois.

Comme le mot "As" est un mot sans intérêt, on se retrouve avec "Squid" et "Game". Comme il y a une égalité d'occurences entre les deux, on choisit "Game" puisque g vient avant s.

Le mot est retourné en minuscule.

------------------------------------------------------------------------

Entrées : "Il faut arreter Jinx Jinx" et ["il", "FAUT", "arreter", "jinx"]

Sorties : Pair{first = null; second = null}

Explication :

Dans cette phrase, le mot "Jinx" est répété 2 fois alors que les autres mots sont répétés 1 fois, mais ce mot est dans la liste de mots sans intérêts. Même s'il est en minuscule, l'algorithme considère que Jinx et jinx sont un même mot (même principe pour faut et FAUT).

Tous les autres mots de la phrase sont des mots sans intérêts et donc on retourne null pour indiquer qu'il n'y a pas de réponse.

------------------------------------------------------------------------

Barème de correction
--------------------

||||
|-----------------|-----------------------------|-----|
| Partie 1        | Réussite des tests          | /12 |
|                 | Propres Observations        | /1  |
| Partie 2        | Réussite des tests          | /2  |
|                 | Complexité attendue         | /2  |
|                 | Justification de complexité | /2  |
| Qualité du code |                             | /1  |
| Total           |                             | /20 |

Un chargé s’assurera que votre code ne contourne pas les tests avant de vous attribuer vos points dans la catégorie «Réussite des tests». Il est important de respecter les complexités en temps mises dans la description de chaque fonction.

Pour avoir tous les points dans la catégorie « Complexité en temps » de la partie 2, vous devez réaliser un algorithme respectant les commentaires situés dans `InterviewTest.java`.


------------------------------------------------------------------------

**Correction automatique** : Les tests sont un bon moyen d'évaluer votre note avant la remise. Néanmoins, l’entièreté
de votre code sera révisée par un chargé de laboratoire pour s'assurer qu'il réalise véritablement les tâches demandées.
Il peut donc y avoir des différences entre la note donnée par vos tests et votre note finale.

##### Qu'est-ce que du code de qualité ?
* Absence de code dédoublé (FAITES DES FONCTIONS!!!)
* Absence de *warnings* à la compilation
* Absence de code mort : Code en commentaire, variable inutilisé, etc...
* Respecte les mêmes conventions de codage dans tout le code produit
    * Langue utilisée
    * Noms des variables, fonctions et classes
* Variables, fonctions et classes avec des noms pertinents et clairs qui expliquent leur intention et non leur comportement


**Date de remise: 15 Octobre, 23h59**

