/*
 * Copyright 2021-2022 VMware, Inc.
 * SPDX-License-Identifier: BSD-2-Clause
 */

package functions;

import java.util.function.Function;

import functions.models.Employee;
import functions.models.Person;

import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.messaging.Message;

/*
This class demonstrates the definition of a function called "Handler".
This function can be accessed by targetting the "/handler" path while
providing the correct data:
    {
        "specversion" : "1.0",
        "type" : "hire",
        "source" : "https://spring.io/",
        "id" : "A234-1234-1234",
        "datacontenttype" : "application/json",
        "data": {
            "firstName": "John",
            "lastName": "Doe"
        }
    }
If this is the only function defined, it may be accessed via "/"
path.
*/
public class Handler implements Function<Message<Person>, Message<Employee>> {
    @Override
    public Message<Employee> apply(Message<Person> msg) {
        String ceType = (String) msg.getHeaders().get("ce-type");
        if (ceType == null || !ceType.equals("hire")) {
            throw new RuntimeException("Did not receive a header 'ce-type' with value 'hire'");
        }

        Person person = msg.getPayload();
        System.out.printf("Person: first(%s) last(%s)\n", person.getFirstName(), person.getLastName());
        Employee employee = new Employee(person);
        System.out.printf("Employee: %s\n", employee.getMessage());
        return CloudEventMessageBuilder.withData(employee).build();
    }
}
