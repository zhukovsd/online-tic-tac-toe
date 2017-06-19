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

package com.zhukovsd.rest.queue;

/**
 * Created by ZhukovSD on 20.06.2017.
 */
public enum TicketState {
    PENDING("pending"),
    GAME_FOUND("game_found");

    private String value;

    TicketState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value + "hi";
    }
}
