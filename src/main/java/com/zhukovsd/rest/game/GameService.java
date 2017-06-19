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
import com.zhukovsd.rest.game.board.MarkData;
import com.zhukovsd.rest.game.board.Position;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GameService {
    private ConcurrentHashMap<Integer, Game> games = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger();

    public int newGame() throws InterruptedException {
        int id = this.nextId.getAndIncrement();

        Game game = new Game();
        games.put(id, game);

        return id;
    }

    public Game getGame(int id) throws NoSuchGameException {
        Game game = games.get(id);

        if (game == null)
            throw new NoSuchGameException();

        return game;
    }

    public Game makeTurn(int id) throws InterruptedException, NoSuchGameException {
        Game game = getGame(id);

        synchronized (game) {
            game.turnNumber++;

            game.test++;
            game.test2++;
        }

        return game;
    }

    public static void main(String[] args) throws InterruptedException, NoSuchGameException {
        GameService service = new GameService();
        int id = service.newGame();

        Game game = service.getGame(id);

        for (int i = 0; i < 9; i++) {
            game.board.marks.add(new MarkData("X", new Position(0, i)));
        }

        long time = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            service.makeTurn(id);
        }

        // 26k for cloning with SerializationUtils
        System.out.println((System.nanoTime() - time) / 1000000 + " ms");
        System.out.println(service.getGame(id).turnNumber);
    }
}
