package com.gestion.repository;

import com.gestion.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REPOSITORY IMPL - Rôle :
 * UserRepositoryImpl est l'implémentation concrète de l'interface UserRepository.
 * Elle contient la vraie logique d'accès aux données.
 * Dans cette Partie 1, les données sont stockées en mémoire dans une
 * Map<Long, User>, où la clé est l'id et la valeur est l'objet User.
 *
 * Avantages de cette approche :
 *  - Aucune dépendance externe (pas de base de données)
 *  - Très rapide pour les tests et le prototypage
 *  - Accès O(1) par identifiant grâce à la HashMap
 *
 * Inconvénient :
 *  - Les données sont perdues à chaque redémarrage de l'application
 */
public class UserRepositoryImpl implements UserRepository {

    // Simule la base de données : clé = id de l'utilisateur
    private final Map<Long, User> database = new HashMap<>();

    /**
     * Sauvegarde ou écrase l'utilisateur dans la Map.
     */
    @Override
    public void save(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("L'utilisateur et son id ne peuvent pas être null.");
        }
        database.put(user.getId(), user);
        System.out.println("[Repository] Utilisateur sauvegardé : " + user);
    }

    /**
     * Recherche un utilisateur par id.
     * Retourne Optional.empty() si l'id est absent de la Map.
     */
    @Override
    public Optional<User> findById(Long id) {
        // Optional.ofNullable retourne Optional.empty() si la valeur est null
        return Optional.ofNullable(database.get(id));
    }

    /**
     * Retourne une nouvelle liste contenant tous les utilisateurs stockés.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }

    /**
     * Supprime l'utilisateur de la Map via son id.
     */
    @Override
    public void delete(Long id) {
        if (!database.containsKey(id)) {
            System.out.println("[Repository] Aucun utilisateur trouvé avec l'id : " + id);
            return;
        }
        database.remove(id);
        System.out.println("[Repository] Utilisateur avec l'id " + id + " supprimé.");
    }
}
