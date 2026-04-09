package com.tarunkumar.practice.Dto;


import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetRequestDTO {

                @NotBlank(message = "Business unit is required")
                private String bu;


                @NotBlank(message = "Item ID is required")
                @Indexed(unique = true)
                private String itemId;

                @NotBlank(message = "Asset display name is required")
                @Size(min = 2, max = 50, message = "Asset display name must be between 2 and 50 characters")
                private String assetDisplayName;

                @NotBlank(message = "Serial number is required")
                @Size(max = 30, message = "Serial number can be up to 30 characters only")
                private String serialNumber;

                @NotBlank(message = "Status is required")
                private String status;

                @NotBlank(message = "Condition is required")
                private String condition;

                @NotBlank(message = "Department is required")
                private String department;

                @NotBlank(message = "Location is required")
                private String location;

                @NotNull(message = "Purchase cost is required")
                @Positive(message = "Purchase cost must be positive")
                private Double purchaseCost;

                @NotNull(message = "Purchase date is required")
                @PastOrPresent(message = "Purchase date cannot be in the future")
                private LocalDate purchaseDate;

                @PastOrPresent(message = "Manufacture date cannot be in the future")
                private LocalDate manufactureDate;

                @Future(message = "Warranty expiry must be a future date")
                private LocalDate warrantyExpiry;

                @Size(max = 1255, message = "Description can be up to 1255 characters only")
                private String description;


    }

