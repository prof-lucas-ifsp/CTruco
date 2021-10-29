/*
 * Copyright (C) 2021 Lucas B. R. de Oliveira
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
 *  along with Foobar.  If not, see <https://www.gnu.org/licenses/>
 */

package com.bueno.truco.domain.entities.hand;

import com.bueno.truco.domain.entities.truco.TrucoResult;
import com.bueno.truco.domain.entities.player.util.Player;

import java.util.Optional;

public class HandResult {

    private final Player winner;
    private final HandScore score;

    public HandResult() {
        winner = null;
        score = null;
    }

    public HandResult(Player winner, HandScore handScore) {
        if(winner == null || handScore == null)
            throw new NullPointerException("Parameters must not be null!");
        this.winner = winner;
        this.score = HandScore.of(handScore);
    }

    public HandResult(TrucoResult result){
        this(result.getWinner().get(), HandScore.of(result.getScore()));
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }

    public HandScore getScore() {
        return score;
    }
}