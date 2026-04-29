package com.gestion;

import com.gestion.controller.UserController;
import com.gestion.model.User;
import com.gestion.repository.UserRepository;
import com.gestion.repository.UserRepositoryImpl;
import com.gestion.service.UserService;
import com.gestion.service.UserServiceImpl;

/**
 * Point d'entrée de l'application - Partie 1 (stockage en Map)
 *
 * Architecture MVC utilisée :
 *
 *   Main
 *    └─► UserController   (reçoit les actions, affiche les résultats)
 *         └─► UserService / UserServiceImpl  (logique métier)
 *              └─► UserRepository / UserRepositoryImpl  (accès aux données - Map)
 *                   └─► Map<Long, User>  (stockage en mémoire)
 *
 * Le flux est unidirectionnel :
 *   Controller → Service → Repository → Données
 * Jamais l'inverse (les couches basses n'appellent pas les couches hautes).
 */
public class MainPartie1 {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Application Gestion Utilisateurs - MVC    ║");
        System.out.println("║            Partie 1 : Map (mémoire)         ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // ── Instanciation des couches (injection de dépendances manuelle) ─────
        UserRepository   repository = new UserRepositoryImpl();
        UserService      service    = new UserServiceImpl(repository);
        UserController   controller = new UserController(service);

        // ── Création de quelques utilisateurs ────────────────────────────────
        User alice = new User(1L, "Alice Martin",   "alice@example.com");
        User bob   = new User(2L, "Bob Dupont",     "bob@example.com");
        User carol = new User(3L, "Carol Durand",   "carol@example.com");

        // ── Test : ajout d'utilisateurs ───────────────────────────────────────
        controller.ajouterUtilisateur(alice);
        controller.ajouterUtilisateur(bob);
        controller.ajouterUtilisateur(carol);

        // ── Test : affichage de tous les utilisateurs ─────────────────────────
        controller.afficherTousLesUtilisateurs();

        // ── Test : recherche par id ───────────────────────────────────────────
        controller.afficherUtilisateur(2L);

        // ── Test : utilisateur inexistant ─────────────────────────────────────
        controller.afficherUtilisateur(99L);

        // ── Test : suppression ────────────────────────────────────────────────
        controller.supprimerUtilisateur(1L);

        // ── Affichage final ───────────────────────────────────────────────────
        controller.afficherTousLesUtilisateurs();

        // ── Test : ajout d'un utilisateur invalide ────────────────────────────
        controller.ajouterUtilisateur(new User(4L, "", "invalide@example.com"));

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║               Fin de l'application          ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
