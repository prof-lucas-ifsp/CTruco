/*
 *  Copyright (C) 2021 Lucas B. R. de Oliveira - IFSP/SCL
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

package com.bueno.domain.entities.player.mineirobot;

import com.bueno.domain.entities.game.HandScore;
import com.bueno.domain.entities.player.util.Bot;
import com.bueno.domain.entities.player.util.CardToPlay;
import com.bueno.domain.usecases.game.GameRepository;

import java.util.UUID;

public class MineiroBot extends Bot {

    public MineiroBot(UUID uuid) {
        this(null, uuid);
    }

    public MineiroBot(GameRepository repo, UUID uuid) {
        super(repo, uuid, "MineiroBot");
    }

    @Override
    public CardToPlay decideCardToPlay() {
        return PlayingStrategy.of(getCards(), this).playCard();
    }

    @Override
    public boolean wantsToRaise() {
        return PlayingStrategy.of(getCards(), this).requestTruco();
    }

    @Override
    public int getRaiseResponse(HandScore newHandScore) {
        return PlayingStrategy.of(getCards(), this).getTrucoResponse(newHandScore.get());
    }

    @Override
    public boolean getMaoDeOnzeResponse() {
        return PlayingStrategy.of(getCards(), this).getMaoDeOnzeResponse();
    }
}
