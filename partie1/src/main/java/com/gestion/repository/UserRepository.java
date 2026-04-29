package com.gestion.repository;

import com.gestion.model.User;
import java.util.List;
import java.util.Optional;

/**
 * REPOSITORY - Rôle :
 * Le Repository est la couche d'accès aux données (Data Access Layer).
 * Il isole la logique d'accès aux données du reste de l'application.
 * En définissant une INTERFACE, on garantit que le Service ne dépend jamais
 * d'une implémentation concrète (Map, MySQL, etc.), ce qui permet de changer
 * la source de données sans toucher au code métier (principe d'inversion
 * de dépendance - SOLID).
 *
 * Rôle d'Optional :
 * Optional<T> est un conteneur introduit en Java 8 qui peut contenir
 * ou non une valeur. Il permet d'éviter les NullPointerException et force
 * l'appelant à gérer explicitement le cas "aucun résultat trouvé".
 * Exemple : Optional<User> result = repo.findById(1L);
 *   result.isPresent()       → true si un User a été trouvé
 *   result.get()             → retourne le User (à utiliser après isPresent())
 *   result.orElse(null)      → retourne null si absent
 *   result.orElseThrow(...)  → lance une exception si absent
 */
public interface UserRepository {

    /**
     * Enregistre ou met à jour un utilisateur.
     * @param user l'utilisateur à sauvegarder
     */
    void save(User user);

    /**
     * Recherche un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur
     * @return Optional contenant le User s'il existe, vide sinon
     */
    Optional<User> findById(Long id);

    /**
     * Retourne la liste de tous les utilisateurs.
     * @return liste (possiblement vide) de tous les utilisateurs
     */
    List<User> findAll();

    /**
     * Supprime l'utilisateur correspondant à l'identifiant donné.
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    void delete(Long id);
}
