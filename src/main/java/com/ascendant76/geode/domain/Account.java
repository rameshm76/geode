package com.ascendant76.geode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Region("Accounts")
public class Account {
    private String id;
    private String firstName;
    private String lastName;
}
