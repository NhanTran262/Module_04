package com.codegym.repository;

import com.codegym.model.MedicalDeclaration;

import java.util.List;

public interface IMedicalDeclarationRepository {
    List<MedicalDeclaration> getAll();

    MedicalDeclaration getById(int id);

    MedicalDeclaration edit(MedicalDeclaration medicalDeclaration);

    List<String> dates();

    List<String> genders();

    List<String> countrys();

    List<String> travelInfo();
}
