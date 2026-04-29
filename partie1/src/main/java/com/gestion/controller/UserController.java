package com.gestion.controller;

import com.gestion.model.User;
import com.gestion.service.UserService;
import java.util.List;

/**
 * CONTRÔLEUR (Controller) - Rôle :
 * Le Contrôleur est le chef d'orchestre de l'architecture MVC.
 * Il reçoit les requêtes/actions de l'utilisateur (ou du main),
 * appelle le Service approprié pour traiter la logique métier,
 * puis affiche le résultat (ici dans la console).
 *
 * Le Contrôleur NE contient PAS de logique métier :
 *  - Il ne sait pas comment les données sont stockées
 *  - Il ne valide pas les données (c'est le rôle du Service)
 *  - Il fait uniquement le lien entre l'entrée (user input) et la sortie (affichage)
 *
 * Dans une application web Spring Boot, le Contrôleur recevrait des
 * requêtes HTTP et retournerait des réponses JSON ou des vues HTML.
 */
public class UserController {

    // Dépend de l'interface UserService, pas de l'implémentation
    // final c'est une constant 
    private final UserService userService;

    // Injection de dépendance via le constructeur
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ─── Actions disponibles ─────────────────────────────────────────────────

    /**
     * Ajoute un utilisateur et affiche un message de confirmation.
     */
    public void ajouterUtilisateur(User user) {
        System.out.println("\n=== [Controller] → Ajout d'un utilisateur ===");
        try {
            userService.addUser(user);
            System.out.println("[Controller] ✓ Succès : " + user);
        } catch (Exception e) {
            System.out.println("[Controller] ✗ Erreur : " + e.getMessage());
        }
    }

    /**
     * Récupère un utilisateur par son id et l'affiche.
     */
    public void afficherUtilisateur(Long id) {
        System.out.println("\n=== [Controller] → Recherche de l'utilisateur id=" + id + " ===");
        try {
            User user = userService.getUser(id);
            System.out.println("[Controller] ✓ Trouvé : " + user);
        } catch (Exception e) {
            System.out.println("[Controller] ✗ Erreur : " + e.getMessage());
        }
    }

    /**
     * Affiche tous les utilisateurs enregistrés.
     */
    public void afficherTousLesUtilisateurs() {
        System.out.println("\n=== [Controller] → Liste de tous les utilisateurs ===");
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("[Controller] Aucun utilisateur enregistré.");
            } else {
                users.forEach(u -> System.out.println("  - " + u));
            }
        } catch (Exception e) {
            System.out.println("[Controller] ✗ Erreur : " + e.getMessage());
        }
    }

    /**
     * Supprime un utilisateur par son id.
     */
    public void supprimerUtilisateur(Long id) {
        System.out.println("\n=== [Controller] → Suppression de l'utilisateur id=" + id + " ===");
        try {
            userService.deleteUser(id);
            System.out.println("[Controller] ✓ Supprimé avec succès.");
        } catch (Exception e) {
            System.out.println("[Controller] ✗ Erreur : " + e.getMessage());
        }
    }
}
