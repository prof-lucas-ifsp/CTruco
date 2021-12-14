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

package com.bueno.domain.usecases.game;

import com.bueno.domain.entities.game.Game;
import com.bueno.domain.entities.hand.Hand;
import com.bueno.domain.entities.hand.Round;
import com.bueno.domain.entities.player.util.Player;
import com.bueno.domain.entities.truco.Truco;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateGameUseCaseTest {

    @Mock private Player p1;
    @Mock private Player p2;
    @Mock private Player p3;
    @Mock private Player p4;

    private CreateGameUseCase sut;

    @BeforeAll
    static void init(){
        Logger.getLogger(Game.class.getName()).setLevel(Level.OFF);
        Logger.getLogger(Hand.class.getName()).setLevel(Level.OFF);
        Logger.getLogger(Round.class.getName()).setLevel(Level.OFF);
        Logger.getLogger(Truco.class.getName()).setLevel(Level.OFF);
    }

    @BeforeEach
    void setUp(){
        sut = new CreateGameUseCase(new InMemoryGameRepository());
    }

    @Test
    @DisplayName("Should create game with valid parameters")
    void shouldCreateGameWithValidParameters() {
        Game game = sut.create(p1, p2);
        assertAll(
                () -> assertEquals(p1, game.getPlayer1()),
                () -> assertEquals(p2, game.getPlayer2()),
                () -> assertNotNull(game.getUuid())
        );
    }

    @Test
    @DisplayName("Should throw if any player parameter is null")
    void shouldThrowIfAnyPlayerParameterIsNull() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> sut.create(null, p2)),
                () -> assertThrows(NullPointerException.class, () -> sut.create(p1, null))
        );
    }

    @Test
    @DisplayName("Should not create new game if one of the players is enrolled in another game")
    void shouldNotCreateNewGameIfOneOfThePlayersEnrolledInAnotherGame() {
        when(p1.getUsername()).thenReturn("p1");
        when(p2.getUsername()).thenReturn("p2");
        sut.create(p1, p2);
        assertThrows(UnsupportedGameRequestException.class, () -> sut.create(p1, p3));
    }

    @Test
    @DisplayName("Should be able to add multiple games")
    void shouldBeAbleToAddMultipleGames() {
        when(p1.getUsername()).thenReturn("p1");
        when(p2.getUsername()).thenReturn("p2");
        when(p3.getUsername()).thenReturn("p3");
        when(p4.getUsername()).thenReturn("p4");
        Game game1 = sut.create(p1, p2);
        Game game2 = sut.create(p3, p4);
        assertNotEquals(game1, game2);
    }

}