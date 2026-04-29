package com.gestion.model;

/**
 * MODÈLE (Model) - Rôle :
 * Le modèle représente les données de l'application.
 * Il encapsule la logique métier et les données, sans connaître
 * la vue ni le contrôleur. Dans notre cas, User est l'entité centrale
 * de l'application.
 */
public class User {

    private Long id;
    private String name;
    private String email;

    // ─── Constructeurs ───────────────────────────────────────────────────────

    public User() {}

    public User(Long id, String name, String email) {
        this.id    = id;
        this.name  = name;
        this.email = email;
    }

    // ─── Getters & Setters ───────────────────────────────────────────────────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ─── toString ────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "User{" +
               "id="    + id    +
               ", name='"  + name  + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
