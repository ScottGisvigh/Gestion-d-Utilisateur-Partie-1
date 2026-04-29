package com.gestion.service;

import com.gestion.model.User;
import com.gestion.repository.UserRepository;
import java.util.List;
import java.util.Optional;

/**
 * SERVICE IMPL - Rôle :
 * UserServiceImpl est l'implémentation concrète de l'interface UserService.
 * Elle contient la logique métier réelle :
 *  - Validation des données entrantes
 *  - Gestion des cas d'erreur (utilisateur introuvable, données invalides)
 *  - Délégation des opérations de persistance au Repository
 *
 * Notez qu'elle dépend de l'interface UserRepository (et non de
 * UserRepositoryImpl), ce qui garantit le découplage.
 */
public class UserServiceImpl implements UserService {

    // Dépend de l'interface, pas de l'implémentation (principe SOLID)
    private final UserRepository userRepository;

    // Injection de dépendance via le constructeur
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Valide puis délègue la sauvegarde au Repository.
     */
    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("[Service] L'utilisateur ne peut pas être null.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("[Service] Le nom de l'utilisateur est obligatoire.");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("[Service] L'email de l'utilisateur est obligatoire.");
        }
        userRepository.save(user);
        System.out.println("[Service] Utilisateur ajouté avec succès : " + user.getName());
    }

    /**
     * Récupère un utilisateur ou lance une exception s'il est introuvable.
     */
    @Override
    public User getUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("[Service] Aucun utilisateur trouvé avec l'id : " + id);
        }
    }

    /**
     * Retourne tous les utilisateurs via le Repository.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("[Service] " + users.size() + " utilisateur(s) trouvé(s).");
        return users;
    }

    /**
     * Supprime un utilisateur après vérification de son existence.
     */
    @Override
    public void deleteUser(Long id) {
        // On vérifie d'abord que l'utilisateur existe
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("[Service] Impossible de supprimer : utilisateur introuvable (id=" + id + ").");
        }
        userRepository.delete(id);
        System.out.println("[Service] Utilisateur (id=" + id + ") supprimé avec succès.");
    }
}
