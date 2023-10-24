[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/aRxH_N2r)
------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>

Merci au cours INF3500 pour le format du Markdown

------------------------------------------------------------------------

Travail pratique \#3
====================

Arbre Binaire de recherche
=============================================================

Objectifs
---------
* Apprendre le fonctionnement d’un arbre binaire de recherche

* Comprendre le fonctionnement d’un arbre AVL

* Utiliser les concepts vus en cours dans des analyses de complexité

Astuces
-------
Veuillez consulter la section **Astuces** du README du travail pratique 1 pour la configuration du projet.

------------------------------------------------------------------------

Partie 1 : Implémentation des arbres binaires de recherche BST et AVL
---------------
Un arbre AVL est un arbre binaire de recherche équilibré. Celui-ci oblige que la différence entre la hauteur gauche et la hauteur droite soit inférieure à 2. Si cette condition n’est pas respectée, il vous faut rééquilibrer l’arbre avec l’algorithme des arbres AVL. Dans le cas du retrait d’un élément, une démarche spécifique doit être utilisée.

L'implémentation de ces algorithmes est disponible dans les diapositives **Cours05** et **Cours06** et dans le chapitre 4 du livre de Mark Allen Weiss.

Pour la première partie, il vous est demandé de compléter les classes BinaryTree, BinarySearchTree et AvlTree.

**ATTENTION : Une note de 0 sera automatiquement attribuée si vous utilisez un arbre binaire d’une librairie quelconque.**

------------------------------------------------------------------------

Partie 2 : Analyse des différents arbres binaires de recherche
---------------
Après avoir mis en œuvre les arbres binaires BST et AVL, le code de l'arbre Splay vous est fourni.

Votre première mission est d'analyser ou de "reverse engineer" l'arbre Splay afin d'en comprendre les mécanismes internes et ses particularités. Vous devrez ensuite comparer vos découvertes avec ce qui est présenté dans la littérature ou ce que ChatGPT peut fournir sur le sujet.

Dans la seconde partie de cette section, vous allez explorer expérimentalement la performance de ces trois arbres en ce qui concerne différentes opérations dans les scénarios du cas moyen et du pire cas.

### Objectifs
* Comprendre en profondeur le fonctionnement de l'arbre Splay et comparer vos découvertes avec des sources fiables.
* Évaluer et comparer la performance des trois types d'arbres (BST, AVL et Splay) pour les opérations d'insertion, de recherche et de suppression.
* Réaliser des tests pour des cas moyens et des pires cas.
* Rédiger un rapport présentant vos observations, appuyées par des données expérimentales.

------------------------------------------------------------------------

Partie 3 : Problème typique d'entrevue
----------------

On vous demande d'élaborer un algorithme répondant à la question d'entrevue suivante de trois manières différentes.
Premièrement de façon itérative en temps linéaire dans la fonction `findNonDuplicateIterativeLinear`, ensuite de matière itérative en temps logarithme dans la fonction `findNonDuplicateIterative` et enfin de manière récursive dans la fonction `findNonDuplicateRecursive`.

Pour chacune des implémentations, on vous demande bien de respecter la contrainte de complexité temporelle et d'expliquer celle-ci.
De plus, on vous demande de déterminer et d'expliquer la complexité spatiale de chacune des implémentations. Dans les deux cas, on vous demande de justifier le pire cas ainsi que le cas moyen.
L'objectif d'avoir trois implémentations différentes est d'observer les différences dans la complexité spatiale et temporelle.

### Entrées
* Un tableau d'entier trié en ordre croissant composé que de doublons à l'exception d'un nombre (ex : [1, 1, 2, 4, 4])

### Sorties
* Un entier contenant le nombre qui est seul (sans un doublons)

### Contraintes
Supposons un tableau de taille n
* Complexité temporelle : O(n) en pire cas pour la fonction  la fonction `findNonDuplicateIterativeLinear`
* Complexité temporelle : O(log(n)) en pire cas pour les deux autres
* Complexité spatiale en pire et en moyen cas : À vous de la déterminer et de vous justifier

Si le tableau ne contient pas d'éléments, il faut retourner *null*.

Si le tableau contient au moins un éléments, vous pouvez assumer qu'il y aura seulement 1 élément seul.

**Il est permis d’utiliser la librairie java.util pour cette partie. Une note de 0 sera attribuée à cette partie si l’étudiant utilise quelconque autre librairie.**

------------------------------------------------------------------------

### Exemple 1
Entrées :
* Tableau : [1, 1, 5, 6, 6]

* Sorties : 5

Explication :

L'entier 5 est le seul qui n'a pas un doublons (il est seul dans le tableau).

------------------------------------------------------------------------

### Exemple 2
Entrées :
* Tableau : [1]

* Sorties : 1

Explication :

L'entier 1 est tout seul dans le tableau

------------------------------------------------------------------------

### Exemple 3
Entrées :
* Tableau : []

* Sorties : null

Explication :

Le tableau ne contient aucun élément et donc, comme il n'y a pas de réponse, on retourne null;

------------------------------------------------------------------------

La remise
---------------
La remise doit contenir les fichiers java complétés, les fichiers de générations des données et un rapport PDF contenant les graphiques et les analyses.

------------------------------------------------------------------------

Barème de correction
--------------------
||||
|-----------------|--------------------------------|-----|
| Partie 1        | Réussite des tests             | /6  |
|                 | Qualité du code                | /1  |
| Partie 2        | Compréhension de l'arbre Splay | /2  |
|                 | Résultats expérimentaux        | /3  |
|                 | Justification de l'analyse     | /3  |
| Partie 3        | Réussite des tests             | /3  |
|                 | Justification de complexité    | /1  |
|                 | Qualité du code                | /1  |
| Total           |                                | /20 |


##### Qu'est-ce que du code de qualité ?
Consulter ce lien pour des détails sur les bonnes pratiques: https://drive.google.com/drive/folders/14RHZZgxQx5ftTdNwSdQGOWYrXxmB6s3r?usp=sharing
* Absence de code dédoublé (FAITES DES FONCTIONS!!!)
* Absence de *warnings* à la compilation
* Absence de code mort : Code en commentaire, variable inutilisé, etc...
* Respecte les mêmes conventions de codage dans tout le code produit
  * Langue utilisée
  * Noms des variables, fonctions et classes
* Variables, fonctions et classes avec des noms pertinents et clairs qui expliquent leur intention et non leur comportement

**Petite astuce:** Utiliser les fonctionnalités offertes par IntelliJ!

Le dernier commit de votre répertoire sera utilisé comme remise finale. Chaque jour de retard créera une pénalité
additionnelle de 20 %. Aucun travail ne sera accepté après 4 jours de retard.

