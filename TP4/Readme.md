------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>


------------------------------------------------------------------------

Travail pratique \#4
====================

Heap / Monceaux
=============================================================

Objectifs
---------
* Apprendre le fonctionnement d’un Heap

* Comprendre la différence entre un arbre complet et incomplet

* Utiliser les concepts vus en cours dans des analyses de complexité

Astuces
-------
Veuillez consulter la section **Astuces** du README du travail pratique 1 pour la configuration du projet.

------------------------------------------------------------------------

Partie 1 : Implémentation d'un algorithme de détection d'arbre complet (2 pts)
---------------
Dans le cadre de votre cours de INF2010, vous avez vu la théorie sur les arbres complets. 

Petit rappel, un arbre est dit complet si sa représentation tableau ne contient pas d'élément vide entre le root et le dernier élément.

Dans la partie 1, on vous demande d'implémenter un algorithme qui détect si un arbre est complet. Si l'arbre est complet,
l'algorithme retourne vrai. 

**La complexité ne doit pas dépasser O(n)**

------------------------------------------------------------------------

Partie 2 : Différence entre Binary Tree et Heap (8 pts)
---------------
Dans la partie 2, on vous demande d'identifier les différences entre un Binary tree et un Heap.
Pour ce faire, on vous demande d'écrire des tests qui permettent de montrer ces différences.

Voici les différences que vous devez tester:
1. Insertion dans un Heap vs un arbre binaire
2. Suppression de valeurs (delete)
3. GetMin()/GetMax()

Dans un rapport, veuillez mettre vos observations sur la différence entre le Heap et un arbre binaire et répondre aux questions suivantes:
* Quelle(s) est/sont la/les différence(s) de les insertions et suppression de donnée?
* Donner 2 cas où l'utilisation d'un heap est meilleur qu'un arbre binaire
* Si on compare un Heap à un arbre AVL, est-ce que les différences sont encore aussi grande en terme de complexité? Expliquer vos résultats

**Pour cette partie, on vous fournis le code de Binary tree et AVL tree. Vous devrez utiliser PriorityQueue pour la partie des Heap**


------------------------------------------------------------------------

Partie 3 : Problèmes typiques d'entrevue (10 pts)
----------------

**Partie 3.1**

En premier lieu, on vous demande d'écrire un algorithme qui permets de détecter si un arbre est un Max Heap, un Min Heap
s'il ne s'agit pas d'une heap. 

Pour vous aider, vous aurez un Enum qui servira de type de retour:
Enum type{ MaxHeap, MinHeap, NotHeap}

Contraites:
* Doit être O(N) où N est le nombre de Noeud

Exemple:

Cas 1:

          1
        /   \
       9     4
      / \   / 
     10 15 20  

Retourne MinHeap

Cas 2:

          20
        /    \
      10     15
     /  \   /  \
    4   5  12   14

Retourne MaxHeap

Cas 3:

        10
       /  \
          15
         /  \
        12   20

Retourne NotHeap


**Partie 3.2**

Pour ce problème typique d'entreview, on vous demande de trier un array de interger à partir de la fréquence
d'apparition des nombres. De plus, si 2 ou plusieurs nombres ont la même fréquence, ils seront triés par leur valeur.

Contraintes:
* Ne doit pas dépasser O(N log K ) où N est le nombre d'élément et K est le k ième élément qu'on cherche
* Les nombres doivent être triés par leur fréquence et leur valeur si les fréquences sont égaux
* Vous pouvez utiliser toutes structures pour résoudre ce problème, mais aucune librairie externe

Exemple:

cas 1:

Arr:{1, 1, 1, 2, 3, 4, 5, 7, 7, 7}


K = 1 --> Retourne: 7

K = 2 --> Retourne: 1

Cas 2:

Arr:{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9}

K = 1 --> Retourne 5

K = 2 --> Retourne 11

**Attention, seulement 1 seule valeur est retourné**

**N'oubliez pas de donner les complexités et les justifications de toutes les fonctions que vous faites**

------------------------------------------------------------------------

Barème de correction
--------------------
||||
|-----------------|--------------------------------|-----|
| Partie 1        	 | Réussite des tests             			 | /2  |
| Partie 2      	 | Différence entre Heap et Arbre 	 | /8  |
|                	 | Validité des tests		       		 | /3  |
|                	 | Justification des réponses 		 | /5  |
| Partie 3        	 | Partie 3.1 		         			 | /4  |
|                 	 | Justification de complexité 3.1  	 | /1  |
|			 | Partie 3.2						 | /4 |
|			 | Justification de complexité 3.2 	| /1   |
|Qualité du code  |              						|      |
| Total           	 |                                				| /20 |


**Un guide de codage sera mis à votre disposition durant le première semaine du TP. Ce guide 
sera la mise-à-jour de l'ancien guide de codage et sera pris en compte lors de la correction**

**Petite astuce:** Utiliser les fonctionnalités offertes par IntelliJ!

Le dernier commit de votre répertoire sera utilisé comme remise finale. Chaque jour de retard créera une pénalité
additionnelle de 20 %. Aucun travail ne sera accepté après 4 jours de retard.

