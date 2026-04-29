package com.gestion.service;

import com.gestion.model.User;
import java.util.List;

/**
 * SERVICE - Rôle :
 * Le Service est la couche métier (Business Layer) de l'application.
 * Il contient la logique applicative : validation, règles métier,
 * orchestration des appels au Repository.
 *
 * Pourquoi définir une INTERFACE pour le Service ?
 *  - Même raison que pour le Repository : respecter l'inversion de dépendance.
 *  - Le Contrôleur ne connaît que cette interface, jamais l'implémentation.
 *  - On peut ainsi facilement créer des implémentations alternatives ou
 *    des mocks pour les tests unitaires.
 *
 * Différence Service vs Repository :
 *  - Repository : parle à la source de données (CRUD pur)
 *  - Service    : contient la logique métier, valide les données,
 *                 peut combiner plusieurs appels Repository
 */
public interface UserService {

    /**
     * Ajoute un nouvel utilisateur après validation.
     * @param user l'utilisateur à ajouter
     */
    void addUser(User user);

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id identifiant de l'utilisateur
     * @return l'utilisateur correspondant
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    User getUser(Long id);

    /**
     * Retourne la liste complète des utilisateurs.
     * @return liste de tous les utilisateurs
     */
    List<User> getAllUsers();

    /**
     * Supprime l'utilisateur identifié par l'id donné.
     * @param id identifiant de l'utilisateur à supprimer
     */
    void deleteUser(Long id);
}
