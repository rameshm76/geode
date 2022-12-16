package com.ascendant76.geode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account {
    private String id;
    private String firstName;
    private String lastName;
}
