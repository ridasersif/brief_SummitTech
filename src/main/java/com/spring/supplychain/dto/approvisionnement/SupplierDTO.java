package com.spring.supplychain.dto.approvisionnement;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {


    private Long idSupplier;

    @NotBlank(message = "Le nom du fournisseur est obligatoire")
    private String name;

    @NotBlank(message = "Le contact est obligatoire")
    private String contact;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    private String phone;

    @PositiveOrZero(message = "La note doit être positive ou zéro")
    private Double rating;

    @PositiveOrZero(message = "Le délai doit être positif ou zéro")
    private Integer leadTime;


}
