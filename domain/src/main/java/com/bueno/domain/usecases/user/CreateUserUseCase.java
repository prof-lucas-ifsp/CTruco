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

import com.bueno.domain.entities.player.User;
import com.bueno.domain.usecases.utils.EntityAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateUserUseCase {

    private final UserRepository repo;

    public CreateUserUseCase(UserRepository repo) {
        this.repo = Objects.requireNonNull(repo, "User repository must not be null.");
    }

    //TODO change every use case to return response model or receive request model
    public ResponseModel create(RequestModel requestModel){
        Objects.requireNonNull(requestModel,"Request model must not be null.");

        repo.findByUsername(requestModel.username()).ifPresent(unused -> {
            throw new EntityAlreadyExistsException("This username is already in use: " + requestModel.username());});

        repo.findByEmail(requestModel.email()).ifPresent(unused -> {
            throw new EntityAlreadyExistsException("This email is already in use: " + requestModel.email());});

        final User user = new User(requestModel.username(), requestModel.email());

        repo.save(user);
        return new ResponseModel(user.getUuid(), user.getUsername(), user.getEmail());
    }
}