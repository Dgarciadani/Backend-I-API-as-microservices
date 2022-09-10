package com.clinic.mspatientservice.domain.model;

import com.clinic.mspatientservice.domain.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public record Address(String street, Integer door, String city, String state, Long patientId) {


}
