
package com.appy44.stream_api_examples.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profession {
    private String domain; // e.g., Backend, Frontend
    private String role;
}
