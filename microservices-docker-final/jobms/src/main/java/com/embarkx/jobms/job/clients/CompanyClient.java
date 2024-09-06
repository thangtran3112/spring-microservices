package com.embarkx.jobms.job.clients;
import com.embarkx.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Pick up the company-service url from the application.properties
 * or application.docker.properties based on Spring profile
 * */
@FeignClient(name = "COMPANY-SERVICE", url = "${company-service.url}")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable("id") Long id);
}

