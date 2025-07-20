
package com.appy44.stream_api_examples.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyExperience {
    private String companyName;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
}
