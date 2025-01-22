package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.List;

/**
 * Interface définissant un observateur pour le modèle.
 */
public interface Observer {

    /**
     * Méthode appelée lorsqu'il y a une mise à jour dans le modèle.
     * @param messages La liste des messages mise à jour.
     */
    void update(List<Message> messages);
}
