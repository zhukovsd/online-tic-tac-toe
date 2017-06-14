/*
 * Copyright 2017 Zhukov Sergey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhukovsd.rest.game;

import com.zhukovsd.rest.exceptions.NoSuchGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    private final GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @PostConstruct
    private void init() throws InterruptedException {
        service.newGame(GameMode.GAME_MODE_3x3);
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id") int id) throws InterruptedException, NoSuchGameException {
//        service.newGame(GameMode.GAME_MODE_3x3);

        return service.getGame(id);
    }

    @PostMapping("/{id}/turns")
    public Game makeTurn(@PathVariable("id") int id) throws NoSuchGameException, InterruptedException {
        return service.makeTurn(id);
    }
}
