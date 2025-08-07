# ClinicReminderApp 📱

Application Android Kotlin native permettant aux cliniques et médecins d'envoyer automatiquement des SMS de rappel aux patients avant leur rendez-vous.

## ✨ Fonctionnalités

- Connexion sécurisée (admin/utilisateur)
- Gestion des utilisateurs (admin)
- Liste des patients
- Envoi de SMS manuel et automatique
- Import Google Sheets (à venir)
- UI simple et professionnelle

## ⚙️ Compilation

Ouvrir dans **Android Studio** :

```
1. File > Open > Sélectionner le dossier
2. Laisser Gradle synchroniser
3. Menu Build > Build APK(s)
```

## ✅ Permissions nécessaires

```xml
<uses-permission android:name="android.permission.SEND_SMS"/>
<uses-permission android:name="android.permission.RECEIVE_SMS"/>
<uses-permission android:name="android.permission.READ_SMS"/>
<uses-permission android:name="android.permission.INTERNET"/>
```

## 🚀 Compilation en ligne (optionnel)

1. Créer un dépôt GitHub
2. Uploader ce dossier
3. Aller sur [https://codemagic.io](https://codemagic.io)
4. Connecter ton dépôt et lancer la génération `.apk`
