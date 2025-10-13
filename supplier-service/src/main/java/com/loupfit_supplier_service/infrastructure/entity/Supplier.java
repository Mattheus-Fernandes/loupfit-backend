package com.loupfit_supplier_service.infrastructure.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("supplies")
public class Supplier {

    @Id
    private String id;
    private String supplierName;
    private String cnpj;
    private String email;
    private String phone;
    private String linkProfile;
    private boolean active;
}
