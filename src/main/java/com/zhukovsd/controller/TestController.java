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

package com.zhukovsd.controller;

import com.zhukovsd.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    private final TestService service;

    @Autowired
    public TestController(TestService service) {
        this.service = service;
    }

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public Message message(@PathVariable("name") String name) throws Exception {
        Message msg = new Message(name, service.greet(name));

        if (!name.equals("mate")) {
            throw new Exception("you are not my mate");
        }

        return msg;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity onException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage())
        );
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
