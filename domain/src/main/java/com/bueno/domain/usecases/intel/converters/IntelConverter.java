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

package com.bueno.domain.usecases.intel.converters;

import com.bueno.domain.entities.intel.Intel;
import com.bueno.domain.usecases.game.dtos.PlayerDto;
import com.bueno.domain.usecases.intel.dtos.IntelDto;

import java.util.List;
import java.util.stream.Collectors;

public class IntelConverter {
    public static IntelDto of(Intel intel){
        final List<PlayerDto> playersDto = intel.players().stream()
                .map(IntelConverter::ofPlayerIntel)
                .collect(Collectors.toList());

        return new IntelDto(
                intel.timestamp(),
                intel.isGameDone(),
                intel.gameWinner().orElse(null),
                intel.isMaoDeOnze(),
                intel.handPoints(),
                intel.pointsProposal().orElse(null),
                intel.roundWinnersUsernames(),
                intel.roundWinnersUuid(),
                intel.roundsPlayed(),
                CardConverter.toDto(intel.vira()),
                intel.openCards().stream().map(CardConverter::toDto).collect(Collectors.toList()),
                intel.handWinner().orElse(null),
                intel.currentPlayerUuid().orElse(null),
                intel.currentPlayerScore(),
                intel.currentPlayerUsername(),
                intel.currentOpponentScore(),
                intel.currentOpponentUsername(),
                intel.cardToPlayAgainst().map(CardConverter::toDto).orElse(null),
                playersDto,
                intel.event().orElse(null),
                intel.eventPlayerUuid().orElse(null),
                intel.eventPlayerUsername().orElse(null),
                intel.possibleActions()
        );
    }

    private static PlayerDto ofPlayerIntel(Intel.PlayerIntel playerIntel){
        final var playerCards = playerIntel.getCards().stream()
                .map(CardConverter::toDto)
                .collect(Collectors.toList());

        return new PlayerDto(
                playerIntel.getUsername(),
                playerIntel.getUuid(),
                playerIntel.getScore(),
                playerIntel.isBot(),
                playerCards);
    }
}
