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

/**
 * Created by ZhukovSD on 12.06.2017.
 */
public enum GameMode {
    GAME_MODE_3x3 (3, 3),
    GAME_MODE_5x5 (5, 5);

    public final String value;

    public final int rowCount;
    public final int columnCount;

    GameMode(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;

        value = this.rowCount + "x" + this.columnCount;
    }
}
