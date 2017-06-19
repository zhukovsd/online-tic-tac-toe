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

import com.zhukovsd.rest.exceptions.NoSuchGameException;
import com.zhukovsd.rest.exceptions.NoSuchTicketException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZhukovSD on 18.06.2017.
 */
@Service
public class QueueService {
    private final ConcurrentHashMap<Integer, QueueTicket> tickets = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger();

    public Integer standInQueue(String sessionId) {
        int id = nextId.getAndIncrement();
        tickets.put(id, new QueueTicket(sessionId));
        return id;
    }

    public QueueTicket getTicket(int id) throws NoSuchTicketException {
        QueueTicket ticket = tickets.get(id);

        if (ticket == null)
            throw new NoSuchTicketException();

        return ticket;
    }
}
