package com.tarunkumar.practice.Entity;

import com.tarunkumar.practice.Enum.AssetCondition;
import com.tarunkumar.practice.Enum.AssetStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "assets")
public class Asset {

        @Id
        private String id;
        private String bu;
        private String itemId;
        private String assetDisplayName;
        private String serialNumber;

        private AssetStatus status;
        private AssetCondition condition;

        private String department;
        private String location;

        private Double purchaseCost;

        private LocalDate purchaseDate;
        private LocalDate manufactureDate;
        private LocalDate warrantyExpiry;

        private String description;

}
