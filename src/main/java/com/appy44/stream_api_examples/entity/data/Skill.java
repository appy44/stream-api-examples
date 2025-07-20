
package com.appy44.stream_api_examples.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    private String name;
    private int proficiency; // 1 to 10
}
