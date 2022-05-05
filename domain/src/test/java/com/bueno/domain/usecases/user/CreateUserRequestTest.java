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

package com.bueno.domain.usecases.user;

import com.bueno.domain.usecases.user.model.CreateUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserRequestTest {

    @Test
    @DisplayName("Should not throw if parameters are valid")
    void shouldNotThrowIfParametersAreValid() {
        assertDoesNotThrow(() -> new CreateUserRequest("username", "password", "email@email.com"));

    }

    @Test
    @DisplayName("Should throw if username is null")
    void shouldThrowIfUsernameIsNull() {
        assertThrows(NullPointerException.class, () -> new CreateUserRequest(null, "password", "email@email.com"));
    }

    @Test
    @DisplayName("Should throw if username is empty")
    void shouldThrowIfUsernameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new CreateUserRequest("", "password", "email@email.com"));
    }

    @Test
    @DisplayName("Should throw if password is null")
    void shouldThrowIfPasswordIsNull() {
        assertThrows(NullPointerException.class, () -> new CreateUserRequest("username", null, "email@email.com"));
    }

    @Test
    @DisplayName("Should throw if password is empty")
    void shouldThrowIfPasswordIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new CreateUserRequest("username", "", "email@email.com"));
    }

    @Test
    @DisplayName("Should throw if user email is null")
    void shouldThrowIfUserEmailIsNull() {
        assertThrows(NullPointerException.class, () -> new CreateUserRequest("username", "password", null));
    }

    @Test
    @DisplayName("Should throw if email is empty")
    void shouldThrowIfEmailIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new CreateUserRequest("username", "password", ""));
    }
}