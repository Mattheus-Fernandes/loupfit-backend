package com.loupfit.bffservice.business.dto.out;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SupplierDTO {

    private String id;
    private String supplierName;
    private String cnpj;
    private String email;
    private String phone;
    private String linkProfile;
    private boolean active;
}
