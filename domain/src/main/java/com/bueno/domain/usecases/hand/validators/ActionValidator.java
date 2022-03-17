/*
 *  Copyright (C) 2022 Lucas B. R. de Oliveira - IFSP/SCL
 *  Contact: lucas <dot> oliveira <at> ifsp <dot> edu <dot> br
 *
 *  This file is part of CTruco (Truco game for didactic purpose).
 *
 *  CTruco is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CTruco is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CTruco.  If not, see <https://www.gnu.org/licenses/>
 */

package com.bueno.domain.usecases.hand.validators;

import com.bueno.domain.entities.game.Game;
import com.bueno.domain.entities.hand.Hand;
import com.bueno.domain.entities.intel.PossibleAction;
import com.bueno.domain.entities.player.Player;
import com.bueno.domain.usecases.game.GameRepository;
import com.bueno.domain.usecases.utils.Notification;
import com.bueno.domain.usecases.utils.Validator;

import java.util.EnumSet;
import java.util.Objects;
import java.util.UUID;

public class ActionValidator extends Validator<UUID> {

    private final GameRepository repo;
    private final OngoingGameValidator gameValidator;
    private final PossibleAction action;

    public ActionValidator(GameRepository repo, PossibleAction action) {
        this.repo = repo;
        this.gameValidator = new OngoingGameValidator(repo);
        this.action = action;
    }

    @Override
    public Notification validate(UUID uuid) {
        final Notification gameNotification = gameValidator.validate(uuid);
        if(gameNotification.hasErrors()) return gameNotification;

        final Game game = repo.findByUserUuid(uuid).orElse(null);
        final Player requester = getRequester(uuid, Objects.requireNonNull(game));
        final Hand currentHand = game.currentHand();
        final EnumSet<PossibleAction> possibleActions = currentHand.getPossibleActions();

        Notification notification = new Notification();

        if(!currentHand.getCurrentPlayer().equals(requester))
            notification.addError("User with UUID " + uuid + " is not in not the current player.");

        if(!possibleActions.contains(action))
            notification.addError("Invalid action for hand state. Valid actions: " + possibleActions);

        return notification;
    }

    private Player getRequester(UUID uuid, Game game) {
        return game.getPlayer1().getUuid().equals(uuid) ? game.getPlayer1() : game.getPlayer2();
    }
}