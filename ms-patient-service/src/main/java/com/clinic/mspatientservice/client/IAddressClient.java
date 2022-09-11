package com.clinic.mspatientservice.client;

import com.clinic.mspatientservice.domain.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "address-service")
//@RequestMapping("/address")
public interface IAddressClient {


    @GetMapping("/address/id={id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id);

    @GetMapping("/address/patientId={id}")
    public ResponseEntity<Address> getAddressByPatientId(@PathVariable Long id);

    @PostMapping("/address/register/patientId={patientId}")
    public ResponseEntity<Address> registerAddress(@PathVariable Long patientId, @RequestBody Address address);

    @PutMapping("/address/update/patientId={patientId}")
    public ResponseEntity<Address> updateAddressByPatientId(@PathVariable Long patientId, @RequestBody Address address);

    @DeleteMapping("/address/delete/patientId={patientId}")
    public ResponseEntity<Void> deleteAddressByPatientId(@PathVariable Long patientId);


}
