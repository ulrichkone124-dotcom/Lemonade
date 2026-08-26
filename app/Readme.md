#  Lemonade App - Jetpack Compose

Une application Android interactive développée avec **Kotlin** et **Jetpack Compose** qui illustre la gestion des états (`State`) et des événements de clics à travers un cycle de préparation de limonade.

---

## Aperçu des fonctionnalités

L'application guide l'utilisateur à travers 4 étapes interactives :

1. **Sélectionner un citron** : Cliquer sur l'arbre pour cueillir un citron.
2. **Presser le citron** : Cliquer sur le citron un nombre aléatoire de fois (entre 2 et 4 clics) pour extraire le jus.
3. **Boire la limonade** : Cliquer sur le verre rempli pour la déguster.
4. **Recommencer** : Cliquer sur le verre vide pour réinitialiser le cycle.

---

## 🛠️ Technologies & Concepts utilisés

* **Langage** : Kotlin
* **UI Framework** : Jetpack Compose
* **Architecture UI** :
    * `Scaffold` & `TopAppBar` pour la barre de navigation jaune supérieure.
    * `State Management` avec `rememberSaveable` et `mutableIntStateOf` pour préserver l'état lors des rotations d'écran.
    * Composables personnalisés (`ApplicationLemonade`, `TexteEtImageCitron`).
    * `Modifier` (`clip`, `background`, `clickable`, `padding`) pour reproduire le design exact du codelab officiel Google.

---

##  Structure du projet

* **`MainActivity.kt`** : Point d'entrée de l'application contenant la logique d'état et le layout Compose.
* **`res/drawable/`** : Ressources graphiques (`lemon_tree`, `lemon_squeeze`, `lemon_drink`, `lemon_restart`).
* **`res/values/strings.xml`** : Textes et descriptions d'accessibilité de l'application.

---

##  Lancement du projet

1. Ouvrez le projet dans **Android Studio**.
2. Associez vos images dans le dossier `res/drawable/`.
3. Assurez-vous que les clés de texte existent dans `res/values/strings.xml`.
4. Sélectionnez votre émulateur Android (ex: Pixel 6).
5. Cliquez sur **Run** (`Shift + F10` ou le bouton vert ▶️).